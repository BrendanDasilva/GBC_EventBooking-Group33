package ca.gbc.eventservice.dto;

public record EventServiceResponse(

		String id,
		String eventName,
		String organizerId,
		String eventType,
		int eventAttendees
) {}
