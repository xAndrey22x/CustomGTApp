package com.customGTApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String url;
    /**
     * The functionality of ManyToOne implemented between Photo and Product
     * The column productId acts as foreign key to the product primary key 'id'
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    @JsonBackReference
    private Product product;

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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
