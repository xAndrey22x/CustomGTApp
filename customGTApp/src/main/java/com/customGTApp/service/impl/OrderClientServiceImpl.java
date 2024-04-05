package com.customGTApp.service.impl;

import com.customGTApp.model.OrderClient;
import com.customGTApp.repository.OrderClientRepo;
import com.customGTApp.service.OrderClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderClientServiceImpl implements OrderClientService {
    /**
     * OrderClientRepo object to handle the CRUD operations and to be able to use the methods in
     * the repository layer.
     */
    private final OrderClientRepo orderClientRepo;

    @Autowired
    public OrderClientServiceImpl(OrderClientRepo orderClientRepo) {
        this.orderClientRepo = orderClientRepo;
    }

    /**
     * Method to get all the orders
     * @return list of all orders
     */
    @Override
    public List<OrderClient> findAllOrders() {
        return this.orderClientRepo.findAll();
    }

    /**
     * Method to add a new order
     * @param orderClient the order we want to add
     * @return the added order
     */
    @Override
    public OrderClient addOrder(OrderClient orderClient) {
        return this.orderClientRepo.save(orderClient);
    }
    /**
     * Method to update an order
     * @param orderClient the order we want to update
     * @return the updated order
     */
    @Override
    public OrderClient updateOrder(OrderClient orderClient) {
        Optional<OrderClient> orderClient1 = this.orderClientRepo.findById(orderClient.getId());
        if(orderClient1.isPresent()){
            return this.orderClientRepo.save(orderClient);
        }
        return null;
    }
    /**
     * Method to delete an order
     * @param orderId the order id
     */
    @Override
    public void deleteOrder(Long orderId) {
        this.orderClientRepo.deleteById(orderId);
    }
}
