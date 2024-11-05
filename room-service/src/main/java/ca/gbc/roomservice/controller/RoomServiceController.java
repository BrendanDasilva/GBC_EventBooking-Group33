package ca.gbc.roomservice.controller;

import ca.gbc.roomservice.dto.RoomServiceRequest;
import ca.gbc.roomservice.model.RoomServiceModel;
import ca.gbc.roomservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomServiceController {

  @Autowired
  private RoomService roomService;

  @PostMapping
  public ResponseEntity<RoomServiceModel> addRoom(@RequestBody RoomServiceRequest request) {
    RoomServiceModel room = roomService.addRoom(request);
    return ResponseEntity.ok(room);
  }

  @PutMapping("/{id}")
  public ResponseEntity<RoomServiceModel> updateRoom(@PathVariable Long id, @RequestBody RoomServiceRequest request) {
    RoomServiceModel room = roomService.updateRoom(id, request);
    return ResponseEntity.ok(room);
  }

  @GetMapping
  public ResponseEntity<List<RoomServiceModel>> getAllRooms() {
    List<RoomServiceModel> rooms = roomService.getAllRooms();
    return ResponseEntity.ok(rooms);
  }

  @GetMapping("/{id}")
  public ResponseEntity<RoomServiceModel> getRoomById(@PathVariable Long id) {
    RoomServiceModel room = roomService.getRoomById(id);
    return ResponseEntity.ok(room);
  }

  @GetMapping("/availability/{roomName}")
  public ResponseEntity<Boolean> checkRoomAvailability(@PathVariable String roomName) {
    boolean isAvailable = roomService.checkRoomAvailability(roomName);
    return ResponseEntity.ok(isAvailable);
  }
}
