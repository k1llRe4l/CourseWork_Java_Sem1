package com.example.curse.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false) // Связь с таблицей групп
    private StudentGroup group; // Ссылка на группу студента

    @Column
    private double averageScore;

    // Пустой конструктор (обязателен для Hibernate)
    public Student() {
    }
    // Конструктор с параметрами
    public Student(String name, StudentGroup group, double averageScore) {
        this.name = name;
        this.group = group;
        this.averageScore = averageScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudentGroup getGroup() {
        return group;
    }

    public void setGroup(StudentGroup group) {
        this.group = group;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}
