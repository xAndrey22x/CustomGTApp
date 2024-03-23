package com.customGTApp.service.impl;

import com.customGTApp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customGTApp.repository.ProductRepo;
import com.customGTApp.service.ProductService;

import java.util.List;

/**
 * The implementation of all the methods described in the interface 'ProductService'
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * We are using the ProductRepo interface for having all the basic CRUD operations in a DataBase.
     */
    private final ProductRepo productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    @Override
    public List<Product> findAllProducts() {
        return this.productRepo.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return this.productRepo.findById(id).orElse(null);
    }


    @Override
    public Product addProduct(Product p) {
        return this.productRepo.save(p);
    }

    @Override
    public Product updateProduct(Product p) {
        return this.productRepo.save(p);
    }

    @Override
    public void deleteProductById(Long id) {
        this.productRepo.deleteById(id);
    }
}
