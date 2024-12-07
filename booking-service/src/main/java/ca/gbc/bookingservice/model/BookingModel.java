package ca.gbc.bookingservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bookings") // MongoDB annotation
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingModel {

	@Id
	private String id; // MongoDB expects a String ID

	@Column(name = "booking_number")
	private String bookingNumber;

	@Column(name = "user_id", nullable = false)
	private String userId; // Changed to String

	@Column(name = "room_id", nullable = false)
	private String roomId; // Changed to String

	@Column(name = "start_time", nullable = false)
	private LocalDateTime startTime;

	@Column(name = "end_time", nullable = false)
	private LocalDateTime endTime;

	@Column(name = "purpose")
	private String purpose;

	private String email;
	private String firstName;
	private String lastName;
}
