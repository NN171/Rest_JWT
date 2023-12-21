package com.server.SpringServer.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {  //Класс для работы с jwt

    private static final String SECRET_KEY = "26fe1746b40acf3f263de2736060b6dceeafb8e0b140de23d9f59dbf11764e41";
    public int extractUsername(String token) {  //Извлекает id студента из токена
        return Integer.parseInt(extractClaim(token, Claims::getSubject, Claims::getSubject));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver, Function<Claims, T> fallbackResolver){  //Извлечение утверждения и обработка случая, когда утверждения нет
        final Claims claims = extractAllClaims(token);
        T extractedClaim = claimsResolver.apply(claims);
        return (extractedClaim != null) ? extractedClaim : fallbackResolver.apply(claims);
    }


    public String generateToken(UserDetails userDetails){  //Генерация токена, используя данные пользователя
        return generateToken(new HashMap<>(), userDetails);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){  //Создает jwt
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(String.valueOf(userDetails.getUsername()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){  //Проверка срока и валидности токена
        final int username = extractUsername(token);
        return (String.valueOf(username).equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {  //Проверка, не истек ли токен
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {  //Извлекает срок действия токена
        return extractClaim(token, Claims::getExpiration, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {  //Верификация подписи и извлечение утверждений(например, срок действия jwt, роль, номер студака)
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {  //Передача ключа методом hmac
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
