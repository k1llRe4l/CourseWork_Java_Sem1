package com.example.curse.controller;

import com.example.curse.model.Student;
import com.example.curse.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        return repository.findById(id).map(student -> {
            student.setName(studentDetails.getName());
            student.setGroup(studentDetails.getGroup());
            student.setAverageScore(studentDetails.getAverageScore());
            return ResponseEntity.ok(repository.save(student));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return repository.findById(id).map(student -> {
            repository.delete(student);
            return ResponseEntity.ok("Student deleted successfully.");
        }).orElse(ResponseEntity.status(404).body("Student not found."));
    }

}
