package com.customGTApp.repository;

import com.customGTApp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for holding all the CRUD operations for a DataBase we want to use.
 */
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}
