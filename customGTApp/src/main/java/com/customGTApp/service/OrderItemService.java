package com.customGTApp.service;

import com.customGTApp.model.OrderItem;

/**
 * OrderItemService interface that will be implemented by OrderItemServiceImpl and other classes,
 * making it easier to manage the OrderItem model. Also, making the code more readable and easier to maintain.
 */
public interface OrderItemService {

    /**
     * Method to add a product to an order
     * @param productId the id of the product that will be added
     * @param orderId the id of the order that the product will be added to
     * @param quantity the quantity of the product that will be added
     * @return the order item that was added
     */
    OrderItem addProductToOrder(Long productId, Long orderId, int quantity);
    /**
     * Method to add a service to an order
     * @param serviceId the id of the service that will be added
     * @param orderId the id of the order that the service will be added to
     * @param quantity the quantity of the service that will be added
     * @return the order item that was added
     */
    OrderItem addServiceToOrder(Long serviceId, Long orderId, int quantity);

}
