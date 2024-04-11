package com.customGTApp.data;

import com.customGTApp.model.OrderOptions;

import java.util.List;
import java.util.Optional;

/**
 * Methods to provide the basic CRUD operations for the OrderOptions entity
 */
public interface OrderOptionsContract {
    /**
     * Find all OrderOptions entities
     * @return List<OrderOptions> - List of all OrderOptions entities
     */
    List<OrderOptions> findAll();
    /**
     * Find an OrderOptions entity by its id
     * @param id The id of the OrderOptions entity
     * @return Optional<OrderOptions> - The OrderOptions entity wrapped in an Optional
     */
    Optional<OrderOptions> findById(Long id);
    /**
     * Save an OrderOptions entity
     * @param orderOptions The OrderOptions entity to be saved
     * @return OrderOptions - The saved OrderOptions entity
     */
    OrderOptions save(OrderOptions orderOptions);
    /**
     * Delete an OrderOptions entity by its id
     * @param id The id of the OrderOptions entity
     */
    void deleteById(Long id);
    /**
     * Find an OrderOptions entity by its orderClientId
     * @param orderClientId The orderClientId of the OrderOptions entity
     * @return Optional<OrderOptions> - The OrderOptions entity wrapped in an Optional
     */
    Optional<OrderOptions> findByOrderClientId(Long orderClientId);
}
