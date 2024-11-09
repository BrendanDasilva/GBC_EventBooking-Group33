package ca.gbc.userservice;

import ca.gbc.userservice.dto.UserServiceRequest;
import ca.gbc.userservice.model.UserServiceModel;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceApplicationTests {

	@ServiceConnection
	static PostgreSQLContainer<?> postgresDB = new PostgreSQLContainer<>("postgres:latest");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		postgresDB.start();
	}


	@Test
	void createUserTest() {
		UserServiceRequest request = new UserServiceRequest(
			"Jane Doe",
			"jane.doe@example.com",
			UserServiceModel.Role.STUDENT,
			"Standard User"
		);

		given()
			.contentType("application/json")
			.body(request)
			.when()
			.post("/api/users")
			.then()
			.statusCode(200)
			.body("id", notNullValue())
			.body("name", equalTo("Jane Doe"))
			.body("email", equalTo("jane.doe@example.com"))
			.body("role", equalTo("STUDENT"))
			.body("userType", equalTo("Standard User"));
	}


	@Test
	void updateUserTest() {
		UserServiceRequest createRequest = new UserServiceRequest(
			"Jane Doe",
			"jane.doe@example.com",
			UserServiceModel.Role.STUDENT,
			"Standard User"
		);

		UUID userId = UUID.fromString(
			given()
				.contentType("application/json")
				.body(createRequest)
				.when()
				.post("/api/users")
				.then()
				.statusCode(200)
				.extract().path("id")
		);

		// Update the user with new details
		UserServiceRequest updateRequest = new UserServiceRequest(
			"Jane Smith",
			"jane.smith@example.com",
			UserServiceModel.Role.STAFF,
			"Premium User"
		);

		given()
			.contentType("application/json")
			.body(updateRequest)
			.when()
			.put("/api/users/" + userId)
			.then()
			.statusCode(200)
			.body("id", equalTo(userId.toString()))
			.body("name", equalTo("Jane Smith"))
			.body("email", equalTo("jane.smith@example.com"))
			.body("role", equalTo("STAFF"))
			.body("userType", equalTo("Premium User"));
	}


	@Test
	void getUserByIdTest() {
		UUID userId = UUID.randomUUID();
		given()
			.pathParam("id", userId)
			.when()
			.get("/api/users/{id}")
			.then()
			.statusCode(404); // Adjust as needed
	}


	@Test
	void getAllUsersTest() {
		given()
			.when()
			.get("/api/users")
			.then()
			.statusCode(200)
			.body("size()", Matchers.greaterThanOrEqualTo(0));
	}


	@Test
	void deleteUserTest() {
		UUID userId = UUID.randomUUID();
		given()
			.pathParam("id", userId)
			.when()
			.delete("/api/users/{id}")
			.then()
			.statusCode(204);
	}

}
