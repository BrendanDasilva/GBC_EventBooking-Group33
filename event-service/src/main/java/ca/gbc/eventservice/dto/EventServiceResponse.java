package ca.gbc.eventservice.dto;

public record EventServiceResponse(

		String eventId,
		String eventName,
		String organizerId,
		String eventType,
		int expectedAttendees
) {}
