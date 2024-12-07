package ca.gbc.bookingservice.dto;

import java.time.LocalDateTime;

import ca.gbc.bookingservice.model.BookingModel;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingServiceResponse {

	private String id; // MongoDB's ID type
	private String userId;
	private String roomId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String purpose;
	private String message;

	// Factory method for convenience
	public static BookingServiceResponse fromBookingModel(BookingModel bookingModel) {
		return new BookingServiceResponse(
				bookingModel.getId(),
				bookingModel.getUserId(),
				bookingModel.getRoomId(),
				bookingModel.getStartTime(),
				bookingModel.getEndTime(),
				bookingModel.getPurpose(),
				null // message is optional
		);
	}
}

