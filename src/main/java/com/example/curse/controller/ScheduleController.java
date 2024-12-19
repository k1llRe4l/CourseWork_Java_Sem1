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

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    // Метод для отображения расписания
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

    // Метод для отображения формы добавления занятия (доступно только для администратора)
    @Secured("ADMIN")
    @GetMapping("/schedule/add")
    public String addScheduleForm(Model model) {
        model.addAttribute("groups", studentGroupRepository.findAll()); // Список групп для выбора
        return "add-schedule"; // Шаблон для добавления занятия
    }

    // Метод для сохранения нового занятия (доступно только для администратора)
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






