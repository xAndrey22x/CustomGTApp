package com.customGTApp.data;

import com.customGTApp.model.OrderItem;

import java.util.List;
import java.util.Optional;

/**
 * Methods to provide the basic CRUD operations for the OrderItem entity
 */
public interface OrderItemContract {
    /**
     * Method to get all the order items in the database
     * @return List of all the order items
     */
    List<OrderItem> findAll();
    /**
     * Method to get an order item based on the id
     * @param id the order item id
     * @return the order item
     */
    Optional<OrderItem> findById(Long id);
    /**
     * Method to save an order item
     * @param orderItem the order item to be saved
     * @return the saved order item
     */
    OrderItem save(OrderItem orderItem);
    /**
     * Method to delete an order item based on the id
     * @param id the order item id
     */
    void deleteById(Long id);

    /**
     * Method to get all the orders based on the product id
     * @param productId the product id
     * @return the list of order items
     */
    Optional<List<OrderItem>> findByProductId(Long productId);
    /**
     * Method to get all the orders based on the service id
     * @param serviceProdId the service id
     * @return the list of order items
     */
    Optional<List<OrderItem>> findByServiceProdId(Long serviceProdId);

    /**
     * Method to delete all the order items
     * @param orderItems the list of order items
     */
    void deleteAll(List<OrderItem> orderItems);

}
