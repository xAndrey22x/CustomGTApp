package com.customGTApp.model;

import jakarta.persistence.*;

/**
 * Product model which will show how we keep information about our products.
 */
@Entity
public class Product {

    /**
     * 'name' for then name of the product we have also 'description', 'price' and 'carModel' which will hold the name
     * for the car this product it's designed.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private String carModel;


    public Product(){

    }

    public Product(String name, String description, float price, String carModel) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.carModel = carModel;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
