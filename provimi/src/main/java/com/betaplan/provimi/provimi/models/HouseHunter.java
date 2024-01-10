package com.betaplan.provimi.provimi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name ="househunters")

public class HouseHunter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Address required")
    private String address;

    @NotBlank(message = "Price required")
    @Size(min = 1, message = "Price must be greater than 0")
    private String price;

    @Past
    private Date createdAt;

    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public HouseHunter() {

    }
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    public HouseHunter(Long id, String address, String price, Date createdAt, Date updatedAt, User user) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
