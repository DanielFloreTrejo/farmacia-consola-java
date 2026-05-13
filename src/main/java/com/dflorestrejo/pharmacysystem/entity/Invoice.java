package com.dflorestrejo.pharmacysystem.entity;

import java.time.LocalDateTime;

public class Invoice {

    private int id;
    private Sale saleId;
    private String invoiceNumber;
    private LocalDateTime createdAt;

    public Invoice() {
    }

    public Invoice(Sale saleId, String invoiceNumber) {
        this.saleId = saleId;
        this.invoiceNumber = invoiceNumber;
        this.createdAt = LocalDateTime.now();
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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
