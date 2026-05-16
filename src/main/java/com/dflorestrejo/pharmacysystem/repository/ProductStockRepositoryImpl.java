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
            throw new RuntimeException("Error no se pudo guardar el stock de producto " + e.getMessage());
        }
    }

    @Override
    public Optional<ProductStock> findById(int id) {
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT id, product_id, branch_id, quantity, minimum_stock " +
                "FROM product_stock WHERE id = ?")) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error no se puedo encontrar el id" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<ProductStock> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<ProductStock> findAll() {
        return List.of();
    }

    @Override
    public void update(ProductStock productStock) {

    }

    private ProductStock mapRow(ResultSet rs) throws SQLException {
        ProductStock productStock = new ProductStock();

        productStock.setId(rs.getInt("id"));
        productStock.setQuantity(rs.getInt("quantity"));
        productStock.setMinimumStock(rs.getInt("minimum_stock"));

        Branch branch = new Branch();
        branch.setId(rs.getInt("id"));
        branch.setName(rs.getString("name"));
        branch.setAddress(rs.getString("address"));
        Product product = new Product();
        return productStock;
    }
}
