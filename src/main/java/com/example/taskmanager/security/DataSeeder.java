package com.example.taskmanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.taskmanager.dao.UserRepo;
import com.example.taskmanager.entity.*;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepo.findByEmail("admin@gmail.com").isEmpty()) {
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRole(Role.ADMIN);
            userRepo.save(admin);
            System.out.println("✅ Default Admin Created! Email: admin@gmail.com | Password: 1234");
        } else {
            System.out.println("⚡ Admin already exists!");
        }
    }
}
