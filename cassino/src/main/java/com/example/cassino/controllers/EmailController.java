package com.example.cassino.controllers;

import com.example.cassino.services.EmailService;
import com.mailjet.client.errors.MailjetException;
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
            emailService.sendEmail(email, "Confirmation Code", "Your confirmation code is: " + confirmationCode);
            return new ResponseEntity<>("Email sent", HttpStatus.OK);
        } catch (MailjetException e) {
            return new ResponseEntity<>("Error sending email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String generateConfirmationCode() {
        Random random = new Random();
        int code = random.nextInt(999999);
        return String.format("%06d", code);
    }
}
