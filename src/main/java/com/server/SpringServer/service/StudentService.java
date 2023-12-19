package com.server.SpringServer.service;

import com.server.SpringServer.auth.AuthenticationResponse;
import com.server.SpringServer.auth.RegisterRequest;
import com.server.SpringServer.data.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {
    List<Student> getStudents();
    void deleteStudent(int studentId);
    Student updateStudent(Student student);
    Student findByStudentId(int studentId);

    Optional<AuthenticationResponse> register(RegisterRequest request);
}
