package com.customGTApp.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

/**
 * Photo model of our database
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String url;
    /**
     * The functionality of ManyToOne implemented between Photo and Product.
     * The column productId acts as foreign key to the product primary key 'id'.
     * The product field acts as a connector between the photo and the product so the photo will know to which product
     * it's connected.
     * JsonBack it's fixing the recursion on this side so the JSON files will know this is the child.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    /**
     * Same as the product but for serviceProd now.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceId")
    private ServiceProd serviceProd;

    public Photo() {

    }

    public Photo(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Product getProduct() {
        return product;
    }

    public ServiceProd getServiceProd(){
        return serviceProd;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Method to add the product reference(FK) to the photos entity
     * @param product the product connected with the photo
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * As same as the setProduct but for services
     */
    public void setServiceProd(ServiceProd serviceProd){
        this.serviceProd = serviceProd;
    }



}
