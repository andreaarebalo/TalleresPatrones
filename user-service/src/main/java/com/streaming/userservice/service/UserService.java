package com.streaming.userservice.service;

import com.streaming.userservice.model.User;
import com.streaming.userservice.repository.UserRepository;
import com.streaming.userservice.util.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            LoginManager.getInstance().registerLogin(username);
            return true;
        }
        return false;
    }
}
