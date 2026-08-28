package com.streaming.user.controller;

import com.streaming.user.dto.LoginRequest;
import com.streaming.user.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final LoginService loginService;

    public UserController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {

        boolean autenticado = loginService.iniciarSesion(request);

        if (autenticado) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        }

        return ResponseEntity.status(401).body("Correo o contraseña incorrectos");
    }
}