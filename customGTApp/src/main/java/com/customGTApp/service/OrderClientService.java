package com.customGTApp.service;

import com.customGTApp.model.OrderClient;

import java.util.List;
/**
 * OrderClientService interface that will be implemented by OrderClientServiceImpl and other classes,
 * making it easier to manage the OrderClient model. Also, making the code more readable and easier to maintain.
 */
public interface OrderClientService {

    /**
     * Method that will return all the orders from the database
     * @return a list of all the orders
     */
    List<OrderClient> findAllOrders();

    /**
     * Method to find an order by its id
     * @param id the id of the order
     * @return the order that was found
     */
    OrderClient findById(Long id);

    /**
     * Method to add a new order to the database
     * @param orderClient the order that will be added
     * @return the order that was added
     */
    OrderClient addOrder(OrderClient orderClient);
    /**
     * Method to update an order in the database
     * @param orderClient the order that will be updated
     * @return the order that was updated
     */
    OrderClient updateOrder(OrderClient orderClient);
    /**
     * Method to delete an order from the database
     * @param orderId the id of the order that will be deleted
     */
    void deleteOrder(Long orderId);

    /**
     * Method to find all orders that have been confirmed
     * @return a list of all orders that have been confirmed
     */
    List<OrderClient> findAllOrderConfirmed();

    /**
     * Method to find all orders that have not been confirmed
     * @return a list of all orders that have not been confirmed
     */
    List<OrderClient> findAllOrderNotConfirmed();

    /**
     * Method to find the newsletter status of an order using the id of the order
     * @param id the id of the order
     * @return the newsletter status
     */
    boolean newsletterStatus(Long id);

    /**
     * Method to find all orders by its email
     * @param email the email of the order
     * @return a list of all orders with the email
     */
    List<OrderClient> findByEmail(String email);


}
