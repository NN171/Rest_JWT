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
public class AuthenticationService {

    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = Student.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
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
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
