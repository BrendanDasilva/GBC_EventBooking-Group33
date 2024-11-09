package ca.gbc.eventservice;

import ca.gbc.eventservice.dto.EventServiceRequest;
import ca.gbc.eventservice.model.EventServiceModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestEventServiceApplication {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest")
			.withExposedPorts(27017)
			.withEnv("MONGO_INITDB_ROOT_USERNAME", "admin")
			.withEnv("MONGO_INITDB_ROOT_PASSWORD", "password");

	@Autowired
	private TestRestTemplate restTemplate; //simulating http request

	@Test
	public void testCreateEvent(){
		EventServiceRequest eventServiceRequest = new EventServiceRequest(
				"01", "Event Name", "Organizer01",  50
		);

		// Post
		ResponseEntity<EventServiceModel> createResponse = restTemplate.postForEntity(
				"/api/events", eventServiceRequest, EventServiceModel.class
		);

		assertEquals(HttpStatus.CREATED, createResponse.getStatusCode(), "---- NOT CREATED.");
		assertNotNull(createResponse.getBody(), "---RESPONSE BODY IS NULL");

		String createEventId = createResponse.getBody().getId();
		ResponseEntity<EventServiceModel> getReponse = restTemplate.getForEntity(
				"/api/events/" + createEventId, EventServiceModel.class
		);
		assertEquals(HttpStatus.OK, getReponse.getStatusCode(), "---- NOT OK.");
		assertNotNull(getReponse.getBody(), "---RESPONSE BODY IS NULL");
		assertEquals("Event Name", getReponse.getBody().getEventName(), "---- NOT EVENT NAME.");
	}
}
