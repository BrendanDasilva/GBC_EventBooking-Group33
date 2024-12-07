package ca.gbc.bookingservice.dto;

import java.time.LocalDateTime;
import ca.gbc.bookingservice.model.BookingModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingServiceResponse {

	private Long id;
	private Long userId;
	private Long roomId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String purpose;
	private String message;  // This is the field for the message

	// Constructor for error or status message response
	public BookingServiceResponse(Long id, String message) {
		this.id = id;
		this.message = message;
	}

	// Optional: Convert BookingModel to BookingServiceResponse
	public static BookingServiceResponse fromBookingModel(BookingModel bookingModel) {
		return new BookingServiceResponse(
			bookingModel.getId(),
			bookingModel.getUserId(),
			bookingModel.getRoomId(),
			bookingModel.getStartTime(),
			bookingModel.getEndTime(),
			bookingModel.getPurpose(),
			null
		);
	}


}