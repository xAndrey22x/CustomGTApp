package com.customGTApp.service;

import com.customGTApp.model.Photo;
import com.customGTApp.model.Product;

import java.util.List;

/**
 * The contract our product service need to implement so the application works correctly and the code is more organized.
 * This interface will be implemented by ProductServiceImpl and other classes.
 */

public interface ProductService {
    /**
     * Method for returning all products.
     * @return a list of all products from the DataBase.
     */
    List<Product> findAllProducts();

    /**
     * Method for finding a product by id
     * @param id the id of the product we need to find
     * @return the product found
     */
    Product findProductById(Long id);

    /**
     * Method for adding a product
     * @param p the product we want to add to DataBase
     * @return the product added
     */
    Product addProduct(Product p);

    /**
     * Method for updating a product
     * @param p the product with the fields updated
     * @return the updated product
     */
    Product updateProduct(Product p);

    /**
     * Method for deleting a product by id
     * @param id the id of the product we want to delete
     */
    void deleteProductById(Long id);

    /**
     * Method to list all the photos of a product
     * @param productId the id of a product we want to list his photos
     * @return the photos list of the product
     */
    List<Photo> findPhotosByProductId(Long productId);

    /**
     * Method to update the quantity of a product
     * @param productId the id of the product we want to update the quantity
     * @param quantity the new quantity of the product
     * @return the product with the updated quantity
     */
    Product updateQuantity(Long productId, int quantity);

    /**
     * Method to update the price of a product
     * @param productId the id of the product we want to update the price
     * @param price the new price of the product
     * @return the product with the updated price
     */
    Product updatePrice(Long productId, float price);

}
