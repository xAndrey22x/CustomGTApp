package com.customGTApp.data.implJpaRepo;

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

    @Autowired
    public OrderOptionsData(OrderOptionsRepo orderOptionsRepo) {
        this.orderOptionsRepo = orderOptionsRepo;
    }

    @Override
    public List<OrderOptions> findAll() {
        return this.orderOptionsRepo.findAll();
    }

    @Override
    public Optional<OrderOptions> findById(Long id) {
        return this.orderOptionsRepo.findById(id);
    }

    @Override
    public OrderOptions save(OrderOptions orderOptions) {
        return this.orderOptionsRepo.save(orderOptions);
    }

    @Override
    public void deleteById(Long id) {
        this.orderOptionsRepo.deleteById(id);
    }

    @Override
    public Optional<OrderOptions> findByOrderClientId(Long orderClientId) {
        return this.orderOptionsRepo.findByOrderClientId(orderClientId);
    }

}
