package ca.gbc.userservice.dto;

import ca.gbc.userservice.model.UserServiceModel.Role;

public record UserServiceRequest(
    String name,
    String email,
    Role role,
    String userType
) {}