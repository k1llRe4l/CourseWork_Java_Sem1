package com.example.curse.repository;

import com.example.curse.model.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с группами студентов.
 */
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {
}
