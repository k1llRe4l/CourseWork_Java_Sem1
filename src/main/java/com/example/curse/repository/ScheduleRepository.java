package com.example.curse.repository;

import com.example.curse.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByDate(LocalDate date);

    List<Schedule> findByGroup_Id(Long groupId);

    List<Schedule> findByDateAndGroup_Id(LocalDate date, Long groupId);
}



