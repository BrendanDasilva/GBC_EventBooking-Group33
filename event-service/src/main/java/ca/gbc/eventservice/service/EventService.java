package ca.gbc.eventservice.services;

import ca.gbc.eventservice.model.EventServiceModel;

import java.util.List;


public interface EventService {

	EventServiceModel createEvent(EventServiceModel eventModel);

	EventServiceModel updateEvent(String eventId, EventServiceModel updateEventModel);

	EventServiceModel getEventById(String eventId);

	List<EventServiceModel> getAllEvents();

	void deleteEvent(String eventId);
}
