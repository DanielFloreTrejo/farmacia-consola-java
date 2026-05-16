package com.dflorestrejo.pharmacysystem.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {

    private int id;
    private String name;
    private String description;
    private Category category;
    private String laboratory;
    private String presentation;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private LocalDate expirationDate;
    private String barCode;
    private boolean requiresPrescription;
    private boolean active;

    public Product() {
    }

    public Product(String name, String description, Category category, String laboratory, String presentation,
                    BigDecimal purchasePrice, BigDecimal salePrice,
                   LocalDate expirationDate, String barCode, boolean requiresPrescription, boolean active) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.laboratory = laboratory;
        this.presentation = presentation;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.expirationDate = expirationDate;
        this.barCode = barCode;
        this.requiresPrescription = requiresPrescription;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public boolean isRequiresPrescription() {
        return requiresPrescription;
    }

    public void setRequiresPrescription(boolean requiresPrescription) {
        this.requiresPrescription = requiresPrescription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
