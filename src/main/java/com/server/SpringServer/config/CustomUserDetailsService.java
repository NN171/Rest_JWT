package com.server.SpringServer.config;

import com.server.SpringServer.data.Student;
import com.server.SpringServer.service.StudentServiceRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service  //Класс реализует бизнес-логику
public class CustomUserDetailsService implements UserDetailsService {
    private StudentServiceRequest studentService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   //Переопределение метода для использования юзернейма типа int
        try {
            int studentId = Integer.parseInt(username);
            Student student = studentService.findByStudentId(studentId);

            if (student == null) {
                throw new UsernameNotFoundException("Пользователь с ID " + username + " не найден");
            }

            return new User(
                    String.valueOf(student.getStudentId()),
                    student.getPassword(),
                    new ArrayList<>()
            );
        }
        catch (NumberFormatException e){
            throw new UsernameNotFoundException("Неверный формат");
        }
    }
}
