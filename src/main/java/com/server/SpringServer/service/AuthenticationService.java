package com.server.SpringServer.service;

import com.server.SpringServer.auth.AuthenticationRequest;
import com.server.SpringServer.auth.AuthenticationResponse;
import com.server.SpringServer.auth.RegisterRequest;
import com.server.SpringServer.config.JwtService;
import com.server.SpringServer.data.Role;
import com.server.SpringServer.data.Student;
import com.server.SpringServer.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {  //Авторизация и регистрация пользователя, установка значений

    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = Student.builder()
                .studentId(request.getStudentId())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        String.valueOf(request.getStudentId()),
                        request.getPassword()
                )
        );
        var user = repository.findByStudentId(request.getStudentId()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
