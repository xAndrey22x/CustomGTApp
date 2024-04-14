package com.customGTApp.data.implJpaRepo;

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

    @Autowired
    public ProductData(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepo.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepo.findById(id);
    }

    @Override
    public Product save(Product product) {
        return this.productRepo.save(product);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepo.deleteById(id);
    }
}
