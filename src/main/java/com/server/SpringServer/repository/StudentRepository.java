package com.server.SpringServer.repository;

import com.server.SpringServer.data.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository  // Интерфейс предоставляет механизм управления бд
public interface StudentRepository extends JpaRepository<Student, Long> {  //Интерфейс позволяет использовать основные методы для работы с сущностью

    Optional<Student> findByStudentId(int studentId);
    void deleteByStudentId(int studentId);
    Student findStudentByStudentId(int studentId);
    boolean existsByStudentId(int studentId);
}
