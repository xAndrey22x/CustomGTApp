package com.customGTApp.data.repository;

import com.customGTApp.model.OrderClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for holding all the CRUD operations for a DataBase we want to use.
 */
public interface OrderClientRepo extends JpaRepository<OrderClient, Long> {
    /**
     * Find all OrderClient which have the newsletter option set to true
     * @return List of OrderClient
     */
    List<OrderClient> findByOrderOptionNewsletterTrue();

}
