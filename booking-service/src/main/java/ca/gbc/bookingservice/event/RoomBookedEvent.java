package ca.gbc.bookingservice.event;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookedEvent {
    private String bookingNumber;
    private String email;
}
