package com.example.curse.config;

import com.example.curse.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Конфигурация безопасности для приложения с использованием Spring Security.
 * Определяет правила доступа, систему аутентификации и настройки шифрования паролей.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Создаёт фильтр безопасности, определяющий правила доступа к ресурсам и страницам.
     *
     * @param http объект HttpSecurity для настройки безопасности
     * @return настроенный объект SecurityFilterChain
     * @throws Exception если возникнет ошибка конфигурации
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/welcome", true) // После логина переходим на /welcome
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login").permitAll()
                )
                .authorizeHttpRequests(auth -> auth
                        // Доступ к статическим ресурсам для всех
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        // Доступ к функциям администратора только для ролей ADMIN
                        .requestMatchers("/students/**", "/schedule/add").hasAuthority("ADMIN")
                        // Доступ к расписанию для ADMIN и STUDENT
                        .requestMatchers("/schedule/**").hasAnyAuthority("ADMIN", "STUDENT")
                        // Все остальные запросы требуют авторизации
                        .anyRequest().authenticated()
                )
                .build();
    }

    /**
     * Создаёт бин для кодировщика паролей с использованием BCrypt.
     *
     * @return объект BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Создаёт бин для пользовательского сервиса аутентификации.
     *
     * @return объект CustomUserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    /**
     * Создаёт провайдер аутентификации, который использует {@link CustomUserDetailsService}
     * для загрузки данных пользователя и {@link BCryptPasswordEncoder} для проверки пароля.
     *
     * @return объект DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Настраивает AuthenticationManager для использования провайдера аутентификации.
     *
     * @param auth объект AuthenticationManagerBuilder
     * @throws Exception если возникнет ошибка конфигурации
     */
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}






