package com.dflorestrejo.pharmacysystem.entity;

import com.dflorestrejo.pharmacysystem.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrder {

    private int id;
    private Supplier supplierId;
    private User userId;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private List<PurchaseOrderItem> items;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Supplier supplierId, User userId, OrderStatus status) {
        this.supplierId = supplierId;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
        this.status = status;
        this.items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Supplier getSupplierId() {
        return supplierId;
    }

    public User getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<PurchaseOrderItem> getItems() {
        return items;
    }

    public void setItems(List<PurchaseOrderItem> items) {
        this.items = items;
    }

    public BigDecimal total() {
        return null;
    }
}
