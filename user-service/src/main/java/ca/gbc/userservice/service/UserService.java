package ca.gbc.userservice.service;

import ca.gbc.userservice.model.UserServiceModel;
import ca.gbc.userservice.dto.UserServiceRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
  UserServiceModel createUser(UserServiceRequest request);
  Optional<UserServiceModel> getUserById(UUID id);
  List<UserServiceModel> getAllUsers();
  UserServiceModel updateUser(UUID id, UserServiceRequest request);
  void deleteUser(UUID id);
}