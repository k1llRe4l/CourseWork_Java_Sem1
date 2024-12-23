package com.example.curse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.curse.model.User;
import com.example.curse.repository.UserRepository;

import java.util.Collections;

/**
 * Сервис для загрузки информации о пользователе по имени пользователя.
 * Реализует интерфейс {@link UserDetailsService}.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Загружает информацию о пользователе по имени пользователя.
     *
     * @param username имя пользователя для поиска
     * @return объект {@link UserDetails}, содержащий информацию о пользователе
     * @throws UsernameNotFoundException если пользователь с указанным именем не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Преобразование роли пользователя в SimpleGrantedAuthority
        String role = user.getRole().name();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPasswordHash(),
                Collections.singleton(new SimpleGrantedAuthority(role)) // Присваиваем роль
        );
    }
}
