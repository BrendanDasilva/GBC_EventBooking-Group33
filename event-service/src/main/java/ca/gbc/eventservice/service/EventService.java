package ca.gbc.eventservice.service;

import ca.gbc.eventservice.dto.EventServiceRequest;
import ca.gbc.eventservice.model.EventServiceModel;
import java.util.List;


public interface EventService {

	EventServiceModel createEvent(EventServiceRequest eventRequest);

	EventServiceModel updateEvent(EventServiceRequest eventRequest, String id);

	EventServiceModel savedEvent(EventServiceRequest eventRequest);

	List<EventServiceModel> getAllEvents();

	EventServiceModel getEventById(String id);

	String getEventType(String id);


	void deleteEvent(String id);




}
