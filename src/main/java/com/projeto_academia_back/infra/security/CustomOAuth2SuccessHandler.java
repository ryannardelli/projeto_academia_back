package com.projeto_academia_back.infra.security;

import com.projeto_academia_back.enums.RoleUser;
import com.projeto_academia_back.models.Users;
import com.projeto_academia_back.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public CustomOAuth2SuccessHandler(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String firstName = (String) attributes.get("given_name");
        String lastName = (String) attributes.get("family_name");
        String picture = (String) attributes.get("picture");

        // Verifica se já existe usuário no banco
        Users user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    // Se não existir, cria novo
                    Users newUser = new Users();
                    newUser.setEmail(email);
                    newUser.setFirstName(firstName);
                    newUser.setLastName(lastName);
                    newUser.setPassword(""); // sem senha
                    newUser.setRoleUser(RoleUser.Aluno);
                    newUser.setPicture(picture);
                    return userRepository.save(newUser);
                });

        // Gera JWT
        String token = tokenService.generateToken(user);

        // Define cookie com JWT
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 2); // 2 horas
        response.addCookie(cookie);

        // Redireciona
        response.sendRedirect("http://localhost:3000/dashboard");
    }
}