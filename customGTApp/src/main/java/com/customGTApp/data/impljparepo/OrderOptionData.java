package com.customGTApp.data.impljparepo;

import com.customGTApp.data.OrderOptionContract;
import com.customGTApp.data.repository.OrderOptionRepo;
import com.customGTApp.model.OrderOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the OrderOptionContract interface to provide the basic CRUD operations for the OrderOption entity
 */
@Repository
public class OrderOptionData implements OrderOptionContract {

    /**
     * Usage of JPA Repository to handle the database operations for the OrderOption entity
     */
    private final OrderOptionRepo orderOptionRepo;

    /**
     * Constructor to inject the OrderOptionRepo dependency
     * @param orderOptionRepo The OrderOptionRepo dependency
     */

    @Autowired
    public OrderOptionData(OrderOptionRepo orderOptionRepo) {
        this.orderOptionRepo = orderOptionRepo;
    }

    /**
     * Method to get all the order options in the database using the JPA Repository method
     * @return List of all the order options
     */

    @Override
    public List<OrderOption> findAll() {
        return this.orderOptionRepo.findAll();
    }

    /**
     * Method to get an order option based on the id using the JPA Repository method
     * @param id the order option id
     * @return the order option
     */

    @Override
    public Optional<OrderOption> findById(Long id) {
        return this.orderOptionRepo.findById(id);
    }

    /**
     * Method to save an order option using the JPA Repository method
     * @param orderOption the order option to be saved
     * @return the saved order option
     */

    @Override
    public OrderOption save(OrderOption orderOption) {
        return this.orderOptionRepo.save(orderOption);
    }

    /**
     * Method to delete an order option based on the id using the JPA Repository method
     * @param id the order option id
     */

    @Override
    public void deleteById(Long id) {
        this.orderOptionRepo.deleteById(id);
    }

    /**
     * Method to get an order option based on the orderClientId using the JPA Repository method
     * @param orderClientId the orderClientId of the order option
     * @return the order option
     */

    @Override
    public Optional<OrderOption> findByOrderClientId(Long orderClientId) {
        return this.orderOptionRepo.findByOrderClientId(orderClientId);
    }

}
