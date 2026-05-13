package com.dflorestrejo.pharmacysystem.entity;

public class Supplier {

    private int id;
    private String businessName;
    private String cuit;
    private String phone;
    private String email;
    private String address;
    private String contactName;
    private boolean active;

    public Supplier() {
    }

    public Supplier(String businessName, String cuit, String phone, String email, String address, String contactName,
                    boolean active) {
        this.businessName = businessName;
        this.cuit = cuit;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.contactName = contactName;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
