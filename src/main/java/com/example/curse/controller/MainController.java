package com.example.curse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * Контроллер для основных страниц приложения.
 * Управляет отображением приветственной страницы, страницы о проекте и перенаправлением к расписанию.
 */
@Controller
public class MainController {

    /**
     * Отображает приветственную страницу после успешного входа пользователя.
     * На страницу передаётся имя пользователя.
     *
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя HTML-шаблона ("welcome")
     */
    @GetMapping("/welcome")
    public String welcomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "welcome";
    }

    /**
     * Перенаправляет запрос на страницу просмотра расписания.
     *
     * @return строка с перенаправлением на маршрут "/schedule/view"
     */
    @GetMapping("/schedule")
    public String scheduleRedirect() {
        return "redirect:/schedule/view"; // Перенаправляем на основную страницу расписания
    }

    /**
     * Отображает страницу "Об авторе".
     *
     * @return имя HTML-шаблона ("about")
     */
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
}
