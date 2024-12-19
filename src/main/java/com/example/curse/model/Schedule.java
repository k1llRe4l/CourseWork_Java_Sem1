package com.example.curse.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;  // Дата занятия
    private LocalTime time;  // Время занятия
    private String subject;  // Название предмета

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private StudentGroup group;  // Ссылка на группу студентов

    // Пустой конструктор для Hibernate
    public Schedule() {
    }

    // Конструктор с параметрами
    public Schedule(LocalDate date, LocalTime time, String subject, StudentGroup group) {
        this.date = date;
        this.time = time;
        this.subject = subject;
        this.group = group;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public StudentGroup getGroup() {
        return group;
    }

    public void setGroup(StudentGroup group) {
        this.group = group;
    }
}






