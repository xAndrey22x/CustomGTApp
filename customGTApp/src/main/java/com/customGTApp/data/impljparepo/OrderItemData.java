package com.customGTApp.data.impljparepo;

import com.customGTApp.data.OrderItemContract;
import com.customGTApp.model.OrderItem;
import com.customGTApp.data.repository.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the OrderItemContract interface to provide the basic CRUD operations for the OrderItem entity
 */
@Repository
public class OrderItemData implements OrderItemContract {

    /**
     * Usage of JPA Repository to handle the database operations for the OrderItem entity
     */
    private final OrderItemRepo orderItemRepo;

    /**
     * Constructor to inject the OrderItemRepo dependency
     * @param orderItemRepo The OrderItemRepo dependency
     */

    @Autowired
    public OrderItemData(OrderItemRepo orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    /**
     * Method to get all the order items in the database using the JPA Repository method
     * @return List of all the order items
     */

    @Override
    public List<OrderItem> findAll() {
        return this.orderItemRepo.findAll();
    }

    /**
     * Method to get an order item based on the id using the JPA Repository method
     * @param id the order item id
     * @return the order item
     */

    @Override
    public Optional<OrderItem> findById(Long id) {
        return this.orderItemRepo.findById(id);
    }

    /**
     * Method to save an order item using the JPA Repository method
     * @param orderItem the order item to be saved
     * @return the saved order item
     */

    @Override
    public OrderItem save(OrderItem orderItem) {
        return this.orderItemRepo.save(orderItem);
    }

    /**
     * Method to delete an order item based on the id using the JPA Repository method
     * @param id the order item id
     */

    @Override
    public void deleteById(Long id) {
        this.orderItemRepo.deleteById(id);
    }

    /**
     * Method to get all the orders based on the product id
     * @param productId the product id
     * @return the list of order items
     */

    @Override
    public Optional<List<OrderItem>> findByProductId(Long productId) {
        return this.orderItemRepo.findByProductId(productId);
    }

    /**
     * Method to get all the orders based on the service id
     * @param serviceProdId the service id
     * @return the list of order items
     */

    @Override
    public Optional<List<OrderItem>> findByServiceProdId(Long serviceProdId) {
        return this.orderItemRepo.findByServiceProdId(serviceProdId);
    }

    /**
     * Method to delete all the order items
     * @param orderItems the list of order items
     */

    @Override
    public void deleteAll(List<OrderItem> orderItems) {
        this.orderItemRepo.deleteAll(orderItems);
    }


}
