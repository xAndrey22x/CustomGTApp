package com.customGTApp.data;

import com.customGTApp.model.OrderOption;

import java.util.List;
import java.util.Optional;

/**
 * Methods to provide the basic CRUD operations for the OrderOption entity
 */
public interface OrderOptionContract {
    /**
     * Find all OrderOption entities
     * @return List<OrderOption> - List of all OrderOption entities
     */
    List<OrderOption> findAll();
    /**
     * Find an OrderOption entity by its id
     * @param id The id of the OrderOption entity
     * @return Optional<OrderOption> - The OrderOption entity wrapped in an Optional
     */
    Optional<OrderOption> findById(Long id);
    /**
     * Save an OrderOption entity
     * @param orderOption The OrderOption entity to be saved
     * @return OrderOption - The saved OrderOption entity
     */
    OrderOption save(OrderOption orderOption);
    /**
     * Delete an OrderOption entity by its id
     * @param id The id of the OrderOption entity
     */
    void deleteById(Long id);
    /**
     * Find an OrderOption entity by its orderClientId
     * @param orderClientId The orderClientId of the OrderOption entity
     * @return Optional<OrderOption> - The OrderOption entity wrapped in an Optional
     */
    Optional<OrderOption> findByOrderClientId(Long orderClientId);
}
