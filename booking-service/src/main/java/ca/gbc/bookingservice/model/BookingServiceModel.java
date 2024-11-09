package ca.gbc.bookingservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingServiceModel {

    @Id
    private String id;

    private String userId;
    private String roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String purpose;
}