package com.example.curse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.curse.model.User;

/**
 * Репозиторий для работы с пользователями.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Находит пользователя по имени пользователя.
     *
     * @param username имя пользователя для поиска
     * @return Optional, содержащий пользователя, если он найден, или пустой Optional, если пользователь не найден
     */
    Optional<User> findByUsername(String username);
}
