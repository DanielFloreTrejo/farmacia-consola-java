package com.dflorestrejo.pharmacysystem.repository;

import com.dflorestrejo.pharmacysystem.config.DatabaseConnection;
import com.dflorestrejo.pharmacysystem.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public Category save(Category category) {

        String sql = "INSERT INTO categories(name) VALUES(?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstms = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstms.setString(1, category.getName());
            pstms.executeUpdate();

            ResultSet key = pstms.getGeneratedKeys();
            if (key.next()) {
                category.setId(key.getInt(1));
            }

            return category;

        } catch (SQLException e) {
            throw new RuntimeException("Error no se pudo guardar la categoria " + e.getMessage());
        }
    }

    @Override
    public Optional<Category> findById(int id) {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT id, name FROM categories WHERE id = ?")) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error no se encontró el id de la categoria " + e.getMessage());
        }
    }

    @Override
    public Optional<Category> findByName(String name) {

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT id, name FROM categories WHERE name = ?")) {

            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error no se encontró el nombre del la categoria");
        }
    }

    @Override
    public List<Category> findAll() {

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT id, name FROM categories")) {

            ResultSet rs = pstmt.executeQuery();
            List<Category> categories = new ArrayList<>();

            while (rs.next()) {
                categories.add(mapRow(rs));
            }

            return categories;

        } catch (SQLException e) {
            throw new RuntimeException("Error no se encontró ningún datos " + e.getMessage());
        }
    }

    @Override
    public void update(Category category) {
        String sql = "UPDATE categories SET name = ? WHERE id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, category.getName());
            pstmt.setInt(2, category.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error no se pudo actualizar los datos " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {

    }

    private Category mapRow(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));

        return category;
    }
}
