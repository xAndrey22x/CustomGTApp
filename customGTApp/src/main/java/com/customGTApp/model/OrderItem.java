package com.customGTApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

/**
 * Implement the table for keeping the items that were ordered
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private int quantity;
    @Column(nullable = false)
    private float price;

    /**
     * Relation between the product table and this table
     */
    @ManyToOne()
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    /**
     * Relation between services table.
     */
    @ManyToOne()
    @JoinColumn(name = "serviceId", referencedColumnName = "id")
    private ServiceProd serviceProd;

    /**
     * Relation between an Order and this item
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private OrderClient orderClient;


    public OrderItem(){ }

    public OrderItem(int quantity, float price) {
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public ServiceProd getServiceProd() {
        return serviceProd;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setServiceProd(ServiceProd serviceProd) {
        this.serviceProd = serviceProd;
    }

    public OrderClient getOrder() {
        return orderClient;
    }

    public void setOrder(OrderClient orderClient) {
        this.orderClient = orderClient;
    }
}
