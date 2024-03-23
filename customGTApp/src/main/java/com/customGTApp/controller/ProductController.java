package com.customGTApp.controller;

import com.customGTApp.model.Product;
import com.customGTApp.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Implementation of the controller which will hold the bridge between our backend part and frontend part of the project.
 * Using HTTP mapping annotations, so we can create a link for a wanted operation.
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    /**
     * The service which holds our logic for the Products.
     */
    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    /**
     * ###
     * All the methods are implemented in the same way as the get method explained below but using different types of
     * mapping like Post, Put, Delete.
     * ###
     * Usage of the method for getting all the data from a DataBase using GetMapping
     * @return A responseEntity which will hold a list of our products which will be converted in a JSON format
     * and also an OK status, so we can confirm the operation worked successfully.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = this.productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product productReceived) {
        Product product = this.productService.addProduct(productReceived);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product productReceived) {
        Product product = this.productService.updateProduct(productReceived);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id) {
        this.productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
