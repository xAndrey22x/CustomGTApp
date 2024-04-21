package com.customGTApp.data;

import com.customGTApp.model.ServiceProd;

import java.util.List;
import java.util.Optional;

/**
 * Methods to provide the basic CRUD operations for the ServiceProd entity
 */
public interface ServiceProdContract {
    /**
     * Find all ServiceProd entities
     * @return The list of ServiceProd entities
     */
    List<ServiceProd> findAll();
    /**
     * Find a ServiceProd entity by its id
     * @param id The id of the ServiceProd entity
     * @return The ServiceProd entity wrapped in an Optional
     */
    Optional<ServiceProd> findById(Long id);
    /**
     * Save a ServiceProd entity
     * @param serviceProd The ServiceProd entity to be saved
     * @return ServiceProd - The saved ServiceProd entity
     */
    ServiceProd save(ServiceProd serviceProd);
    /**
     * Delete a ServiceProd entity by its id
     * @param id The id of the ServiceProd entity
     */
    void deleteById(Long id);
}
