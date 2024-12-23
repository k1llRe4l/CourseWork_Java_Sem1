package com.example.curse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для управления процессом аутентификации пользователей.
 * Обрабатывает отображение страницы логина.
 */
@Controller
public class LoginController {

    /**
     * Отображает страницу логина.
     *
     * @return имя HTML-файла с формой логина ("index")
     */
    @GetMapping("/login")
    public String loginPage() {
        return "index"; // Имя файла с формой логина
    }
}

