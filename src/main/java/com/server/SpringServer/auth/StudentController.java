package com.server.SpringServer.auth;

import com.server.SpringServer.data.Student;
import com.server.SpringServer.service.AuthenticationService;
import com.server.SpringServer.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //Аннотация отвечает за обработку Http реквестов и преобразование в формат json
@AllArgsConstructor
@RequestMapping(path = "/api/v1/student")  //Задается API
public class StudentController {  //Класс, где реализуются CRUD операции

    private final StudentService studentService;
    private final AuthenticationService authenticationService;

    @GetMapping("/studentList") //Read - чтение данных
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/register")  //Create - регистрация пользователя
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        if (studentService.existsByStudentId(request.getStudentId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthenticationResponse());
        }
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")  //Create - авторизация пользователя
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @DeleteMapping ("/studentDelete/{studentId}")  //Delete - удаление пользователя
    public void deleteStudent(@PathVariable int studentId){
        studentService.deleteStudent(studentId);
    }

    @GetMapping("/{studentId}")  //Read - поиск пользователя, получение данных
    public Student findByStudentId(@PathVariable int studentId){
        return studentService.findByStudentId(studentId);
    }

    @PutMapping("/studentUpdate")  //Update - обновление данных пользователя
    public Student updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }
}
