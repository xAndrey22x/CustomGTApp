package com.customGTApp.service;

import com.customGTApp.model.OrderClient;

import java.util.List;
/**
 * OrderClientService interface that will be implemented by OrderClientServiceImpl and other classes,
 * making it easier to manage the OrderClient model. Also, making the code more readable and easier to maintain.
 */
public interface OrderClientService {

    List<OrderClient> findAllOrders();
    OrderClient addOrder(OrderClient orderClient);
    OrderClient updateOrder(OrderClient orderClient);
    void deleteOrder(Long orderId);

}
