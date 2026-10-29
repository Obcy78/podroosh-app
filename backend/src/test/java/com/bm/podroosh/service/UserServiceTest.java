package com.bm.podroosh.service;

import com.bm.podroosh.domain.User;
import com.bm.podroosh.domain.UserStatus;
import com.bm.podroosh.dto.UserRequest;
import com.bm.podroosh.dto.UserResponse;
import com.bm.podroosh.exceptions.UserNotFoundException;
import com.bm.podroosh.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private final Instant created = Instant.parse("2026-01-15T10:00:00Z");

    // ---------- getUser ----------

    @Test
    void getUser_shouldReturnUser() {
        User user = user(1L, "Jan", "Kowalski");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponse response = userService.getUser(1L);

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("Jan");
        assertThat(response.surname()).isEqualTo("Kowalski");
        assertThat(response.email()).isEqualTo("jan@example.com");
        assertThat(response.phone()).isEqualTo("123456789");
        assertThat(response.status()).isEqualTo(UserStatus.ACTIVE);
        assertThat(response.created()).isEqualTo(created);
    }

    @Test
    void getUser_shouldThrowWhenNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUser(99L))
                .isInstanceOf(UserNotFoundException.class);
    }

    // ---------- getAllUsers ----------

    @Test
    void getAllUsers_shouldReturnMappedList() {
        User u1 = user(1L, "Jan", "Kowalski");
        User u2 = user(2L, "Anna", "Nowak");
        when(userRepository.findAll()).thenReturn(List.of(u1, u2));

        List<UserResponse> result = userService.getAllUsers();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).id()).isEqualTo(1L);
        assertThat(result.get(0).name()).isEqualTo("Jan");
        assertThat(result.get(1).id()).isEqualTo(2L);
        assertThat(result.get(1).name()).isEqualTo("Anna");
    }

    @Test
    void getAllUsers_shouldReturnEmptyList() {
        when(userRepository.findAll()).thenReturn(List.of());

        assertThat(userService.getAllUsers()).isEmpty();
    }

    // ---------- createUser ----------

    @Test
    void createUser_shouldSaveAndReturnResponse() {
        UserRequest request = new UserRequest(
                "Jan",
                "Kowalski",
                "jan@example.com",
                "123456789",
                UserStatus.ACTIVE
        );

        User saved = user(10L, "Jan", "Kowalski");
        when(userRepository.save(any(User.class))).thenReturn(saved);

        UserResponse response = userService.createUser(request);

        assertThat(response.id()).isEqualTo(10L);
        assertThat(response.name()).isEqualTo("Jan");
        assertThat(response.surname()).isEqualTo("Kowalski");
        assertThat(response.email()).isEqualTo("jan@example.com");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());

        User toSave = captor.getValue();
        assertThat(toSave.getName()).isEqualTo("Jan");
        assertThat(toSave.getSurname()).isEqualTo("Kowalski");
        assertThat(toSave.getEmail()).isEqualTo("jan@example.com");
        assertThat(toSave.getPhone()).isEqualTo("123456789");
        assertThat(toSave.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(toSave.getId()).isNull(); // przed zapisem
    }

    // ---------- updateUser ----------

    @Test
    void updateUser_shouldUpdateFields() {
        User existing = user(5L, "Stare", "Nazwisko");
        when(userRepository.findById(5L)).thenReturn(Optional.of(existing));

        UserRequest request = new UserRequest(
                "Nowe",
                "Nazwisko",
                "nowe@example.com",
                "987654321",
                UserStatus.INACTIVE
        );

        UserResponse response = userService.updateUser(5L, request);

        assertThat(response.name()).isEqualTo("Nowe");
        assertThat(response.surname()).isEqualTo("Nazwisko");
        assertThat(response.email()).isEqualTo("nowe@example.com");
        assertThat(response.phone()).isEqualTo("987654321");
        assertThat(response.status()).isEqualTo(UserStatus.INACTIVE);

        verify(userRepository).findById(5L);
        verify(userRepository, never()).save(any()); // dirty checking
    }

    @Test
    void updateUser_shouldThrowWhenNotFound() {
        when(userRepository.findById(123L)).thenReturn(Optional.empty());

        UserRequest request = new UserRequest(
                "X", "Y", "x@y.com", "111", UserStatus.ACTIVE
        );

        assertThatThrownBy(() -> userService.updateUser(123L, request))
                .isInstanceOf(UserNotFoundException.class);

        verify(userRepository, never()).save(any());
    }

    // ---------- deleteUser ----------

    @Test
    void deleteUser_shouldDeleteExistingUser() {
        User existing = user(7L, "Jan", "Kowalski");
        when(userRepository.findById(7L)).thenReturn(Optional.of(existing));

        userService.deleteUser(7L);

        verify(userRepository).findById(7L);
        verify(userRepository).delete(existing);
    }

    @Test
    void deleteUser_shouldThrowWhenNotFound() {
        when(userRepository.findById(50L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.deleteUser(50L))
                .isInstanceOf(UserNotFoundException.class);

        verify(userRepository, never()).delete(any());
    }

    // ---------- helper ----------

    private User user(Long id, String name, String surname) {
        User user = User.builder()
                .name(name)
                .surname(surname)
                .email("jan@example.com")
                .phone("123456789")
                .status(UserStatus.ACTIVE)
                .build();
        user.setId(id);
        user.setCreated(created);
        return user;
    }
}