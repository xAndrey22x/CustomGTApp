package com.customGTApp.data.impljparepo;

import com.customGTApp.data.OrderClientContract;
import com.customGTApp.model.OrderClient;
import com.customGTApp.data.repository.OrderClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the OrderClientContract interface to provide the basic CRUD operations for the OrderClient entity
 */
@Repository
public class OrderClientData implements OrderClientContract {

    /**
     * Usage of JPA Repository to handle the database operations for the OrderClient entity
     */
    private final OrderClientRepo orderClientRepo;

    /**
     * Constructor to inject the OrderClientRepo dependency
     * @param orderClientRepo The OrderClientRepo dependency
     */
    @Autowired
    public OrderClientData(OrderClientRepo orderClientRepo) {
        this.orderClientRepo = orderClientRepo;
    }

    /**
     * Method to get all the order clients in the database using the JPA Repository method
     * @return List of all the order clients
     */
    @Override
    public List<OrderClient> findAll() {
        return this.orderClientRepo.findAll();
    }

    /**
     * Method to get an order client based on the id using the JPA Repository method
     * @param id the order client id
     * @return the order client
     */

    @Override
    public Optional<OrderClient> findById(Long id) {
        return this.orderClientRepo.findById(id);
    }

    /**
     * Method to save an order client using the JPA Repository method
     * @param orderClient the order client to be saved
     * @return the saved order client
     */

    @Override
    public OrderClient save(OrderClient orderClient) {
        return this.orderClientRepo.save(orderClient);
    }

    /**
     * Method to delete an order client based on the id using the JPA Repository method
     * @param id the order client id
     */

    @Override
    public void deleteById(Long id) {
        this.orderClientRepo.deleteById(id);
    }

    /**
     * Method to get all the order clients based on the newsletter option from the order option table
     * if it's true using the JPA Repository method
     * @return List of all the order clients based on the newsletter option set to true
     */

    @Override
    public List<OrderClient> findByOrderOptionNewsletterTrue() {
        return this.orderClientRepo.findByOrderOptionNewsletterTrue();
    }

    /**
     * Method to get all the order clients based on the order confirmed option from the order option table
     * if it's true using the JPA Repository method
     * @return List of all the order clients based on the order confirmed option set to true
     */
    @Override
    public List<OrderClient> findByOrderOptionOrderConfirmedTrue() {
        return this.orderClientRepo.findByOrderOptionOrderConfirmedTrue();
    }

    /**
     * Method to get all the order clients based on the order confirmed option from the order option table
     * if it's false using the JPA Repository method
     * @return List of all the order clients based on the order confirmed option set to false
     */
    @Override
    public List<OrderClient> findByOrderOptionOrderConfirmedFalse() {
        return this.orderClientRepo.findByOrderOptionOrderConfirmedFalse();
    }

    /**
     * Method to get an order client based on the email using the JPA Repository method
     * @param email the order client email
     * @return the list of order clients with the email
     */
    @Override
    public List<OrderClient> findByEmail(String email) {
        return this.orderClientRepo.findByEmail(email);
    }

}
