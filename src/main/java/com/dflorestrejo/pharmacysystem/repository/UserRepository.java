package com.dflorestrejo.pharmacysystem.repository;

import com.dflorestrejo.pharmacysystem.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(int userId);

    Optional<User> findByUsername(String username);

    User save(User user);
}
