package com.customGTApp.service.impl;

import com.customGTApp.data.OrderClientContract;
import com.customGTApp.model.OrderClient;
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
    private final OrderClientContract orderClientContract;

    @Autowired
    public OrderClientServiceImpl(OrderClientContract orderClientContract) {
        this.orderClientContract = orderClientContract;
    }

    /**
     * Method to get all the orders from the database using our data layer with the help of the contract.
     * @return list of all orders
     */
    @Override
    public List<OrderClient> findAllOrders() {
        return this.orderClientContract.findAll();
    }

    /**
     * Method to add a new order to the database using our data layer with the help of the contract.
     * @param orderClient the order we want to add
     * @return the added order
     */
    @Override
    public OrderClient addOrder(OrderClient orderClient) {
        return this.orderClientContract.save(orderClient);
    }
    /**
     * Method to update an order in the database using our data layer and check if the order exists.
     * @param orderClient the order we want to update
     * @return the updated order
     */
    @Override
    public OrderClient updateOrder(OrderClient orderClient) {
        Optional<OrderClient> orderClient1 = this.orderClientContract.findById(orderClient.getId());
        if(orderClient1.isPresent()){
            return this.orderClientContract.save(orderClient);
        }
        return null;
    }
    /**
     * Method to delete an order from the database using our data layer with the help of the contract.
     * @param orderId the order id
     */
    @Override
    public void deleteOrder(Long orderId) {
        this.orderClientContract.deleteById(orderId);
    }

    /**
     * Method to find all orders that have been confirmed using our data layer with the help of the contract.
     * @return list of all orders that have been confirmed
     */
    @Override
    public List<OrderClient> findAllOrderConfirmed() {
        return this.orderClientContract.findByOrderOptionOrderConfirmedTrue();
    }

    /**
     * Method to find all orders that have not been confirmed using our data layer with the help of the contract.
     * @return list of all orders that have not been confirmed
     */
    @Override
    public List<OrderClient> findAllOrderNotConfirmed() {
        return this.orderClientContract.findByOrderOptionOrderConfirmedFalse();
    }
}
