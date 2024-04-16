package com.customGTApp.data;


import com.customGTApp.model.OrderClient;

import java.util.List;
import java.util.Optional;

/**
 * Methods to provide the basic CRUD operations for the OrderClient entity
 */
public interface OrderClientContract {
    /**
     * Find all OrderClient entities
     * @return List<OrderClient> - List of all OrderClient entities
     */
    List<OrderClient> findAll();
    /**
     * Find an OrderClient entity by its id
     * @param id The id of the OrderClient entity
     * @return Optional<OrderClient> - The OrderClient entity wrapped in an Optional
     */
    Optional<OrderClient> findById(Long id);
    /**
     * Save an OrderClient entity
     * @param orderClient The OrderClient entity to be saved
     * @return OrderClient - The saved OrderClient entity
     */
    OrderClient save(OrderClient orderClient);
    /**
     * Delete an OrderClient entity by its id
     * @param id The id of the OrderClient entity
     */
    void deleteById(Long id);
    /**
     * Find all OrderClient entities with the newsletter option set to true
     * @return List<OrderClient> - List of all OrderClient entities with the newsletter option set to true
     */
    List<OrderClient> findByOrderOptionNewsletterTrue();
}
