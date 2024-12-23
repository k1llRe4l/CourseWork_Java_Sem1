package com.example.curse.model;

import jakarta.persistence.*;
import lombok.Data;


/**
 * Сущность "Студент", представляющая данные о студенте.
 * Содержит информацию об имени, группе и среднем балле.
 */

@Entity
@Data
public class Student {

    /**
     * Уникальный идентификатор студента.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Имя студента.
     */
    @Column
    private String name;

    /**
     * Ссылка на группу, к которой относится студент.
     */
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private StudentGroup group;

    /**
     * Средний балл студента.
     */
    @Column
    private double averageScore;

    /**
     * Пустой конструктор (обязателен для Hibernate).
     */
    public Student() {
    }

    /**
     * Конструктор с параметрами для создания нового объекта студента.
     *
     * @param name          имя студента
     * @param group         группа, к которой относится студент
     * @param averageScore  средний балл студента
     */
    public Student(String name, StudentGroup group, double averageScore) {
        this.name = name;
        this.group = group;
        this.averageScore = averageScore;
    }

    /**
     * Возвращает уникальный идентификатор студента.
     *
     * @return уникальный идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор студента.
     *
     * @param id уникальный идентификатор
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает имя студента.
     *
     * @return имя студента
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя студента.
     *
     * @param name имя студента
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает группу студента.
     *
     * @return группа студента
     */
    public StudentGroup getGroup() {
        return group;
    }

    /**
     * Устанавливает группу студента.
     *
     * @param group группа студента
     */
    public void setGroup(StudentGroup group) {
        this.group = group;
    }

    /**
     * Возвращает средний балл студента.
     *
     * @return средний балл
     */
    public double getAverageScore() {
        return averageScore;
    }

    /**
     * Устанавливает средний балл студента.
     *
     * @param averageScore средний балл
     */
    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}

