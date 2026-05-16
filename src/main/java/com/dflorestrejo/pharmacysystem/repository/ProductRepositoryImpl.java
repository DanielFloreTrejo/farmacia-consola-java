package com.dflorestrejo.pharmacysystem.repository;

import com.dflorestrejo.pharmacysystem.config.DatabaseConnection;
import com.dflorestrejo.pharmacysystem.entity.Category;
import com.dflorestrejo.pharmacysystem.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public Product save(Product product) {

        String sql = "INSERT INTO productos(name, description, category_id, laboratory, presentation, current_stock, " +
                "minimum_stock, purchase_price, sale_price , expiration_date, bar_code, requires_prescription, active) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setInt(3, product.getCategory().getId());
            pstmt.setString(4, product.getLaboratory());
            pstmt.setString(5, product.getPresentation());
            pstmt.setInt(6, product.getCurrentStock());
            pstmt.setInt(7, product.getMinimumStock());
            pstmt.setBigDecimal(8, product.getPurchasePrice());
            pstmt.setBigDecimal(9, product.getSalePrice());
            pstmt.setTimestamp(10, Timestamp.valueOf(product.getExpirationDate().atStartOfDay()));
            pstmt.setString(11, product.getBarCode());
            pstmt.setBoolean(12, product.isRequiresPrescription());
            pstmt.setBoolean(13, product.isActive());

            pstmt.executeUpdate();
            ResultSet key = pstmt.getGeneratedKeys();
            if (key.next()) {
                product.setId(key.getInt(1));
                return product;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar el producto " + e.getMessage());
        }
    }

    @Override
    public Optional<Product> findById(int id) {

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT p.id, p.name, p.description, c.id AS category_id," +
                     " c.name AS category_name, p.laboratory, p.presentation, p.current_stock, p.minimum_stock, " +
                     "p.purchase_price, p.sale_price , p.expiration_date, p.bar_code, p.requires_prescription, p.active " +
                     "FROM products p INNER JOIN categories c on p.category_id = c.id WHERE id = ?")) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el id del producto" + e.getMessage());
        }
    }

    @Override
    public List<Product> findAll() {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT p.id, p.name, p.description, c.id AS category_id, " +
                     "c.name AS category_name, p.laboratory, p.presentation, p.current_stock, p.minimum_stock, " +
                     "p.purchase_price, p.sale_price , p.expiration_date, p.bar_code, p.requires_prescription, p.active FROM products p" +
                     " INNER JOIN categories c on p.category_id = c.id")) {

            ResultSet rs = pstmt.executeQuery();
            List<Product> products = new ArrayList<>();

            while (rs.next()) {
                products.add(mapRow(rs)); // nueva instancia por cada fila
            }

            return products;

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los productos " + e.getMessage());
        }
    }

    @Override
    public Optional<Product> findByName(String name) {

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT p.id, p.name, p.description, c.id AS category_id," +
                     " c.name AS category_name, p.laboratory, p.presentation, p.current_stock, p.minimum_stock, " +
                     "p.purchase_price, p.sale_price , p.expiration_date, p.bar_code, p.requires_prescription, p.active" +
                     " FROM products p INNER JOIN categories c on p.category_id = c.id WHERE name = ?")) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el nombre del producto " + e.getMessage());
        }
    }

    @Override
    public void update(Product product) {

        String sql = "UPDATE products SET name=?, description=?, category_id=?, laboratory=?, presentation=?, " +
                "current_stock=?, minimum_stock=?, purchase_price=?, sale_price=?, expiration_date=?, bar_code=?," +
                " requires_prescription=?, active=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setInt(3, product.getCategory().getId());
            pstmt.setString(4, product.getLaboratory());
            pstmt.setString(5, product.getPresentation());
            pstmt.setInt(6, product.getCurrentStock());
            pstmt.setInt(7, product.getMinimumStock());
            pstmt.setBigDecimal(8, product.getPurchasePrice());
            pstmt.setBigDecimal(9, product.getSalePrice());
            pstmt.setTimestamp(10, Timestamp.valueOf(product.getExpirationDate().atStartOfDay()));
            pstmt.setString(11, product.getBarCode());
            pstmt.setBoolean(12, product.isRequiresPrescription());
            pstmt.setBoolean(13, product.isActive());
            pstmt.setInt(14,product.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el producto " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "UPDATE products SET active = 0 WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar producto " + e.getMessage());
        }
    }

    private Product mapRow(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setLaboratory(rs.getString("laboratory"));
        product.setPresentation(rs.getString("presentation"));
        product.setCurrentStock(rs.getInt("current_stock"));
        product.setMinimumStock(rs.getInt("minimum_stock"));
        product.setPurchasePrice(rs.getBigDecimal("purchase_price"));
        product.setSalePrice(rs.getBigDecimal("sale_price"));
        product.setExpirationDate(rs.getTimestamp("expiration_date").toLocalDateTime().toLocalDate());
        product.setBarCode(rs.getString("bar_code"));
        product.setRequiresPrescription(rs.getBoolean("requires_prescription"));
        product.setActive(rs.getBoolean("active"));

        Category category = new Category();
        category.setId(rs.getInt("category_id"));
        category.setName(rs.getString("category_name"));
        product.setCategory(category);

        return product;
    }
}
