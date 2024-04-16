package com.customGTApp.controller;

import com.customGTApp.model.Photo;
import com.customGTApp.model.Product;
import com.customGTApp.service.ProductService;
import com.customGTApp.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Implementation of the controller which will hold the bridge between our backend part and frontend part of the project.
 * Using HTTP mapping annotations, so we can create a link for a wanted operation.
 * /product/all - list all the products.
 * /product/{productId}/photos - list all the photos of a product based on the product id.
 * /product/find/{id} - find a product based on the id provided
 * /product/add - add a product, the product will be received as a request body
 * /product/update - update a product, the product will be received as a request body
 * /product/delete/{id} - delete a product based on the id provided
 * /product/updateQuantity/{productId} - update the quantity of a product, quantity as a request parameter
 *                                     - param quantity
 * /product/updatePrice/{productId} - update the price of a product, price as a request parameter
 *                                  - param price
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    /**
     * ProductService object to handle the product operations and to be able to use the methods in
     * the service layer
     */
    private final ProductService productService;

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

    /**
     * Method to get all the photos of a product based on the list of photos from a product and it calls the
     * findPhotosByProductId method from the ProductService class
     * @param productId the product id
     * @return list of all photos of a product
     */
    @GetMapping("/{productId}/photos")
    public ResponseEntity<List<Photo>> getAllPhotosByProductId(@PathVariable("productId") Long productId) {
        List<Photo> photos = this.productService.findPhotosByProductId(productId);
        if (photos != null)
            return new ResponseEntity<>(photos, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * Method to find a product based on the id provided and it calls the findProductById method from the ProductService class
     * @param id the id of the product we want to find
     * @return the product found
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = this.productService.findProductById(id);
        if(product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * Method to add a product, and it calls the addProduct method from the ProductService class
     * @param productReceived the product we want to add
     * @return the added product
     */
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product productReceived) {
        Product product = this.productService.addProduct(productReceived);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    /**
     * Method to update a product, and it calls the updateProduct method from the ProductService class
     * @param productReceived the product with the fields updated
     * @return the updated product
     */
    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product productReceived) {
        Product product = this.productService.updateProduct(productReceived);
        if (product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * Method to delete a product based on the id provided, and it calls the deleteProductById method from the ProductService class
     * @param id the id of the product we want to delete
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id) {
        this.productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Method to update the quantity of a product with quantity as a request parameter, and it calls the updateQuantity method from the ProductService class
     * @param productId the id of the product we want to update the quantity
     * @param quantity the new quantity of the product
     * @return the product with the updated quantity
     */
    @PutMapping("/updateQuantity/{productId}")
    public ResponseEntity<Product> updateQuantity(@PathVariable("productId") Long productId, @RequestParam("quantity") int quantity) {
        Product product = this.productService.updateQuantity(productId, quantity);
        if (product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method to update the price of a product with price as a request parameter and it calls the updatePrice method from the ProductService class
     * @param productId the id of the product we want to update the price
     * @param price the new price of the product
     * @return the product with the updated price
     */
    @PutMapping("/updatePrice/{productId}")
    public ResponseEntity<Product> updatePrice(@PathVariable("productId") Long productId, @RequestParam("price") float price) {
        Product product = this.productService.updatePrice(productId, price);
        if (product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
