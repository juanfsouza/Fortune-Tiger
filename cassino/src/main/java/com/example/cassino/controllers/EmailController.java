package com.example.cassino.controllers;

import com.example.cassino.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-confirmation-email")
    public ResponseEntity<String> sendConfirmationEmail(@RequestParam String email) {
        try {
            String confirmationCode = generateConfirmationCode();
            String confirmationLink = "http://localhost:3000/confirm?token=" + confirmationCode;
            emailService.sendEmail(email, "Confirm Your Registration", "Click the link to confirm your registration: " + confirmationLink);
            return new ResponseEntity<>("Email sent", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error sending email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String generateConfirmationCode() {
        Random random = new Random();
        int code = random.nextInt(999999);
        return String.format("%06d", code);
    }
}
