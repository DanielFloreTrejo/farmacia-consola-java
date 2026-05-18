package com.dflorestrejo.pharmacysystem.repository;

import com.dflorestrejo.pharmacysystem.config.DatabaseConnection;
import com.dflorestrejo.pharmacysystem.entity.Branch;
import com.dflorestrejo.pharmacysystem.entity.Category;
import com.dflorestrejo.pharmacysystem.entity.Product;
import com.dflorestrejo.pharmacysystem.entity.ProductStock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductStockRepositoryImpl implements ProductStockRepository {

    @Override
    public ProductStock save(ProductStock productStock) {

        String sql = "INSERT INTO product_stock(product_id, branch_id, quantity, minimum_stock) " +
                "VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, productStock.getProduct().getId());
            pstmt.setInt(2, productStock.getBranch().getId());
            pstmt.setInt(3, productStock.getQuantity());
            pstmt.setInt(4, productStock.getMinimumStock());

            pstmt.executeUpdate();

            ResultSet key = pstmt.getGeneratedKeys();
            if (key.next()) {
                productStock.setId(key.getInt(1));
            }

            return productStock;

        } catch (SQLException e) {
            throw new RuntimeException("Error no se pudo guardar el stock " + e.getMessage());
        }
    }

    @Override
    public Optional<ProductStock> findByProductAndBranch(int productId, int branchId) {
        String sql = "SELECT ps.id, ps.quantity, ps.minimum_stock, p.id AS product_id, p.name AS product_name, p.description," +
                " c.id AS category_id, c.name AS category_name, p.laboratory, p.presentation, p.purchase_price, p.sale_price, " +
                "p.expiration_date, p.bar_code, p.requires_prescription, p.active AS product_active, b.id AS branch_id," +
                " b.name AS branch_name, b.address, b.phone, b.active AS branch_active FROM product_stock ps " +
                "INNER JOIN products p ON ps.product_id = p.id INNER JOIN branches b ON ps.branch_id = b.id" +
                " INNER JOIN categories c ON p.category_id = c.id WHERE ps.product_id = ? AND ps.branch_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            pstmt.setInt(2, branchId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar datos");
        }
    }

    @Override
    public List<ProductStock> findByProduct(int productId) {

        String sql = "SELECT ps.id, ps.quantity, ps.minimum_stock, p.id AS product_id, p.name AS product_name, p.description," +
                " c.id AS category_id, c.name AS category_name, p.laboratory, p.presentation, p.purchase_price, p.sale_price, " +
                "p.expiration_date, p.bar_code, p.requires_prescription, p.active AS product_active, b.id AS branch_id," +
                " b.name AS branch_name, b.address, b.phone, b.active AS branch_active FROM product_stock ps " +
                "INNER JOIN products p ON ps.product_id = p.id INNER JOIN branches b ON ps.branch_id = b.id" +
                " INNER JOIN categories c ON p.category_id = c.id WHERE ps.product_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            List<ProductStock> productStocks = new ArrayList<>();

            while (rs.next()) {
                productStocks.add(mapRow(rs));
            }

            return productStocks;

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar datos");
        }
    }

    @Override
    public int totalStockProduct(int productId) {
        String sql = "SELECT COALESCE(SUM(quantity), 0) AS TOTAL FROM product_stock WHERE product_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ptsmt = conn.prepareStatement(sql)) {

            ptsmt.setInt(1, productId);
            ResultSet rs = ptsmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("TOTAL");
            }

            return 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al calcular el stock " + e.getMessage());
        }
    }

    @Override
    public void updateQuantity(int productId, int branchId, int quantity) {
        String sql = "UPDATE product_stock SET quantity = ? WHERE product_id = ? and branch_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, quantity);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, branchId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el stock " + e.getMessage());
        }
    }

    private ProductStock mapRow(ResultSet rs) throws SQLException {
        ProductStock productStock = new ProductStock();
        productStock.setId(rs.getInt("id"));
        productStock.setQuantity(rs.getInt("quantity"));
        productStock.setMinimumStock(rs.getInt("minimum_stock"));

        Product product = new Product();
        product.setId(rs.getInt("product_id"));
        product.setName(rs.getString("product_name"));
        product.setDescription(rs.getString("description"));
        product.setLaboratory(rs.getString("laboratory"));
        product.setPresentation(rs.getString("presentation"));
        product.setPurchasePrice(rs.getBigDecimal("purchase_price"));
        product.setSalePrice(rs.getBigDecimal("sale_price"));

        // null-safe para fecha de vencimiento
        Timestamp ts = rs.getTimestamp("expiration_date");
        product.setExpirationDate(ts != null ? ts.toLocalDateTime().toLocalDate() : null);

        product.setBarCode(rs.getString("bar_code"));
        product.setRequiresPrescription(rs.getBoolean("requires_prescription"));
        product.setActive(rs.getBoolean("product_active"));

        Category category = new Category();
        category.setId(rs.getInt("category_id"));
        category.setName(rs.getString("category_name"));

        product.setCategory(category);

        Branch branch = new Branch();
        branch.setId(rs.getInt("branch_id"));
        branch.setName(rs.getString("branch_name"));
        branch.setAddress(rs.getString("address"));
        branch.setPhone(rs.getString("phone"));
        branch.setActive(rs.getBoolean("branch_active"));

        productStock.setProduct(product);
        productStock.setBranch(branch);

        return productStock;
    }
}
