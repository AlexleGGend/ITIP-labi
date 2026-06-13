package ru.mtuci.petunin.notify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.petunin.notify.model.entity.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
