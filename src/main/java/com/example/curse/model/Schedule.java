package com.example.curse.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Сущность "Расписание", представляющая запись о занятии.
 * Хранит информацию о дате, времени, предмете и группе студентов.
 */
@Entity
public class Schedule {

    /**
     * Уникальный идентификатор записи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Дата проведения занятия.
     */
    private LocalDate date;

    /**
     * Время проведения занятия.
     */
    private LocalTime time;

    /**
     * Название предмета.
     */
    private String subject;

    /**
     * Ссылка на группу студентов, для которой проводится занятие.
     */
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private StudentGroup group;

    /**
     * Пустой конструктор для Hibernate.
     */
    public Schedule() {
    }

    /**
     * Конструктор с параметрами для создания нового объекта расписания.
     *
     * @param date    дата проведения занятия
     * @param time    время проведения занятия
     * @param subject название предмета
     * @param group   группа студентов
     */
    public Schedule(LocalDate date, LocalTime time, String subject, StudentGroup group) {
        this.date = date;
        this.time = time;
        this.subject = subject;
        this.group = group;
    }

    /**
     * Возвращает уникальный идентификатор записи.
     *
     * @return уникальный идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор записи.
     *
     * @param id уникальный идентификатор
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает дату проведения занятия.
     *
     * @return дата занятия
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Устанавливает дату проведения занятия.
     *
     * @param date дата занятия
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Возвращает время проведения занятия.
     *
     * @return время занятия
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Устанавливает время проведения занятия.
     *
     * @param time время занятия
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Возвращает название предмета.
     *
     * @return название предмета
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Устанавливает название предмета.
     *
     * @param subject название предмета
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Возвращает группу студентов, для которой проводится занятие.
     *
     * @return группа студентов
     */
    public StudentGroup getGroup() {
        return group;
    }

    /**
     * Устанавливает группу студентов, для которой проводится занятие.
     *
     * @param group группа студентов
     */
    public void setGroup(StudentGroup group) {
        this.group = group;
    }
}
