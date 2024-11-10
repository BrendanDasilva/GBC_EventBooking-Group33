package ca.gbc.eventservice.service;



import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ca.gbc.eventservice.model.EventServiceModel;
import ca.gbc.eventservice.repository.EventServiceRepository;




@SuppressWarnings("ALL")
@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements ca.gbc.eventservice.services.EventService {

	private final EventServiceRepository eventServiceRepository;

	@Override
	public EventServiceModel createEvent(EventServiceModel eventModel) {
		log.info("CREATING EVENT: {}", eventModel);
		return eventServiceRepository.save(eventModel);
	}

	@Override
	public EventServiceModel updateEvent(String eventId, EventServiceModel updateEventModel) {
		log.info("UPDATING EVENT: {}", updateEventModel);
		return eventServiceRepository.findById(eventId)
				.map(existingEvent -> {
					existingEvent.setEventName(updateEventModel.getEventName());
					existingEvent.setOrganizerId(updateEventModel.getOrganizerId());
					existingEvent.setEventType(updateEventModel.getEventType());
					existingEvent.setExpectedAttendees(updateEventModel.getExpectedAttendees());
					return eventServiceRepository.save(existingEvent);
				})
				.orElseThrow(() -> new RuntimeException("--- EVENT NOT FOUND"));
	}

	@Override
	public EventServiceModel getEventById(String eventId) {
		return eventServiceRepository.findById(eventId)
				.orElseThrow(() -> new RuntimeException("--- EVENT NOT FOUND"));

	}

	@Override
	public List<EventServiceModel> getAllEvents() {
		return eventServiceRepository.findAll();
	}

	@Override
	public void deleteEvent(String eventId) {
		log.info("DELETING EVENT: {}", eventId);
		eventServiceRepository.deleteById(eventId);
	}
}
