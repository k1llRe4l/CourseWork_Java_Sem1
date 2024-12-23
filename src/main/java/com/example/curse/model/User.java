package com.example.curse.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Сущность "Пользователь", представляющая данные пользователя системы.
 * Хранит информацию о логине, хеше пароля и роли пользователя.
 */
@Entity
@Data
public class User {

    /**
     * Уникальный идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Уникальное имя пользователя (логин).
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * Хеш пароля пользователя.
     */
    @Column(nullable = false)
    private String passwordHash;

    /**
     * Роль пользователя, определяющая его уровень доступа в системе.
     * Может быть ADMIN или STUDENT.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /**
     * Перечисление, представляющее возможные роли пользователя.
     */
    public enum Role {
        ADMIN, STUDENT
    }

    /**
     * Возвращает уникальный идентификатор пользователя.
     *
     * @return уникальный идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор пользователя.
     *
     * @param id уникальный идентификатор
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает имя пользователя (логин).
     *
     * @return имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Устанавливает имя пользователя (логин).
     *
     * @param username имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Возвращает хеш пароля пользователя.
     *
     * @return хеш пароля
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Устанавливает хеш пароля пользователя.
     *
     * @param passwordHash хеш пароля
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Возвращает роль пользователя.
     *
     * @return роль пользователя
     */
    public Role getRole() {
        return role;
    }

    /**
     * Устанавливает роль пользователя.
     *
     * @param role роль пользователя
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
