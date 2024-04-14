package com.customGTApp.data.repository;

import com.customGTApp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for holding all the CRUD operations for a DataBase we want to use.
 */
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
    /**
     * Find OrderItem by productId
     * @param productId the productId to search for
     * @return OrderItem
     */
    Optional<List<OrderItem>> findByProductId(Long productId);
    /**
     * Find OrderItem by serviceProdId
     * @param serviceProdId the serviceProdId to search for
     * @return OrderItem
     */
    Optional<List<OrderItem>> findByServiceProdId(Long serviceProdId);
}
