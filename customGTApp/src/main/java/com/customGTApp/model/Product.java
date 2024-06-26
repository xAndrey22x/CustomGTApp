package com.customGTApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Product model which will show how we keep information about our products.
 * Resolve the bidirectional problem between the product and the photo. The JsonIdentityInfo will fix the problem where
 * we will have an infinite recursion between the product and the photo.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @Column(nullable = false)
    private int quantity;
    /**
     * OneToMany connection between the product and photos. In the photos list we will hold all the photos connected
     * between the primary key of a product 'id' and the foreign key of photo 'productId'.
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    public Product(){

    }

    public Product(long id, String name, String description, float price, String carModel, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.carModel = carModel;
        this.quantity = quantity;
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


    /**
     * Method to add the photo reference to the list of photos which are connected with this product entity. And also to
     * set the reference to the photo so the photo will know it's connected to this product
     * @param photo the new photo entity connected with the product
     */
    public void addPhoto(Photo photo) {
        this.photos.add(photo);
        photo.setProduct(this);
    }

    /**
     * As the method to add, we have the method to remove it.
     */
    public void removePhoto(Photo photo) {
        this.photos.remove(photo);
        photo.setProduct(null);
    }

    /**
     * Return the photos list of a product.
     */
    public List<Photo> getPhotos(){
        return this.photos;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
