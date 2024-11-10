package ca.gbc.bookingservice.dto;

import ca.gbc.bookingservice.model.BookingModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingServiceRequest {

    private Long userId;
    private Long roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String purpose;

    // Convert DTO to Entity (BookingModel)
    public BookingModel toEntity() {
        return BookingModel.builder()
                .userId(this.userId)
                .roomId(this.roomId)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .purpose(this.purpose)
                .build();
    }
}
