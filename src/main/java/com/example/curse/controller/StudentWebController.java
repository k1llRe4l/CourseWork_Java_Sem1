package com.example.curse.controller;

import com.example.curse.model.Student;
import com.example.curse.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.example.curse.repository.StudentGroupRepository; // Импорт репозитория для групп

@Controller
public class StudentWebController {

    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository; // Репозиторий для групп

    public StudentWebController(StudentRepository studentRepository,
                                StudentGroupRepository studentGroupRepository) {
        this.studentRepository = studentRepository;
        this.studentGroupRepository = studentGroupRepository;
    }
    @GetMapping("/students")
    public String getStudentsPage(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "students"; // Имя HTML-шаблона
    }
    // Отображение страницы добавления студента
    @GetMapping("/students/add")
    public String addStudentForm(Model model) {
        // Передаем все группы на страницу
        model.addAttribute("groups", studentGroupRepository.findAll());
        return "add-student"; // Переход на add-student.html
    }

    // Обработка сохранения нового студента
    @PostMapping("/students/save")
    public String saveStudent(@RequestParam String name,
                              @RequestParam Long groupId, // Используем ID группы
                              @RequestParam double averageScore) {
        Student student = new Student();
        student.setName(name);

        // Находим группу по ID
        var group = studentGroupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id:" + groupId));
        student.setGroup(group); // Устанавливаем группу

        student.setAverageScore(averageScore);
        studentRepository.save(student);

        return "redirect:/students"; // Перенаправление обратно на список студентов
    }

    @PostMapping("/students/delete")
    public String deleteStudent(@RequestParam Long id) {
        studentRepository.deleteById(id); // Удаление студента по ID
        return "redirect:/students"; // Перенаправление на страницу со списком студентов
    }

    @GetMapping("/students/edit")
    public String editStudentForm(@RequestParam Long id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);  // Добавляем студента в модель
        model.addAttribute("groups", studentGroupRepository.findAll());  // Передаем все группы
        return "edit-student";  // Переход на страницу редактирования
    }

    @PostMapping("/students/update")
    public String updateStudent(@RequestParam Long id,
                                @RequestParam String name,
                                @RequestParam Long groupId,
                                @RequestParam double averageScore) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));

        student.setName(name);

        // Находим группу по ID
        var group = studentGroupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id:" + groupId));
        student.setGroup(group); // Устанавливаем группу

        student.setAverageScore(averageScore);
        studentRepository.save(student);

        return "redirect:/students"; // Перенаправление обратно на страницу со списком студентов
    }
}

