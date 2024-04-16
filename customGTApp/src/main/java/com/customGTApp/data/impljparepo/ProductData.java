package com.customGTApp.data.impljparepo;

import com.customGTApp.data.ProductContract;
import com.customGTApp.model.Product;
import com.customGTApp.data.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ProductContract interface to provide the basic CRUD operations for the Product entity
 */
@Repository
public class ProductData implements ProductContract {

    /**
     * Usage of JPA Repository to handle the database operations for the Product entity
     */
    private final ProductRepo productRepo;

    /**
     * Constructor to inject the ProductRepo dependency
     * @param productRepo The ProductRepo dependency
     */

    @Autowired
    public ProductData(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    /**
     * Method to get all the products in the database using the JPA Repository method
     * @return List of all the products
     */

    @Override
    public List<Product> findAll() {
        return this.productRepo.findAll();
    }

    /**
     * Method to get a product based on the id using the JPA Repository method
     * @param id the product id
     * @return the product
     */

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepo.findById(id);
    }

    /**
     * Method to save a product using the JPA Repository method
     * @param product the product to be saved
     * @return the saved product
     */

    @Override
    public Product save(Product product) {
        return this.productRepo.save(product);
    }

    /**
     * Method to delete a product based on the id using the JPA Repository method
     * @param id the product id
     */

    @Override
    public void deleteById(Long id) {
        this.productRepo.deleteById(id);
    }
}
