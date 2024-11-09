package ca.gbc.eventservice.service;

import ca.gbc.eventservice.dto.EventServiceRequest;
import ca.gbc.eventservice.dto.EventServiceResponse;
import ca.gbc.eventservice.model.EventServiceModel;
import java.util.List;


public interface EventService {

	EventServiceModel createEvent(EventServiceRequest eventRequest);

	List<EventServiceModel> getAllEvents();

	EventServiceModel getEventById(String id);

	EventServiceModel updateEvent(EventServiceRequest eventRequest, String id);

	void deleteEvent(String id);
	String getEventType(String id);


}
