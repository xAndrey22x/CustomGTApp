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

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    /**
     * Method to get all the products
     * @return list of all products
     */
    @Override
    public List<Product> findAllProducts() {
        return this.productRepo.findAll();
    }
    /**
     * Method to get a product based on the id
     * @param id the product id
     * @return the product
     */
    @Override
    public Product findProductById(Long id) {
        return this.productRepo.findById(id).orElse(null);
    }

    /**
     * Method to add a new product
     * @param p the product we want to add
     * @return the added product
     */
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
            return this.productRepo.save(p);
        }
        return null;
    }
    /**
     * Method to delete a product based on the id
     * @param id the product id
     */
    @Override
    public void deleteProductById(Long id) {
        this.productRepo.deleteById(id);
    }
    /**
     * Method to get all the photos of a product
     * @param productId the product id
     * @return list of all photos of the product
     */
    @Override
    @Transactional
    public List<Photo> findPhotosByProductId(Long productId) {
        Optional<Product> product = this.productRepo.findById(productId);
        return product.map(Product::getPhotos).orElse(null);
    }
}
