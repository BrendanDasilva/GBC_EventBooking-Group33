package ca.gbc.eventservice.controller;

import ca.gbc.eventservice.model.EventServiceModel;
import ca.gbc.eventservice.dto.EventServiceRequest;
import ca.gbc.eventservice.dto.EventServiceResponse;
import ca.gbc.eventservice.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventServiceController {

	private final EventService eventService;

	/*---------- CREATE NEW EVENT ----------*/
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // 201
	public ResponseEntity<EventServiceResponse> createEvent(@RequestBody EventServiceRequest eventRequest){
		log.info("Creating event {}", eventRequest.eventName());

		EventServiceModel event = mapToModel(eventRequest);
		EventServiceModel savedEvent = eventService.saveEvent(event);
		return new ResponseEntity<>(mapToResponse(savedEvent), HttpStatus.CREATED);
	}

	/*---------- GET ALL EVENTS ----------*/
	@GetMapping
	public List<EventServiceResponse> getAllEvents(){
		log.info("Getting all events");

		return eventService.getAllEvents().stream().map(this::mapToResponse).toList();
	}

	/*---------- GET BY ID ----------*/
	@GetMapping("/{id}")
	public ResponseEntity<EventServiceResponse> getEventById(@PathVariable String id){
		log.info("Getting event {}", id);

		EventServiceModel event = eventService.getEventById(id);
		return event != null ? ResponseEntity.ok(mapToResponse(event)) : ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.build();
	}

	/*---------- UPDATE EXISTING EVENT ----------*/
	@PutMapping("/{id}")
	public EventServiceResponse updateEvent(@PathVariable String id, @RequestBody EventServiceRequest eventRequest){

		log.info("Updating event {}", id);

		EventServiceModel event = mapToModel(eventRequest);
		event.setId(id);

		EventServiceModel updatedEvent = eventService.updateEvent(event);
		return ResponseEntity.ok(mapToResponse(updatedEvent)).getBody();

	}

	/*---------- DELETE BY ID ----------*/
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204 NO CONTENT
	public void deleteEvent(@PathVariable String id){

		log.info("Deleting event {}", id);
		eventService.deleteEvent(id);

	}

	/*---------- HELPER METHODS ----------*/
	private EventServiceModel mapToModel(EventServiceRequest request){

		return EventServiceModel.builder()
				.eventName(request.eventName())
				.organizerId(request.organizerId())
				.eventType(request.eventType())
				.expectedAttendees(request.expectedAttendees())
				.build();

	}

	private EventServiceResponse mapToResponse(EventServiceModel event){

		return new EventServiceResponse(
				event.getId(),
				event.getEventName(),
				event.getOrganizerId(),
				event.getEventType(),
				event.getExpectedAttendees()

		);
	}
}