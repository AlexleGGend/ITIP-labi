package ru.mtuci.petunin.notify.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mtuci.petunin.notify.exception.ResourceNotFoundException;
import ru.mtuci.petunin.notify.model.dto.UserRequest;
import ru.mtuci.petunin.notify.model.entity.AppUser;
import ru.mtuci.petunin.notify.repository.AppUserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final AppUserRepository userRepository;

    public UserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public AppUser create(UserRequest request) {
        AppUser user = new AppUser();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setTelegramChatId(request.getTelegramChatId());
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public AppUser findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
    }

    @Transactional
    public AppUser update(Long id, UserRequest request) {
        AppUser user = findById(id);
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setTelegramChatId(request.getTelegramChatId());
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.delete(findById(id));
    }
}
