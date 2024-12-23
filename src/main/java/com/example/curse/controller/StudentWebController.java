package com.example.curse.controller;

import com.example.curse.model.Student;
import com.example.curse.repository.StudentRepository;
import com.example.curse.repository.StudentGroupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Веб-контроллер для управления студентами через пользовательский интерфейс.
 * Позволяет отображать список студентов, добавлять, редактировать и удалять записи.
 */
@Controller
public class StudentWebController {

    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository;

    /**
     * Конструктор контроллера.
     *
     * @param studentRepository        репозиторий для работы с сущностью {@link Student}
     * @param studentGroupRepository   репозиторий для работы с сущностью {@link com.example.curse.model.StudentGroup}
     */
    public StudentWebController(StudentRepository studentRepository,
                                StudentGroupRepository studentGroupRepository) {
        this.studentRepository = studentRepository;
        this.studentGroupRepository = studentGroupRepository;
    }

    /**
     * Отображает страницу со списком студентов.
     *
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя HTML-шаблона "students"
     */
    @GetMapping("/students")
    public String getStudentsPage(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "students";
    }

    /**
     * Отображает форму для добавления нового студента.
     *
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя HTML-шаблона "add-student"
     */
    @GetMapping("/students/add")
    public String addStudentForm(Model model) {
        model.addAttribute("groups", studentGroupRepository.findAll());
        return "add-student";
    }

    /**
     * Обрабатывает сохранение нового студента.
     *
     * @param name         имя студента
     * @param groupId      идентификатор группы
     * @param averageScore средний балл студента
     * @return перенаправление на страницу со списком студентов
     */
    @PostMapping("/students/save")
    public String saveStudent(@RequestParam String name,
                              @RequestParam Long groupId,
                              @RequestParam double averageScore) {
        Student student = new Student();
        student.setName(name);

        var group = studentGroupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id:" + groupId));
        student.setGroup(group);
        student.setAverageScore(averageScore);
        studentRepository.save(student);

        return "redirect:/students";
    }

    /**
     * Удаляет студента по его ID.
     *
     * @param id идентификатор студента
     * @return перенаправление на страницу со списком студентов
     */
    @PostMapping("/students/delete")
    public String deleteStudent(@RequestParam Long id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }

    /**
     * Отображает форму для редактирования информации о студенте.
     *
     * @param id    идентификатор студента
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя HTML-шаблона "edit-student"
     */
    @GetMapping("/students/edit")
    public String editStudentForm(@RequestParam Long id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        model.addAttribute("groups", studentGroupRepository.findAll());
        return "edit-student";
    }

    /**
     * Обновляет информацию о студенте.
     *
     * @param id           идентификатор студента
     * @param name         новое имя студента
     * @param groupId      новый идентификатор группы
     * @param averageScore новый средний балл
     * @return перенаправление на страницу со списком студентов
     */
    @PostMapping("/students/update")
    public String updateStudent(@RequestParam Long id,
                                @RequestParam String name,
                                @RequestParam Long groupId,
                                @RequestParam double averageScore) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));

        student.setName(name);

        var group = studentGroupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id:" + groupId));
        student.setGroup(group);
        student.setAverageScore(averageScore);
        studentRepository.save(student);

        return "redirect:/students";
    }
}
