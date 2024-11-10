package ca.gbc.eventservice.dto;

public record EventServiceRequest(

		String eventName,
		String organizerId,
		String eventType,
		int expectedAttendees

) {}
