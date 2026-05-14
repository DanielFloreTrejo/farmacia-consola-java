package com.dflorestrejo.pharmacysystem.repository;

import com.dflorestrejo.pharmacysystem.config.DatabaseConnection;
import com.dflorestrejo.pharmacysystem.entity.User;
import com.dflorestrejo.pharmacysystem.enums.EmployeeRole;

import java.sql.*;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findById(int userId) {
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT id, username, password_hash, employee_role, " +
                    "active, created_at FROM users WHERE id = ?")) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = mapRow(rs);
                return Optional.of(user);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el id del usuario " + e.getMessage());
        }

    }

    @Override
    public Optional<User> findByUsername(String username) {
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT id, username, password_hash, employee_role, " +
                "active, created_at FROM users WHERE username = ?")) {

            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = mapRow(rs);
                return Optional.of(user);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el nombre del usuario " + e.getMessage());
        }
    }

    @Override
    public User save(User user) {

        String sql = "INSERT INTO users(username, password_hash, employee_role, active, created_at) " +
                "VALUES(?, ?, ?, ?, ?)";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            // RETURN_GENERATED_KEYS recupera el id asignado por AUTO_INCREMENT
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1,user.getUsername());
            stmt.setString(2,user.getPasswordHash());
            stmt.setString(3,user.getEmployeeRole().name()); // enum → String
            stmt.setBoolean(4,user.isActive());
            stmt.setTimestamp(5,Timestamp.valueOf(user.getCreatedAt()));

            stmt.executeUpdate();

            // Recupera el id generado por MySQL y se lo asigna al objeto
            ResultSet key = stmt.getGeneratedKeys();
            if (key.next()) {
                user.setId(key.getInt(1));
            }

            return user; // ← ahora user.getId() tiene el id real de la DB

        } catch (SQLException e) {
            throw new RuntimeException("Error no se pudo registrar el usuario "+ e.getMessage());
        }
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setEmployeeRole(EmployeeRole.valueOf(rs.getString("employee_role")));
        user.setActive(rs.getBoolean("active"));
        user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

        return  user;
    }
}
