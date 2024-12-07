package ca.gbc.roomservice.repository;

import ca.gbc.roomservice.model.RoomServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomServiceRepository extends MongoRepository<RoomServiceModel, Long> {
    Optional<RoomServiceModel> findByRoomName(String roomName);
}