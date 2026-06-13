package ru.mtuci.petunin.notify.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mtuci.petunin.notify.model.dto.RegisterRequest;
import ru.mtuci.petunin.notify.model.entity.AppUser;
import ru.mtuci.petunin.notify.model.enums.AppRole;
import ru.mtuci.petunin.notify.repository.AppUserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private AppUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldRegisterUser() {
        RegisterRequest request = new RegisterRequest();
        request.setFullName("Алексей Петунин");
        request.setEmail("petunin@example.com");
        request.setPassword("12345");
        when(userRepository.existsByEmail("petunin@example.com")).thenReturn(false);
        when(passwordEncoder.encode("12345")).thenReturn("encoded");
        authService.register(request);
        ArgumentCaptor<AppUser> captor = ArgumentCaptor.forClass(AppUser.class);
        verify(userRepository).save(captor.capture());
        assertEquals("encoded", captor.getValue().getPassword());
        assertEquals(AppRole.ROLE_USER, captor.getValue().getRole());
    }

    @Test
    void shouldThrowWhenEmailExists() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("petunin@example.com");
        when(userRepository.existsByEmail("petunin@example.com")).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> authService.register(request));
        verify(userRepository, never()).save(any(AppUser.class));
    }
}
