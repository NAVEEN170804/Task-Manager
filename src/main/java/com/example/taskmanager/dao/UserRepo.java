package com.example.taskmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanager.entity.User;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
