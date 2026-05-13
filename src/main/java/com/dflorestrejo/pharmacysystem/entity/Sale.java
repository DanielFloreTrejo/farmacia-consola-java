package com.dflorestrejo.pharmacysystem.entity;

import com.dflorestrejo.pharmacysystem.enums.PaymentMethod;
import com.dflorestrejo.pharmacysystem.enums.SaleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {

    private int id;
    private User userId;
    private String customerName;
    private LocalDateTime createdAt;
    private BigDecimal discount;
    private PaymentMethod paymentMethod;
    private SaleStatus status;
    private List<SaleItem> items;

    public Sale() {
    }

    public Sale(User userId, String customerName, BigDecimal discount, PaymentMethod paymentMethod, SaleStatus status) {
        this.userId = userId;
        this.customerName = customerName;
        this.discount = discount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.items = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public SaleStatus getStatus() {
        return status;
    }

    public void setStatus(SaleStatus status) {
        this.status = status;
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public void setItems(List<SaleItem> items) {
        this.items = items;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BigDecimal total() {
        BigDecimal subtotal = items.stream()
                .map(SaleItem::subtotal)          // transforma cada item en su subtotal
                .reduce(BigDecimal.ZERO, BigDecimal::add);  // los suma todos
        return subtotal.subtract(discount != null ? discount : BigDecimal.ZERO);
    }
}
