package com.customGTApp.service.impl;

import com.customGTApp.data.OrderClientContract;
import com.customGTApp.data.OrderItemContract;
import com.customGTApp.data.ProductContract;
import com.customGTApp.model.OrderClient;
import com.customGTApp.model.OrderItem;
import com.customGTApp.model.Photo;
import com.customGTApp.model.Product;
import com.customGTApp.observerService.ClientProductObserver;
import com.customGTApp.observerService.impl.ClientNotificationService;
import com.customGTApp.service.observerManagement.ProductObserverManage;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customGTApp.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The implementation of all the methods described in the interface 'ProductService'
 */
@Service
public class ProductServiceImpl implements ProductService, ProductObserverManage {

    /**
     * We are using the ProductRepo interface for having all the basic CRUD operations in a DataBase.
     */
    private final ProductContract productContract;
    private final OrderItemContract orderItemContract;
    private final OrderClientContract orderClientContract;

    /**
     * List of observers that will be notified when a new product is added
     */
    private final List<ClientProductObserver> observers = new ArrayList<>();

    @Autowired
    public ProductServiceImpl(ProductContract productContract, OrderItemContract orderItemContract, OrderClientContract orderClientContract) {
        this.productContract = productContract;
        this.orderItemContract = orderItemContract;
        this.orderClientContract = orderClientContract;
    }


    /**
     * Method to set up the observer for notifications about a product when it's added, when the class is created
     */
    @PostConstruct
    public void setupObservers(){
        List<OrderClient> orderClients = this.orderClientContract.findByOrderOptionsNewsletterTrue();
        for(OrderClient orderClient : orderClients){
            ClientNotificationService clientNotificationService = new ClientNotificationService(null);
            clientNotificationService.setEmail(orderClient.getEmail());
            clientNotificationService.setClientId(orderClient.getId());
            addObserver(clientNotificationService);
        }
    }

    /**
     * Method to get all the products
     * @return list of all products
     */
    @Override
    public List<Product> findAllProducts() {
        return this.productContract.findAll();
    }
    /**
     * Method to get a product based on the id
     * @param id the product id
     * @return the product
     */
    @Override
    public Product findProductById(Long id) {
        return this.productContract.findById(id).orElse(null);
    }

    /**
     * Method to add a new product
     * @param p the product we want to add
     * @return the added product
     */
    @Override
    public Product addProduct(Product p) {
        notifyObservers(p);
        return this.productContract.save(p);
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
        Optional<Product> productOptional = productContract.findById(id);
        if(productOptional.isPresent()){
            return this.productContract.save(p);
        }
        return null;
    }
    /**
     * Method to delete a product based on the id and all the order items that contain this product
     * @param id the product id
     */
    @Override
    @Transactional
    public void deleteProductById(Long id) {
        Optional<List<OrderItem>> orderItems = this.orderItemContract.findByProductId(id);
        orderItems.ifPresent(this.orderItemContract::deleteAll);
        this.productContract.deleteById(id);
    }
    /**
     * Method to get all the photos of a product
     * @param productId the product id
     * @return list of all photos of the product
     */
    @Override
    @Transactional
    public List<Photo> findPhotosByProductId(Long productId) {
        Optional<Product> product = this.productContract.findById(productId);
        return product.map(Product::getPhotos).orElse(null);
    }

    /**
     * Method to update the quantity of a product
     * @param productId the product id
     * @param quantity the new quantity
     * @return the product with the updated quantity
     */
    @Override
    @Transactional
    public Product updateQuantity(Long productId, int quantity) {
        Optional<Product> productOptional = productContract.findById(productId);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            product.setQuantity(quantity);
            return productContract.save(product);
        }
        return null;
    }

    /**
     * Method to update the price of a product
     * @param productId the product id
     * @param price the new price
     * @return the product with the updated price
     */
    @Override
    @Transactional
    public Product updatePrice(Long productId, float price) {
        Optional<Product> productOptional = productContract.findById(productId);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            product.setPrice(price);
            return productContract.save(product);
        }
        return null;
    }

    /**
     * Method to add an observer to the list of observers
     * @param observer the observer to be added
     */
    @Override
    public void addObserver(ClientProductObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Method to remove an observer from the list of observers
     * @param id the id of the observer to be removed
     */
    @Override
    public void removeObserver(Long id) {
        this.observers.removeIf(observer -> observer instanceof ClientNotificationService && ((ClientNotificationService) observer).getClientId().equals(id));
    }

    /**
     * Method to notify all observers about the new added product
     * @param product the product that was added
     */
    @Override
    public void notifyObservers(Product product) {
        for (ClientProductObserver observer : observers) {
            observer.update(product);
        }
    }
}
