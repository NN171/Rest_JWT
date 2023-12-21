package com.server.SpringServer.service;

import com.server.SpringServer.data.Student;
import com.server.SpringServer.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean existsByStudentId(int studentId) {
        return studentRepository.existsByStudentId(studentId);
    }
}
