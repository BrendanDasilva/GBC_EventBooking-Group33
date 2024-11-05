package ca.gbc.userservice.service;

import ca.gbc.userservice.model.UserServiceModel;
import ca.gbc.userservice.dto.UserServiceRequest;
import ca.gbc.userservice.repository.UserServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

  private final UserServiceRepository userServiceRepository;

  @Autowired
  public UserServiceImpl(UserServiceRepository userServiceRepository) {
    this.userServiceRepository = userServiceRepository;
  }

  @Override
  public UserServiceModel createUser(UserServiceRequest request) {
    UserServiceModel user = UserServiceModel.builder()
        .name(request.name())
        .email(request.email())
        .role(request.role())
        .userType(request.userType())
        .build();
    return userServiceRepository.save(user);
  }

  @Override
  public Optional<UserServiceModel> getUserById(UUID id) {
    return userServiceRepository.findById(id);
  }

  @Override
  public List<UserServiceModel> getAllUsers() {
    return userServiceRepository.findAll();
  }

  @Override
  public UserServiceModel updateUser(UUID id, UserServiceRequest request) {
    return userServiceRepository.findById(id)
        .map(user -> {
          user.setName(request.name());
          user.setEmail(request.email());
          user.setRole(request.role());
          user.setUserType(request.userType());
          return userServiceRepository.save(user);
        })
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  @Override
  public void deleteUser(UUID id) {
    userServiceRepository.deleteById(id);
  }
}