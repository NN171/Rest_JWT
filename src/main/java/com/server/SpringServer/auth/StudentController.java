package com.server.SpringServer.auth;

import com.server.SpringServer.data.Student;
import com.server.SpringServer.service.AuthenticationService;
import com.server.SpringServer.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/student")
public class StudentController {

    private final StudentService studentService;
    private final AuthenticationService authenticationService;

    @GetMapping("/studentList")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @DeleteMapping ("/studentDelete/{studentId}")
    public void deleteStudent(@PathVariable int studentId){
        studentService.deleteStudent(studentId);
    }

    @GetMapping("/{studentId}")
    public Student findByStudentId(@PathVariable int studentId){
        return studentService.findByStudentId(studentId);
    }

    @PutMapping("/studentUpdate")
    public Student updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }
}
