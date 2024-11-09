package ca.gbc.roomservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomServiceRequest {
    private String roomName;
    private int capacity;
    private List<String> features;
    private boolean availability;
}