package com.customGTApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

/**
 * OrderOption model of our database who will hold information about the options of an order.
 */

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrderOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private boolean newsletter;
    @Column(nullable = false)
    private boolean orderConfirmed;

    /**
     * Relation between the order client table and this table to keep the options of an order
     */
    @OneToOne
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private OrderClient orderClient;

    public OrderOption() {
    }

    public OrderOption(long id, boolean newsletter, boolean orderConfirmed) {
        this.id = id;
        this.newsletter = newsletter;
        this.orderConfirmed = orderConfirmed;
    }

    public Long getId() {
        return id;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }

    public boolean isOrderConfirmed() {
        return orderConfirmed;
    }

    public void setOrderConfirmed(boolean orderConfirmed) {
        this.orderConfirmed = orderConfirmed;
    }

    public OrderClient getOrderClient() {
        return orderClient;
    }

    public void setOrderClient(OrderClient orderClient) {
        this.orderClient = orderClient;
    }


}
