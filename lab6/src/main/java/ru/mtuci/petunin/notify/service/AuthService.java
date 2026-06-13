package ru.mtuci.petunin.notify.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mtuci.petunin.notify.model.dto.RegisterRequest;
import ru.mtuci.petunin.notify.model.entity.AppUser;
import ru.mtuci.petunin.notify.model.enums.AppRole;
import ru.mtuci.petunin.notify.repository.AppUserRepository;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(RegisterRequest request) {
        createUser(request, AppRole.ROLE_USER);
    }

    @Transactional
    public void registerAdmin(RegisterRequest request) {
        createUser(request, AppRole.ROLE_ADMIN);
    }

    private void createUser(RegisterRequest request, AppRole role) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        AppUser user = new AppUser();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
    }
}
