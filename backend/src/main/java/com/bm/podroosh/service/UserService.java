package com.bm.podroosh.service;

import com.bm.podroosh.domain.User;
import com.bm.podroosh.dto.UserRequest;
import com.bm.podroosh.dto.UserResponse;
import com.bm.podroosh.exceptions.UserNotFoundException;
import com.bm.podroosh.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUser(Long userid) {
        User user = userRepository.findById(userid).orElseThrow(() ->
                new UserNotFoundException(userid));
        return mapToResponse(user);
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .status(user.getStatus())
                .created(user.getCreated())
                .build();
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public UserResponse createUser(UserRequest request) {
        User saved = userRepository.save(mapToEntity(request));
        return mapToResponse(saved);
    }

    private User mapToEntity(UserRequest user) {
        return User.builder()
                .name(user.name())
                .surname(user.surname())
                .email(user.email())
                .phone(user.phone())
                .status(user.status())
                .build();
    }

    public UserResponse updateUser(Long userId, UserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        updateEntity(request, user);
        return mapToResponse(user);
    }

    private void updateEntity(UserRequest request, User userEntity) {
        userEntity.setName(request.name());
        userEntity.setSurname(request.surname());
        userEntity.setEmail(request.email());
        userEntity.setPhone(request.phone());
        userEntity.setStatus(request.status());
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.delete(user);
    }
}
