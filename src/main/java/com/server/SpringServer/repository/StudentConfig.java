package com.server.SpringServer.repository;

import com.server.SpringServer.data.Student;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Getter
@Repository
public class StudentConfig {
    private final List<Student> students = new ArrayList<>();

    public Student setStudents(Student student){
        students.add(student);
        return student;
    }

    public Student findByStudentId(String studentId){
        return students.stream()
                .filter(el -> el.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    public Student updateStudents(Student student){
        var studentIndex = IntStream.range(0, students.size())
                .filter(id -> students.get(id).getStudentId().equals(student.getStudentId()))
                .findFirst()
                .orElse(-1);
        if (studentIndex > -1){
            students.set(studentIndex, student);
        }
        return null;
    }

    public void deleteStudents(String studentId) {
        var student = findByStudentId(studentId);
        if (student != null)
            students.remove(student);
    }
}
