package com.projejo_academia_back.controllers;

import com.projejo_academia_back.dtos.AuthenticateDto;
import com.projejo_academia_back.dtos.LoginResponseDto;
import com.projejo_academia_back.dtos.RegisterDto;
import com.projejo_academia_back.infra.security.TokenService;
import com.projejo_academia_back.models.Users;
import com.projejo_academia_back.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
            // Usuário não autenticado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }
        return ResponseEntity.ok(user.getAttributes());
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticateDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto data) {
        if (userRepository.findByEmail(data.email()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("E-mail já está em uso");
        }

        if (!isPasswordValid(data.password())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Sua senha precisa ter pelo menos 8 letras ou números, com uma letra maiúscula, uma minúscula e um número.");
        }


        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Users newUser = new Users(
                data.email(),
                data.firstName(),
                data.lastName(),
                encryptedPassword,
                data.role(),
                data.phone()
        );

        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso");
    }

    private boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
    }

}
