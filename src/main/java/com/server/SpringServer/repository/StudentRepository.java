package com.server.SpringServer.repository;

import com.server.SpringServer.data.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentId(int studentId);
    void deleteByStudentId(int studentId);
    Student findStudentByStudentId(int studentId);
}
