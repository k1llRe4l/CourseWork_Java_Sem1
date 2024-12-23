package com.example.curse.repository;

import com.example.curse.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

/**
 * Репозиторий для работы с расписанием.
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     * Находит все записи расписания для указанной даты.
     *
     * @param date дата для поиска записей расписания
     * @return список записей расписания для указанной даты
     */
    List<Schedule> findByDate(LocalDate date);

    /**
     * Находит все записи расписания для указанной группы.
     *
     * @param groupId идентификатор группы для поиска записей расписания
     * @return список записей расписания для указанной группы
     */
    List<Schedule> findByGroup_Id(Long groupId);

    /**
     * Находит все записи расписания для указанной даты и группы.
     *
     * @param date дата для поиска записей расписания
     * @param groupId идентификатор группы для поиска записей расписания
     * @return список записей расписания для указанной даты и группы
     */
    List<Schedule> findByDateAndGroup_Id(LocalDate date, Long groupId);
}

