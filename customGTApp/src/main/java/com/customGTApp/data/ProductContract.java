package com.customGTApp.data;

import com.customGTApp.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Methods to provide the basic CRUD operations for the Product entity
 */
public interface ProductContract {
    /**
     * Method to get all the products in the database
     * @return List of all the products
     */
    List<Product> findAll();
    /**
     * Method to get a product based on the id
     * @param id the product id
     * @return the product
     */
    Optional<Product> findById(Long id);
    /**
     * Method to save a product
     * @param product the product to be saved
     * @return the saved product
     */
    Product save(Product product);
    /**
     * Method to delete a product based on the id
     * @param id the product id
     */
    void deleteById(Long id);
}
