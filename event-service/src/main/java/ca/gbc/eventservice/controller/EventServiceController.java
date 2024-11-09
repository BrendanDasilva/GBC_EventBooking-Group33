package ca.gbc.eventservice.controller;

import ca.gbc.eventservice.model.EventServiceModel;
import ca.gbc.eventservice.dto.EventServiceRequest;
import ca.gbc.eventservice.dto.EventServiceResponse;
import ca.gbc.eventservice.service.EventService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventServiceController {

	private final EventService eventService;

	/*------- CREATE NEW EVENT -------*/
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // 201
	public ResponseEntity<EventServiceResponse> createEvent(@RequestBody EventServiceRequest eventRequest) {
		log.info("CREATING EVENT {}", eventRequest.eventName());

			EventServiceModel savedEvent = eventService.createEvent(eventRequest);
			EventServiceResponse response = mapToResponse(savedEvent);
			return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	/*------- GET ALL EVENTS -------*/
	@GetMapping
	public List<EventServiceResponse> getAllEvents() {
		log.info("GETTING ALL EVENTS");
		return eventService.getAllEvents()
				.stream()
				.map(this::mapToResponse)
				.toList();
	}

	/*------- GET EVENT BY ID -------*/
	@GetMapping("/{id}")
	public ResponseEntity<EventServiceResponse> getEventById(@PathVariable String id) {
		log.info("GETTING EVENT WITH ID {}", id);
		EventServiceModel event = eventService.getEventById(id);
		return ResponseEntity.ok(mapToResponse(event));

	}

	/*------- UPDATE EXISTING EVENT -------*/
	@PutMapping("/{id}")
	public ResponseEntity<EventServiceResponse> updateEvent(@PathVariable String id,
															@RequestBody EventServiceRequest eventRequest) {
		log.info("UPDATING EVENT WITH ID {}", id);
		EventServiceModel updatedEvent = eventService.updateEvent(eventRequest, id);
		return ResponseEntity.ok(mapToResponse(updatedEvent));

	}

	/*------- DELETE BY ID -------*/
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204
	public void deleteEvent(@PathVariable String id) {
		log.info("DELETING EVENT WITH ID {}", id);
		eventService.deleteEvent(id);
	}

	/*------- HELPER METHODS -------*/
	private EventServiceResponse mapToResponse(EventServiceModel event) {
		return new EventServiceResponse(
				event.getId(),
				event.getEventName(),
				event.getOrganizerId(),
				event.getEventType(),
				event.getExpectedAttendees()
		);
	}


}