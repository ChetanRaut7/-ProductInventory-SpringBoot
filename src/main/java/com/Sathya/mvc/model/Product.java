package com.Sathya.mvc.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private double price;
    private int quantity;
    private String image;
    private String createdBy;
    private LocalDateTime createdAt;

    @Transient
    public double getTotalPrice() {
        return price * quantity;
    }

    @Transient
    public double getDiscountedPrice() {
        return getTotalPrice() * 0.82;
    }

    @Transient
    public double getTaxPrice() {
        return getTotalPrice() * 0.18;
    }

    @Transient
    public double getNetAmount() {
        return getDiscountedPrice() + getTaxPrice();
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.createdBy = "Ratan"; // You can make this dynamic later
    }

    // Getters and setters...
}

