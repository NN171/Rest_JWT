package com.server.SpringServer.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity  //Обозначение класса, как сущность
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "users")  //Обращение к таблице users
public class Student implements UserDetails {
    @Id  //Указание переменной, в качестве id  в таблице
    @GeneratedValue  //Равносильно primary key в таблице
    private Long id;
    @Column(unique = true)  //Столбец, который хранит уникальные значения
    private int studentId;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return String.valueOf(studentId);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
