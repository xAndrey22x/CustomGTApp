package com.customGTApp.service;

import com.customGTApp.model.OrderItem;

/**
 * OrderItemService interface that will be implemented by OrderItemServiceImpl and other classes,
 * making it easier to manage the OrderItem model. Also, making the code more readable and easier to maintain.
 */
public interface OrderItemService {

    OrderItem addProductToOrder(Long productId, Long orderId, OrderItem orderItem);
    OrderItem addServiceToOrder(Long serviceId, Long orderId, OrderItem orderItem);

}
