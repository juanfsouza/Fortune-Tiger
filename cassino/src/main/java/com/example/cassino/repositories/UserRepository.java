package com.example.cassino.repositories;

import com.example.cassino.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByConfirmationToken(String confirmationToken); // Adiciona este método
}
