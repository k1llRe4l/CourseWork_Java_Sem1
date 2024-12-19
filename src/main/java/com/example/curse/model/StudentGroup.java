package com.example.curse.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Entity
@Data
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName; // Название группы

    @OneToMany(mappedBy = "group") // Связь с таблицей студентов
    private List<Student> students;

    @OneToMany(mappedBy = "group") // Связь с таблицей расписания
    private List<Schedule> schedules;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}

