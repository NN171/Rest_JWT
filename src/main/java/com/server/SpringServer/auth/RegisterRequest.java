package com.server.SpringServer.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //Автоматическое создание геттеров и сттеров для полей
@Builder  //Автоматическое создание экземпляра класса
@AllArgsConstructor  //Создание конструктора со всеми аргументами
@NoArgsConstructor  //Создание конструктора без параметров

//Класс получает запрос из мобильного приложения во время авторизации
public class RegisterRequest {

    private int studentId;
    private String email;
    private String password;
}
