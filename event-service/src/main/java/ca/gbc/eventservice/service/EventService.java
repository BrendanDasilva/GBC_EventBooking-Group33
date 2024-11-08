package ca.gbc.eventservice.service;

import ca.gbc.eventservice.model.EventServiceModel;
import java.util.List;


public interface EventService {

	EventServiceModel saveEvent(EventServiceModel event);
	List<EventServiceModel> getAllEvents();
	EventServiceModel getEventById(String id);
	EventServiceModel updateEvent(EventServiceModel event);
	void deleteEvent(String id);
	String getEventType(String id);

}
