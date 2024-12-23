package com.example.curse.repository;

import com.example.curse.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы со студентами.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
