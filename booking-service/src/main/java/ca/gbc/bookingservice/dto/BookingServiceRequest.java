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

	// Convert the record to BookingModel entity
	public BookingModel toEntity() {
		return BookingModel.builder()
				.userId(this.userId())
				.roomId(this.roomId())
				.startTime(this.startTime())
				.endTime(this.endTime())
				.purpose(this.purpose())
				.email(this.userDetails().email())
				.firstName(this.userDetails().firstName())
				.lastName(this.userDetails().lastName())
				.build();
	}

	// Nested UserDetails record
	public record UserDetails(String email, String firstName, String lastName) { }
}
