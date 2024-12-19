package com.example.curse;

import com.example.curse.model.Schedule;
import com.example.curse.model.Student;
import com.example.curse.model.StudentGroup;
import com.example.curse.model.User;
import com.example.curse.repository.ScheduleRepository;
import com.example.curse.repository.StudentGroupRepository;
import com.example.curse.repository.StudentRepository;
import com.example.curse.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class InitDatabase {

    @Bean
    CommandLineRunner init(UserRepository userRepository,
                           StudentRepository studentRepository,
                           StudentGroupRepository groupRepo,
                           ScheduleRepository scheduleRepo,
                           BCryptPasswordEncoder encoder) {
        return args -> {
            // Создаём пользователей
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPasswordHash(encoder.encode("admin")); // Пароль: admin
                admin.setRole(User.Role.ADMIN);
                userRepository.save(admin);
                System.out.println("Admin user created.");
            }

            if (userRepository.findByUsername("student").isEmpty()) {
                User studentUser = new User();
                studentUser.setUsername("student");
                studentUser.setPasswordHash(encoder.encode("student")); // Пароль: student
                studentUser.setRole(User.Role.STUDENT);
                userRepository.save(studentUser);
                System.out.println("Student user created.");
            }

            // Создаём учебные группы, если их ещё нет
            if (groupRepo.count() == 0) {
                StudentGroup groupA = new StudentGroup();
                groupA.setGroupName("Группа А");
                groupRepo.save(groupA);

                StudentGroup groupB = new StudentGroup();
                groupB.setGroupName("Группа Б");
                groupRepo.save(groupB);

                System.out.println("Student groups created.");

                // Создаём студентов
                studentRepository.save(new Student("Иванова Алиса Михайловна", groupA, 85.7));
                studentRepository.save(new Student("Сидоров Борис Викторович", groupB, 75.3));

                System.out.println("Students created.");

                // Создаём расписание
                scheduleRepo.save(new Schedule(LocalDate.of(2024, 12, 20), LocalTime.of(10, 0), "Математика", groupA));
                scheduleRepo.save(new Schedule(LocalDate.of(2024, 12, 21), LocalTime.of(14, 0), "Физика", groupB));

                System.out.println("Schedule created.");
            }
        };
    }
}


