package com.server.SpringServer.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

//Класс отправляет в мобильное приложение токен
public class AuthenticationResponse {

    private String token;
}
