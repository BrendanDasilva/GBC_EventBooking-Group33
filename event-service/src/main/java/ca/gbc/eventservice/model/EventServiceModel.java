package ca.gbc.eventservice.model;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@Document(collection = "events")
public class EventServiceModel {

	@Id
	private String id;
	private String eventName;
	private String organizerId;
	private String eventType;
	private int expectedAttendees;
}
