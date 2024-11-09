package ca.gbc.eventservice.service;

import ca.gbc.eventservice.dto.EventServiceRequest;
import ca.gbc.eventservice.dto.EventServiceResponse;
import ca.gbc.eventservice.model.EventServiceModel;
import ca.gbc.eventservice.repository.EventServiceRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor

public class EventServiceImpl implements EventService {

	private final EventServiceRepository eventRepository;

	/*----- CREATE EVENT -----*/
	@Override
	public EventServiceModel createEvent(EventServiceRequest eventRequest){

		EventServiceModel eventServiceModel = new EventServiceModel(
				null,
				eventRequest.eventName(),
				eventRequest.organizerId(),
				eventRequest.eventType(),
				eventRequest.expectedAttendees()
		);

		return eventRepository.save(eventServiceModel);
	}

	/*----- GET ALL EVENTS -----*/
	@Override
	public List<EventServiceModel> getAllEvents(){
		return eventRepository.findAll();
	}

	/*----- GET EVENT BY ID -----*/
	@Override
	public EventServiceModel getEventById(String id){
		return eventRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Event with id " + id + " not found"));
	}

	/*----- UPDATE EVENT -----*/
	@Override
	public EventServiceModel updateEvent(EventServiceRequest eventRequest, String id){

		EventServiceModel event = getEventById(id);
		event.setEventName(eventRequest.eventName());
		event.setOrganizerId(eventRequest.organizerId());
		event.setEventType(eventRequest.eventType());
		event.setExpectedAttendees(eventRequest.expectedAttendees());
		return eventRepository.save(event);

	}


	/*----- DELETE EVENT -----*/
	@Override
	public void deleteEvent(String id){
		eventRepository.deleteById(id);
	}

	@Override
	public String getEventType(String id) {
		return eventServiceRepository.findById(id)
				.map(EventServiceModel::getEventType)
				.orElseThrow(() -> new RuntimeException("Event not found"));
	}


}