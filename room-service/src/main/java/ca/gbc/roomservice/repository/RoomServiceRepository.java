package ca.gbc.roomservice.repository;

import ca.gbc.roomservice.model.RoomServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomServiceRepository extends JpaRepository<RoomServiceModel, Long> {
    Optional<RoomServiceModel> findByRoomName(String roomName);
}