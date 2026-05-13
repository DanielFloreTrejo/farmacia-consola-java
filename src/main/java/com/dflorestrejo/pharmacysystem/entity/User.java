package com.dflorestrejo.pharmacysystem.entity;

import com.dflorestrejo.pharmacysystem.enums.EmployeeRole;

import java.time.LocalDateTime;

public class User {

    private int id;
    private String username;
    private String passwordHash;
    private EmployeeRole employeeRole;
    private boolean active;
    private LocalDateTime createdAt;

    public User() {
    }

    public User(String username, String passwordHash, EmployeeRole employeeRole, boolean active) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.employeeRole = employeeRole;
        this.active = active;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
