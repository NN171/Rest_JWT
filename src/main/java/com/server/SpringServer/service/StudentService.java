package com.server.SpringServer.service;

import com.server.SpringServer.data.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {  //Сервис - интерфейс для работы с данными студента
    List<Student> getStudents();
    void deleteStudent(int studentId);
    Student updateStudent(Student student);
    Student findByStudentId(int studentId);
    boolean existsByStudentId(int studentId);
}
