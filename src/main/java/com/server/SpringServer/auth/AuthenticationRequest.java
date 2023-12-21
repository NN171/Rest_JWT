package com.server.SpringServer.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

//Класс получает запрос из мобильного приложения во время авторизации
public class AuthenticationRequest {
    private int studentId;
    private String password;
}
