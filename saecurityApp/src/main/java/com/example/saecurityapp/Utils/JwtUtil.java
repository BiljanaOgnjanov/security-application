package com.example.saecurityapp.Utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.saecurityapp.User.model.User;
import com.example.saecurityapp.Role.*;
import com.example.saecurityapp.User.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class JwtUtil {
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    @Autowired
    private UserService userService;


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return createToken(claims, userDetails);
    }

    private String createToken(Map<String, Object> claims, UserDetails userDetails) {
        User user = userService.getUserBy(userDetails.getUsername());
        return JWT.create().withSubject(userDetails.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 30)).
                withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .sign(Algorithm.HMAC512(JwtUtil.SECRET.getBytes()));
    }

    public static void setResponseMessage(HttpServletResponse response, String messageName, String messageText)
            throws IOException {
        Map<String, String> responseObject = new HashMap<>();
        responseObject.put(messageName, messageText);
        new ObjectMapper().writeValue(response.getOutputStream(), responseObject);
    }


    public String extractUsername(String token) {
        try {
            DecodedJWT decodedJWT = getVerifier().verify(token);
            return decodedJWT.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public static JWTVerifier getVerifier() {
        return JWT.require(getAlgorithm()).build();
    }

    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC512("secret".getBytes());
    }
}
