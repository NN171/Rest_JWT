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
    Student setStudents(Student student);
    void deleteStudent(String studentId);
    Student updateStudent(Student student);
    Student findByStudentId(String studentId);

    Optional<AuthenticationResponse> register(RegisterRequest request);
}
