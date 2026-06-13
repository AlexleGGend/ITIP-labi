package ru.mtuci.petunin.notify.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mtuci.petunin.notify.exception.ResourceNotFoundException;
import ru.mtuci.petunin.notify.model.dto.UserRequest;
import ru.mtuci.petunin.notify.model.entity.AppUser;
import ru.mtuci.petunin.notify.repository.AppUserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private AppUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUser() {
        UserRequest request = new UserRequest();
        request.setFullName("Алексей Петунин");
        request.setEmail("petunin@example.com");
        AppUser saved = new AppUser();
        saved.setId(1L);
        saved.setFullName(request.getFullName());
        saved.setEmail(request.getEmail());
        when(userRepository.save(any(AppUser.class))).thenReturn(saved);
        AppUser result = userService.create(request);
        assertEquals(1L, result.getId());
        assertEquals("petunin@example.com", result.getEmail());
        verify(userRepository).save(any(AppUser.class));
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.findById(99L));
    }
}
