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
     * Method to find an order by its id using our data layer with the help of the contract.
     * @param id the id of the order
     * @return the order that was found
     */
    @Override
    public OrderClient findById(Long id) {
        Optional<OrderClient> orderClient = this.orderClientContract.findById(id);
        return orderClient.orElse(null);
    }

    /**
     * Method to add a new order to the database using our data layer with the help of the contract.
     * @param orderClient the order we want to add
     * @return the added order
     */
    @Override
    public OrderClient addOrder(OrderClient orderClient) {
        orderClient.setTotalPrice(0);
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

    /**
     * Method to find the newsletter status of an order using the id of the order.
     * @param id the order id
     * @return the newsletter status
     */
    public boolean newsletterStatus(Long id) {
        return this.orderClientContract.findById(id).get().getOrderOptions().isNewsletter();
    }

    /**
     * Method to find all orders by its email using our data layer with the help of the contract.
     * @param email the email of the order
     * @return the list of order clients by that email
     */
    @Override
    public List<OrderClient> findByEmail(String email) {
        return this.orderClientContract.findByEmail(email);
    }

}
