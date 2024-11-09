package ca.gbc.userservice.controller;

import ca.gbc.userservice.dto.UserServiceRequest;
import ca.gbc.userservice.model.UserServiceModel;
import ca.gbc.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserServiceController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserServiceModel> createUser(@RequestBody UserServiceRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserServiceModel> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserServiceModel>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserServiceModel> updateUser(@PathVariable UUID id, @RequestBody UserServiceRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/role")
    @ResponseStatus(HttpStatus.OK)
    public boolean getUserRoleByID(@PathVariable UUID id) {
        return userService.getRoleById(id);
    }
}
