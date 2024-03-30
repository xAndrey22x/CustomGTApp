package com.customGTApp.service;

import com.customGTApp.model.OrderItem;

public interface OrderItemService {

    OrderItem addProductToOrder(Long productId, Long orderId, OrderItem orderItem);
    OrderItem addServiceToOrder(Long serviceId, Long orderId, OrderItem orderItem);

}
