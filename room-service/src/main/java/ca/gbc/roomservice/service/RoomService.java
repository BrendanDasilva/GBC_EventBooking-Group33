package ca.gbc.roomservice.service;

import ca.gbc.roomservice.dto.RoomServiceRequest;
import ca.gbc.roomservice.model.RoomServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    RoomServiceModel addRoom(RoomServiceRequest request);
    RoomServiceModel updateRoom(Long id, RoomServiceRequest request);
    List<RoomServiceModel> getAllRooms();
    RoomServiceModel getRoomById(Long id);
    boolean checkRoomAvailability(String roomName);
    void deleteRoom(Long id);
}