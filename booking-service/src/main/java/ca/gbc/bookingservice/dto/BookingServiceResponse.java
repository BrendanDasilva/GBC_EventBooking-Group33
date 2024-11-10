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

	private String id;
	private String userId;
	private String roomId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String purpose;

	// Optional: Convert BookingModel to BookingServiceResponse
	public static BookingServiceResponse fromBookingModel(BookingModel bookingModel) {
		return new BookingServiceResponse(
				bookingModel.getId(),
				bookingModel.getUserId(),
				bookingModel.getRoomId(),
				bookingModel.getStartTime(),
				bookingModel.getEndTime(),
				bookingModel.getPurpose()
		);
	}
}
