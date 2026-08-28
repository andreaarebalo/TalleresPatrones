package com.streaming.user.service;

import com.streaming.user.dto.LoginRequest;
import com.streaming.user.repository.UserRepository;
import com.streaming.user.singleton.LoginManager;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean iniciarSesion(LoginRequest request) {

        LoginManager loginManager = LoginManager.getInstance();

        return loginManager.validarLogin(
                request.getEmail(),
                request.getPassword(),
                userRepository
        );
    }
}