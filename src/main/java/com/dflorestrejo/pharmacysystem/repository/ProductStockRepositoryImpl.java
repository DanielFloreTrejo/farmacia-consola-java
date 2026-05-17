package com.dflorestrejo.pharmacysystem.repository;

import com.dflorestrejo.pharmacysystem.config.DatabaseConnection;
import com.dflorestrejo.pharmacysystem.entity.Branch;
import com.dflorestrejo.pharmacysystem.entity.Product;
import com.dflorestrejo.pharmacysystem.entity.ProductStock;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ProductStockRepositoryImpl implements ProductStockRepository {

    @Override
    public ProductStock save(ProductStock productStock) {

        String sql = "INSERT INTO product_stock(product_id, branch_id, quantity, minimum_stock) " +
                "VALUES(?, ?, ?, ?)";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
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
        return Optional.empty();
    }

    @Override
    public List<ProductStock> findByProduct(int productId) {
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT id, product_id, branch_id, quantity,minimum_stock" +
                " FROM product_stock WHERE product_id = ?")) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();

        }catch (SQLException e) {
            throw new RuntimeException("Error al buscar datos");
        }
        return List.of();
    }

    @Override
    public int totalStockProduct(int productId) {
        String sql = "SELECT COALESCE(SUM(quantity), 0) AS TOTAL FROM product_stock WHERE id = ?";
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ptsmt = conn.prepareStatement(sql)){

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
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, quantity);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, branchId);

            pstmt.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Error al actualizar el stock " + e.getMessage());
        }
    }
}
