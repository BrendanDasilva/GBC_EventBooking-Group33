package ca.gbc.eventservice.service;

import ca.gbc.eventservice.repository.EventServiceRepository;
import ca.gbc.eventservice.model.EventServiceModel;
import ca.gbc.userservice.model.UserServiceModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

	private final EventServiceRepository eventServiceRepository;
	private final RestTemplate restTemplate;

	@Value("${user.service.url}")
	private String userServiceUrl;

	public EventServiceImpl(EventServiceRepository eventServiceRepository, RestTemplate restTemplate) {
		this.eventServiceRepository = eventServiceRepository;
		this.restTemplate = restTemplate;
	}

	@Override
	public EventServiceModel saveEvent(EventServiceModel event) {
		String userRole = fetchRole(event.getOrganizerId());

		if("FACULTY".equals(userRole) || "STAFF".equals(userRole)) {

			return eventServiceRepository.save(event);

		} else {

			throw new IllegalArgumentException("Invalid user role");
		}
	}

	/*------------- COMMUNICATION WITH USER-SERVICE -------------*/

	private String fetchRole(String organizerId) {
		String url = UriComponentsBuilder.fromHttpUrl(userServiceUrl)
				.path("/api/users/" + organizerId + "/role")
				.toUriString();

		UserServiceModel user = restTemplate.getForObject(url, UserServiceModel.class);

		if(user == null || user.getRole() == null) {
			throw new IllegalArgumentException("Invalid user role");
		}

		return user.getRole().name();
	}

	@Override
	public List<EventServiceModel> getAllEvents() {
		return eventServiceRepository.findAll();
	}

	@Override
	public EventServiceModel getEventById(String id){
		return eventServiceRepository.findById(id).orElse(null);
	}

	@Override
	public EventServiceModel updateEvent(EventServiceModel event){
		return eventServiceRepository.save(event);
	}

	@Override
	public void deleteEvent(String id) {
		eventServiceRepository.deleteById(id);
	}

	@Override
	public String getEventType(String id) {
		return eventServiceRepository.findById(id)
				.map(EventServiceModel::getEventType)
				.orElseThrow(() -> new RuntimeException("Event not found"));
	}


}