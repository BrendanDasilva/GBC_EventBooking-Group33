package ca.gbc.roomservice.service;

import ca.gbc.roomservice.dto.RoomServiceRequest;
import ca.gbc.roomservice.model.RoomServiceModel;

import java.util.List;

public interface RoomService {
  RoomServiceModel addRoom(RoomServiceRequest request);
  RoomServiceModel updateRoom(Long id, RoomServiceRequest request);
  List<RoomServiceModel> getAllRooms();
  RoomServiceModel getRoomById(Long id);
  boolean checkRoomAvailability(String roomName);
}