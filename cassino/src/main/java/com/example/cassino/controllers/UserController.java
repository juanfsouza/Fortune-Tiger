package com.example.cassino.controllers;

import com.example.cassino.models.User;
import com.example.cassino.services.EmailService;
import com.example.cassino.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // Verifica se o e-mail já está registrado
        if (userService.emailExists(user.getEmail())) {
            return new ResponseEntity<>("Email already in use", HttpStatus.BAD_REQUEST);
        }

        // Cria o usuário e gera um link de confirmação
        User newUser = userService.createUser(user);
        String confirmationLink = "http://localhost:3000/confirm?token=" + newUser.getConfirmationToken();

        // Envia o e-mail de confirmação
        try {
            emailService.sendEmail(user.getEmail(), "Confirm Your Registration", "Click the link to confirm your registration: " + confirmationLink);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send confirmation email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam String token) {
        // Verifique o token e confirme o registro
        boolean confirmed = userService.confirmUser(token);

        if (confirmed) {
            return new ResponseEntity<>("Registration confirmed", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
        }
    }
}
