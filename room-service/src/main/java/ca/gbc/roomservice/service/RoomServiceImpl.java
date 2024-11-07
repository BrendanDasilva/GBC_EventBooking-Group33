package ca.gbc.roomservice.service;

import ca.gbc.roomservice.dto.RoomServiceRequest;
import ca.gbc.roomservice.model.RoomServiceModel;
import ca.gbc.roomservice.repository.RoomServiceRepository;
import ca.gbc.roomservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

  @Autowired
  private RoomServiceRepository roomRepository;

  @Override
  public RoomServiceModel addRoom(RoomServiceRequest request) {
    RoomServiceModel room = RoomServiceModel.builder()
        .roomName(request.getRoomName())
        .capacity(request.getCapacity())
        .features(request.getFeatures())
        .availability(request.isAvailability())
        .build();
    return roomRepository.save(room);
  }

  @Override
  public RoomServiceModel updateRoom(Long id, RoomServiceRequest request) {
    Optional<RoomServiceModel> optionalRoom = roomRepository.findById(id);
    if (optionalRoom.isPresent()) {
      RoomServiceModel room = optionalRoom.get();
      room.setRoomName(request.getRoomName());
      room.setCapacity(request.getCapacity());
      room.setFeatures(request.getFeatures());
      room.setAvailability(request.isAvailability());
      return roomRepository.save(room);
    }
    throw new RuntimeException("Room not found with id: " + id);
  }

  @Override
  public List<RoomServiceModel> getAllRooms() {
    return roomRepository.findAll();
  }

  @Override
  public RoomServiceModel getRoomById(Long id) {
    return roomRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
  }

  @Override
  public boolean checkRoomAvailability(String roomName) {
    return roomRepository.findByRoomName(roomName)
        .map(RoomServiceModel::isAvailability)
        .orElse(false);
  }

  @Override
  public void deleteRoom(Long id) {

  }
}