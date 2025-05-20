package com.jvt.timetracker.controller;

import com.jvt.timetracker.dto.UserRequestDTO;
import com.jvt.timetracker.dto.UserResponseDTO;
import com.jvt.timetracker.mapper.ModelMapper;
import com.jvt.timetracker.model.User;
import com.jvt.timetracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userDTO) {
        User user = modelMapper.toUser(userDTO);
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(modelMapper.toUserResponseDTO(createdUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(modelMapper.toUserResponseDTO(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable) {
        Page<User> users = userService.getAllUsers(pageable);
        Page<UserResponseDTO> userDTOs = users.map(modelMapper::toUserResponseDTO);
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsersList() {
        List<User> users = userService.getAllUsers();
        List<UserResponseDTO> userDTOs = users.stream()
                .map(modelMapper::toUserResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDTO userDTO) {
        User user = modelMapper.toUser(userDTO);
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(modelMapper.toUserResponseDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
} 