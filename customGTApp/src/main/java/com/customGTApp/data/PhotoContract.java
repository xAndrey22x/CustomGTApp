package com.customGTApp.data;

import com.customGTApp.model.Photo;

import java.util.List;
import java.util.Optional;

/**
 * Methods to provide the basic CRUD operations for the Photo entity
 */
public interface PhotoContract {
    /**
     * Find all Photo entities
     * @return List of all Photo entities
     */
    List<Photo> findAll();
    /**
     * Find a Photo entity by its id
     * @param id The id of the Photo entity
     * @return The Photo entity wrapped in an Optional
     */
    Optional<Photo> findById(Long id);
    /**
     * Save a Photo entity
     * @param photo The Photo entity to be saved
     * @return Photo - The saved Photo entity
     */
    Photo save(Photo photo);
    /**
     * Delete a Photo entity by its id
     * @param id The id of the Photo entity
     */
    void deleteById(Long id);
}
