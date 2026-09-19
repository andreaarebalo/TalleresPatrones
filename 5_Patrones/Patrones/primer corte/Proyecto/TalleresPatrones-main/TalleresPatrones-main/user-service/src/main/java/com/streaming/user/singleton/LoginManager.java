package com.streaming.user.singleton;

import com.streaming.user.entity.User;
import com.streaming.user.repository.UserRepository;

import java.util.Optional;

public class LoginManager {

    private static LoginManager instance;

    private LoginManager() {
    }

    public static LoginManager getInstance() {

        if (instance == null) {
            instance = new LoginManager();
        }

        return instance;
    }

    public boolean validarLogin(String email, String password, UserRepository userRepository) {

        Optional<User> usuario = userRepository.findByEmail(email);

        if (usuario.isPresent()) {
            return usuario.get().getPassword().equals(password);
        }

        return false;
    }
}