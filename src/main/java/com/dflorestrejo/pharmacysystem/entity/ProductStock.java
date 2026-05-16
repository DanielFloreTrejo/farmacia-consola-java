package com.dflorestrejo.pharmacysystem.entity;


public class ProductStock {

    private int id;
    private Product product;
    private Branch branch;
    private int quantity;
    private int minimumStock;

    public ProductStock() {
    }

    public ProductStock(Product product, Branch branch, int quantity, int minimumStock) {
        this.product = product;
        this.branch = branch;
        this.quantity = quantity;
        this.minimumStock = minimumStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public Branch getBranch() {
        return branch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(int minimumStock) {
        this.minimumStock = minimumStock;
    }
}
