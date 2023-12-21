package com.server.SpringServer.repository;

import com.server.SpringServer.data.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentId(int studentId);
    void deleteByStudentId(int studentId);
    Student findStudentByStudentId(int studentId);
    boolean existsByStudentId(int studentId);
}
