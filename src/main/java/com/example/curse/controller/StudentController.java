package com.example.curse.controller;

import com.example.curse.model.Student;
import com.example.curse.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-контроллер для управления данными студентов.
 * Предоставляет CRUD-операции через API.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository repository;

    /**
     * Конструктор контроллера.
     *
     * @param repository репозиторий для работы с сущностью {@link Student}
     */
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    /**
     * Возвращает список всех студентов.
     *
     * @return список объектов {@link Student}
     */
    @GetMapping
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    /**
     * Создаёт нового студента.
     *
     * @param student объект {@link Student}, переданный в теле запроса
     * @return сохранённый объект {@link Student}
     */
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    /**
     * Обновляет данные студента по его ID.
     *
     * @param id             идентификатор студента
     * @param studentDetails объект {@link Student} с новыми данными
     * @return обновлённый объект {@link Student} или статус 404, если студент не найден
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        return repository.findById(id).map(student -> {
            student.setName(studentDetails.getName());
            student.setGroup(studentDetails.getGroup());
            student.setAverageScore(studentDetails.getAverageScore());
            return ResponseEntity.ok(repository.save(student));
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Удаляет студента по его ID.
     *
     * @param id идентификатор студента
     * @return сообщение об успешном удалении или ошибка 404, если студент не найден
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return repository.findById(id).map(student -> {
            repository.delete(student);
            return ResponseEntity.ok("Student deleted successfully.");
        }).orElse(ResponseEntity.status(404).body("Student not found."));
    }
}
