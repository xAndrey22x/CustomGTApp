package com.customGTApp.data.implJpaRepo;

import com.customGTApp.data.OrderItemContract;
import com.customGTApp.model.OrderItem;
import com.customGTApp.data.repository.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the OrderItemContract interface to provide the basic CRUD operations for the OrderItem entity
 */
@Repository
public class OrderItemData implements OrderItemContract {

    /**
     * Usage of JPA Repository to handle the database operations for the OrderItem entity
     */
    private final OrderItemRepo orderItemRepo;

    @Autowired
    public OrderItemData(OrderItemRepo orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    @Override
    public List<OrderItem> findAll() {
        return this.orderItemRepo.findAll();
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return this.orderItemRepo.findById(id);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return this.orderItemRepo.save(orderItem);
    }

    @Override
    public void deleteById(Long id) {
        this.orderItemRepo.deleteById(id);
    }

    @Override
    public Optional<List<OrderItem>> findByProductId(Long productId) {
        return this.orderItemRepo.findByProductId(productId);
    }

    @Override
    public Optional<List<OrderItem>> findByServiceProdId(Long serviceProdId) {
        return this.orderItemRepo.findByServiceProdId(serviceProdId);
    }

    @Override
    public void deleteAll(List<OrderItem> orderItems) {
        this.orderItemRepo.deleteAll(orderItems);
    }


}
