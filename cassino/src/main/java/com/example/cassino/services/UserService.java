package com.example.cassino.services;

import com.example.cassino.models.User;
import com.example.cassino.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User createUser(User user) {
        user.setBalance(new BigDecimal("1000.00"));
        String confirmationToken = generateConfirmationToken();
        user.setConfirmationToken(confirmationToken);
        return userRepository.save(user);
    }

    public boolean confirmUser(String token) {
        User user = userRepository.findByConfirmationToken(token);
        if (user != null) {
            user.setConfirmed(true);
            user.setConfirmationToken(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private String generateConfirmationToken() {
        return UUID.randomUUID().toString();
    }
}
