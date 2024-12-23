package com.example.curse.controller;

import com.example.curse.model.Schedule;
import com.example.curse.model.StudentGroup;
import com.example.curse.repository.ScheduleRepository;
import com.example.curse.repository.StudentGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Контроллер для управления расписанием.
 * Позволяет отображать расписание, добавлять новые занятия и фильтровать их по дате и группе.
 */
@Controller
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    /**
     * Отображает расписание с возможностью фильтрации по дате и группе.
     *
     * @param date    дата для фильтрации (необязательно)
     * @param groupId ID группы для фильтрации (необязательно)
     * @param model   объект {@link Model} для передачи данных в представление
     * @return имя HTML-шаблона "schedule"
     */
    @GetMapping("/schedule/view")
    public String schedulePage(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                               @RequestParam(required = false) Long groupId,
                               Model model) {

        List<Schedule> schedules;

        // Логика фильтрации
        if (date != null && groupId != null) {
            schedules = scheduleRepository.findByDateAndGroup_Id(date, groupId);
        } else if (date != null) {
            schedules = scheduleRepository.findByDate(date);
        } else if (groupId != null) {
            schedules = scheduleRepository.findByGroup_Id(groupId);
        } else {
            schedules = scheduleRepository.findAll(); // Если фильтры не заданы, показываем все
        }

        // Добавляем атрибуты в модель
        model.addAttribute("schedules", schedules);
        model.addAttribute("groups", studentGroupRepository.findAll()); // Список групп для фильтрации

        return "schedule"; // Шаблон для отображения расписания
    }

    /**
     * Отображает форму для добавления нового занятия.
     * Доступно только для пользователей с ролью ADMIN.
     *
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя HTML-шаблона "add-schedule"
     */
    @Secured("ADMIN")
    @GetMapping("/schedule/add")
    public String addScheduleForm(Model model) {
        model.addAttribute("groups", studentGroupRepository.findAll()); // Список групп для выбора
        return "add-schedule"; // Шаблон для добавления занятия
    }

    /**
     * Сохраняет новое занятие в расписании.
     * Доступно только для пользователей с ролью ADMIN.
     *
     * @param date    дата занятия
     * @param time    время занятия
     * @param subject предмет занятия
     * @param groupId ID группы, к которой относится занятие
     * @return перенаправление на страницу расписания
     */
    @Secured("ADMIN")
    @PostMapping("/schedule/save")
    public String saveSchedule(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time,
                               @RequestParam String subject,
                               @RequestParam Long groupId) {

        StudentGroup group = studentGroupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group ID: " + groupId));

        Schedule schedule = new Schedule(date, time, subject, group);
        scheduleRepository.save(schedule);

        return "redirect:/schedule/view"; // После сохранения перенаправляем на страницу расписания
    }
}

