package com.customGTApp.repository;

import com.customGTApp.model.OrderClient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for holding all the CRUD operations for a DataBase we want to use.
 */
public interface OrderClientRepo extends JpaRepository<OrderClient, Long> {
}
