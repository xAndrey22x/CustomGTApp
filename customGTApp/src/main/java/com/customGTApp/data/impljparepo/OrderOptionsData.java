package com.customGTApp.data.impljparepo;

import com.customGTApp.data.OrderOptionsContract;
import com.customGTApp.model.OrderOptions;
import com.customGTApp.data.repository.OrderOptionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the OrderOptionsContract interface to provide the basic CRUD operations for the OrderOptions entity
 */
@Repository
public class OrderOptionsData implements OrderOptionsContract {

    /**
     * Usage of JPA Repository to handle the database operations for the OrderOptions entity
     */
    private final OrderOptionsRepo orderOptionsRepo;

    /**
     * Constructor to inject the OrderOptionsRepo dependency
     * @param orderOptionsRepo The OrderOptionsRepo dependency
     */

    @Autowired
    public OrderOptionsData(OrderOptionsRepo orderOptionsRepo) {
        this.orderOptionsRepo = orderOptionsRepo;
    }

    /**
     * Method to get all the order options in the database using the JPA Repository method
     * @return List of all the order options
     */

    @Override
    public List<OrderOptions> findAll() {
        return this.orderOptionsRepo.findAll();
    }

    /**
     * Method to get an order option based on the id using the JPA Repository method
     * @param id the order option id
     * @return the order option
     */

    @Override
    public Optional<OrderOptions> findById(Long id) {
        return this.orderOptionsRepo.findById(id);
    }

    /**
     * Method to save an order option using the JPA Repository method
     * @param orderOptions the order option to be saved
     * @return the saved order option
     */

    @Override
    public OrderOptions save(OrderOptions orderOptions) {
        return this.orderOptionsRepo.save(orderOptions);
    }

    /**
     * Method to delete an order option based on the id using the JPA Repository method
     * @param id the order option id
     */

    @Override
    public void deleteById(Long id) {
        this.orderOptionsRepo.deleteById(id);
    }

    /**
     * Method to get an order option based on the orderClientId using the JPA Repository method
     * @param orderClientId the orderClientId of the order option
     * @return the order option
     */

    @Override
    public Optional<OrderOptions> findByOrderClientId(Long orderClientId) {
        return this.orderOptionsRepo.findByOrderClientId(orderClientId);
    }

}
