package ca.gbc.userservice.repository;

import ca.gbc.userservice.model.UserServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserServiceRepository extends JpaRepository<UserServiceModel, UUID> {
  Optional<UserServiceModel> findByEmail(String username);
}
