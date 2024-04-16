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
     * Method to get all the order clients based on the order option id using the JPA Repository method
     * @return List of all the order clients based on the order option id
     */

    @Override
    public List<OrderClient> findByOrderOptionNewsletterTrue() {
        return this.orderClientRepo.findByOrderOptionNewsletterTrue();
    }
}
