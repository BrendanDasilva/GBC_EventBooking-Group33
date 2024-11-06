package ca.gbc.userservice.repository;

import ca.gbc.userservice.model.UserServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserServiceRepository extends JpaRepository<UserServiceModel, UUID> {
  Optional<UserServiceModel> findByEmail(String username);
}
