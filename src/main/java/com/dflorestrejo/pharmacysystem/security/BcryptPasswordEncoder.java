package com.dflorestrejo.pharmacysystem.security;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptPasswordEncoder implements PasswordEncoder {

    private static final int COST = 10;

    @Override
    public String encode(String rawPassword) {

        if (rawPassword == null || rawPassword.isBlank()) {
            throw new RuntimeException("Ingrese una contraseña");
        }

        // gensalt(COST) genera una "sal" aleatoria — hace que dos passwords
        // iguales produzcan hashes distintos
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(COST));
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {

        if (rawPassword.isBlank() || encodedPassword.isBlank()) {
            return false;
        }

        try {
            return BCrypt.checkpw(rawPassword, encodedPassword);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
