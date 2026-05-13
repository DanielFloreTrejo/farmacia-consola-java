package com.dflorestrejo.pharmacysystem.entity;


public class ProductStock {

    private int id;
    private Product productId;
    private Branch branchId;
    private int quantity;

    public ProductStock() {
    }

    public ProductStock(Product productId, Branch branchId, int quantity) {
        this.productId = productId;
        this.branchId = branchId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public Branch getBranchId() {
        return branchId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
