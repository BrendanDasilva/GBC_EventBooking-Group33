package ca.gbc.eventservice.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import ca.gbc.eventservice.services.EventService;
import org.springframework.web.bind.annotation.*;
import ca.gbc.eventservice.model.EventServiceModel;


@SuppressWarnings("ALL")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventServiceController {

	private final EventService eventService;

	@PostMapping
	public EventServiceModel createEvent(@RequestBody EventServiceModel eventServiceModel) {
		return eventService.createEvent(eventServiceModel);
	}

	@PutMapping("/{id}")
	public EventServiceModel updateEvent(@PathVariable String id, @RequestBody EventServiceModel eventServiceModel) {
		return eventService.updateEvent(id, eventServiceModel);
	}

	@GetMapping("/{id}")
	public EventServiceModel getEventById(@PathVariable String id) {
		return eventService.getEventById(id);
	}

	@GetMapping
	public List<EventServiceModel> getAllEvents() {
		return eventService.getAllEvents();
	}

	@DeleteMapping("/{id}")
	public void deleteEvent(@PathVariable String id) {
		eventService.deleteEvent(id);
	}

}
