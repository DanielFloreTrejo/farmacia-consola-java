package com.dflorestrejo.pharmacysystem.service;

import com.dflorestrejo.pharmacysystem.entity.User;
import com.dflorestrejo.pharmacysystem.enums.EmployeeRole;
import com.dflorestrejo.pharmacysystem.repository.UserRepository;
import com.dflorestrejo.pharmacysystem.security.PasswordEncoder;

public class UserServiceImpl implements UserService {

    // Depende de interfaces, no de clases concretas
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String username, String rawPassword) {

        if (username.isBlank()) {
            throw new RuntimeException("Ingrese un nombre de usuario");
        }

        if (rawPassword.isBlank()) {
            throw new RuntimeException("Ingrese una contraseña");
        }

        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("El nombre de usuario o contraseña no son correctas"));

        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new RuntimeException("El nombre de usuario o contraseña no son correctas");
        }

        return user;
    }

    @Override
    public User register(String username, String rawPassword, EmployeeRole role) {

        if (username.isBlank()) {
            throw new RuntimeException("Ingrese un nombre de usuario");
        }

        if (rawPassword.isBlank()) {
            throw new RuntimeException("Ingrese una contraseña");
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("El usuario " + username + " ya esta registrado");
        }

        String passwordHash = passwordEncoder.encode(rawPassword);
        User user = new User(username, passwordHash, role, true);

        return userRepository.save(user);
    }
}
