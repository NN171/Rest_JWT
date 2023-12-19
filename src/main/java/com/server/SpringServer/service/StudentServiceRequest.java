package com.server.SpringServer.service;

import com.server.SpringServer.auth.AuthenticationResponse;
import com.server.SpringServer.auth.RegisterRequest;
import com.server.SpringServer.data.Student;
import com.server.SpringServer.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceRequest implements StudentService{

    private final StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(int studentId) {
        studentRepository.deleteByStudentId(studentId);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findByStudentId(int studentId) {
        return studentRepository.findStudentByStudentId(studentId);
    }

    @Override
    public Optional<AuthenticationResponse> register(RegisterRequest request) {
        return Optional.empty();
    }
}
