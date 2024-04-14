package com.customGTApp.data.repository;

import com.customGTApp.model.OrderClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for holding all the CRUD operations for a DataBase we want to use.
 */
public interface OrderClientRepo extends JpaRepository<OrderClient, Long> {
    /**
     * Find OrderClient by orderOptionsNewsletterTrue
     * @return OrderClient
     */
    List<OrderClient> findByOrderOptionsNewsletterTrue();

}
