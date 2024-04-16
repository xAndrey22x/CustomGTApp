package com.customGTApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderClient model of our database who will hold information about our orders.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String county;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private int postalCode;
    @Column(nullable = false)
    private float totalPrice;

    /**
     * All the order items in an order
     */
    @OneToMany(mappedBy = "orderClient",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * The options for an order
     */
    @OneToOne(mappedBy = "orderClient", cascade = CascadeType.ALL, orphanRemoval = true)
    private OrderOptions orderOptions;


    public OrderClient() {}


    public OrderClient(long id, String name, String email, String phoneNumber, String county, String city, String address, int postalCode, float totalPrice) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.county = county;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCounty() {
        return county;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Add, remove, get the list of items from an order
     * @param orderItem the item we want to add or remove
     */
    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void removeOrderItem(OrderItem orderItem){
        this.orderItems.remove(orderItem);
        orderItem.setOrder(null);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderOptions getOrderOptions() {
        return orderOptions;
    }

    public void setOrderOptions(OrderOptions orderOptions) {
        this.orderOptions = orderOptions;
    }

}
