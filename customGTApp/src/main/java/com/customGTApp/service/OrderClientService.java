package com.customGTApp.service;

import com.customGTApp.model.OrderClient;

import java.util.List;

public interface OrderClientService {

    List<OrderClient> findAllOrders();
    OrderClient addOrder(OrderClient orderClient);
    OrderClient updateOrder(OrderClient orderClient);
    void deleteOrder(Long orderId);

}
