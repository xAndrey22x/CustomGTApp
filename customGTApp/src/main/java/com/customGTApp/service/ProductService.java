package com.customGTApp.service;

import com.customGTApp.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();
    Product findProductById(Long id);
    Product addProduct(Product p);
    Product updateProduct(Product p);
    void deleteProductById(Long id);
}
