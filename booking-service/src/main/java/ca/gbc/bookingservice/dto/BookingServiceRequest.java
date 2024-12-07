package ca.gbc.bookingservice.dto;

import ca.gbc.bookingservice.model.BookingModel;
import java.time.LocalDateTime;

public record BookingServiceRequest(
		Long userId,
		Long roomId,
		LocalDateTime startTime,
		LocalDateTime endTime,
		String purpose,
		UserDetails userDetails
) {

	// Nested UserDetails record
	public record UserDetails(String email, String firstName, String lastName) { }
}
