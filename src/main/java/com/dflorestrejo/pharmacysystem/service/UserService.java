package com.dflorestrejo.pharmacysystem.service;

import com.dflorestrejo.pharmacysystem.entity.User;
import com.dflorestrejo.pharmacysystem.enums.EmployeeRole;

public interface UserService {

    User login(String username, String rawPassword);

    User register(String username, String rawPassword, EmployeeRole role);
}
