package ca.gbc.eventservice.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "events")
public class EventServiceModel {

	@Id
	private String id;
	private String eventName;
	private String organizerId;
	private String eventType;
	private int expectedAttendees;


}
