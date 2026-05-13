package com.dflorestrejo.pharmacysystem.entity;

import java.math.BigDecimal;

public class SaleItem {

    private int id;
    private Sale saleId;
    private Product productId;
    private int quantity;
    private BigDecimal unitPrice;

    public SaleItem() {
    }

    public SaleItem(Sale saleId, Product productId, int quantity, BigDecimal unitPrice) {
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sale getSaleId() {
        return saleId;
    }

    public Product getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal subtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
