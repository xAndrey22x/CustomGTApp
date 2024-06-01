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

    /**
     * Find all OrderClient which have the orderConfirmed option set to true
     * @return List of OrderClient
     */
    List<OrderClient> findByOrderOptionOrderConfirmedTrue();

    /**
     * Find all OrderClient which have the orderConfirmed option set to false
     * @return List of OrderClient
     */
    List<OrderClient> findByOrderOptionOrderConfirmedFalse();

    /**
     * Find all order clients entities by its email
     * @param email The email of the OrderClient
     * @return The list of clients with the email
     */
    List<OrderClient> findByEmail(String email);

}
