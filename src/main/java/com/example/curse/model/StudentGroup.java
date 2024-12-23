package com.example.curse.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


/**
 * Сущность "Группа студентов", представляющая данные о студенческой группе.
 * Хранит информацию о названии группы, списке студентов и расписании занятий.
 */
@Entity
@Data
public class StudentGroup {

    /**
     * Уникальный идентификатор группы.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название группы.
     */
    private String groupName;

    /**
     * Список студентов, принадлежащих к этой группе.
     */
    @OneToMany(mappedBy = "group")
    private List<Student> students;

    /**
     * Список занятий, связанных с этой группой.
     */
    @OneToMany(mappedBy = "group")
    private List<Schedule> schedules;

    /**
     * Возвращает уникальный идентификатор группы.
     *
     * @return уникальный идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор группы.
     *
     * @param id уникальный идентификатор
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает название группы.
     *
     * @return название группы
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Устанавливает название группы.
     *
     * @param groupName название группы
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Возвращает список студентов, принадлежащих к этой группе.
     *
     * @return список студентов
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Устанавливает список студентов, принадлежащих к этой группе.
     *
     * @param students список студентов
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Возвращает список расписаний занятий, связанных с этой группой.
     *
     * @return список расписаний
     */
    public List<Schedule> getSchedules() {
        return schedules;
    }

    /**
     * Устанавливает список расписаний занятий, связанных с этой группой.
     *
     * @param schedules список расписаний
     */
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}