package ca.gbc.eventservice;


import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import ca.gbc.eventservice.dto.EventServiceRequest;
import ca.gbc.eventservice.dto.EventServiceResponse;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventServiceApplicationTests {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;

	}

	/*---------- CREATE TEST ----------*/
	@Test
	public void createEventTest(){

		EventServiceRequest request = new EventServiceRequest(
				"Event Name",
				"123",
				"Social",
				100
		);

		RestAssured.given()

				.contentType(ContentType.JSON)
				.body(request)
				.when()
				.post("/api/events")
				.then()
				.statusCode(201) // CREATED
				.body("eventName", equalTo("Event Name"))
				.body("organizerId", equalTo("123"))
				.body("eventType", equalTo("Social"))
				.body("expectedAttendees", equalTo(200)); // OK
	}

	/*---------- GET ALL TEST ----------*/
	@Test
	public void getAllEventsTest(){

		RestAssured.given()

				.contentType(ContentType.JSON)
				.when()
				.get("/api/events")
				.then()
				.statusCode(200) // OK
				.body("events.size()", equalTo(0));
	}

	/*---------- GET BY ID TEST ----------*/
	@Test
	public void getEventByIdTest(){

		String eventId = "123";

		RestAssured.given()

				.contentType(ContentType.JSON)
				.pathParam("id", eventId)
				.when()
				.get("/api/events/{id}")
				.then()
				.statusCode(200) // OK
				.body("id", equalTo(eventId));

	}


	@Test
	public void getEventByIdNotFoundTest(){

		String invalidEventId = "--- Invalid Event Id ";

		RestAssured.given()

				.contentType(ContentType.JSON)
				.pathParam("id", invalidEventId)
				.when()
				.get("/api/events/{id}")
				.then()
				.statusCode(404); // NOT FOUND
	}


	/*---------- UPDATE TEST ----------*/
	@Test
	public void updateEventTest(){

		String eventId = "123";

		EventServiceRequest request = new EventServiceRequest(

				"Updated eventName",
				"1234",
				"Luncheon",
				200

		);

		RestAssured.given()

				.contentType(ContentType.JSON)
				.body(request)
				.pathParam("id", eventId)
				.when()
				.put("/api/events/{id}")
				.then()
				.statusCode(200) // OK
				.body("eventName", equalTo("Updated eventName"))
				.body("expectedAttendees", equalTo(200));

	}

	/*---------- DELETE TEST ----------*/
	@Test
	public void deleteEventTest(){

		String eventId = "123";

		RestAssured.given()

				.contentType(ContentType.JSON)
				.pathParam("id", eventId)
				.when()
				.delete("/api/events/{id}")
				.then()
				.statusCode(204); // NO CONTENT

		RestAssured.given()

				.contentType(ContentType.JSON)
				.pathParam("id", eventId)
				.when()
				.get("/api/events/{id}")
				.then()
				.statusCode(404); // NOT FOUND
	}

}



