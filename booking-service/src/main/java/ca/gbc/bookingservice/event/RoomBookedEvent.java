package ca.gbc.bookingservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookedEvent {

    private String bookingNumber;
    private String email;

}
