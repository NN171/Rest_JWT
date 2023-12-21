package com.server.SpringServer.config;

import com.server.SpringServer.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //Класс - конфигурация для настройки авторизации и регистрации
@RequiredArgsConstructor //Создание конструктора с обязательными аргументами
public class AppConfig {
    @Autowired  //Автоматическое связывание компонентов
    private final StudentRepository repository;
    @Bean  //Создание или использование бина в других классах
    public UserDetailsService userDetailsService() {    //Метод для получения информации о пользователе по юзернейму
        return username -> repository.findByStudentId(Integer.parseInt(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {   //Метод для проверки введенных данных юзера
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {  //Метод для приема запросов на аутентификацию
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {  //Метод для создания и использования хэширования пароля
        return new BCryptPasswordEncoder();
    }
}
