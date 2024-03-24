package com.customGTApp.service.impl;

import com.customGTApp.model.Photo;
import com.customGTApp.model.Product;
import com.customGTApp.repository.PhotoRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customGTApp.repository.ProductRepo;
import com.customGTApp.service.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * The implementation of all the methods described in the interface 'ProductService'
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * We are using the ProductRepo interface for having all the basic CRUD operations in a DataBase.
     */
    private final ProductRepo productRepo;
    private final PhotoRepo photoRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, PhotoRepo photoRepo) {
        this.productRepo = productRepo;
        this.photoRepo = photoRepo;
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

    /**
     * Updates the project only if it already exists in the database
     * @param p the product with the fields updated
     * @return the updated product or null
     */
    @Override
    @Transactional
    public Product updateProduct(Product p) {
        Long id = p.getId();
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isPresent()){
            productRepo.save(p);
        }
        return null;
    }

    @Override
    public void deleteProductById(Long id) {
        this.productRepo.deleteById(id);
    }

    @Override
    public List<Photo> findPhotosByProductId(Long productId) {
        return this.photoRepo.findByProductId(productId);
    }
}
