package com.customGTApp.data.implJpaRepo;

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

    @Autowired
    public OrderClientData(OrderClientRepo orderClientRepo) {
        this.orderClientRepo = orderClientRepo;
    }

    @Override
    public List<OrderClient> findAll() {
        return this.orderClientRepo.findAll();
    }

    @Override
    public Optional<OrderClient> findById(Long id) {
        return this.orderClientRepo.findById(id);
    }

    @Override
    public OrderClient save(OrderClient orderClient) {
        return this.orderClientRepo.save(orderClient);
    }

    @Override
    public void deleteById(Long id) {
        this.orderClientRepo.deleteById(id);
    }

    @Override
    public List<OrderClient> findByOrderOptionsNewsletterTrue() {
        return this.orderClientRepo.findByOrderOptionsNewsletterTrue();
    }
}
