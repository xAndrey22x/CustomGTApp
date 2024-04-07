package com.customGTApp.service.impl;

import com.customGTApp.model.OrderClient;
import com.customGTApp.model.OrderItem;
import com.customGTApp.model.Product;
import com.customGTApp.model.ServiceProd;
import com.customGTApp.repository.OrderClientRepo;
import com.customGTApp.repository.OrderItemRepo;
import com.customGTApp.repository.ProductRepo;
import com.customGTApp.repository.ServiceProdRepo;
import com.customGTApp.service.OrderItemService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    /**
     * All the repositories needed to handle the CRUD operations.
     */
    private final OrderItemRepo orderItemRepo;
    private final ProductRepo productRepo;
    private final OrderClientRepo orderClientRepo;
    private final ServiceProdRepo serviceProdRepo;

    public OrderItemServiceImpl(OrderItemRepo orderItemRepo, ProductRepo productRepo, OrderClientRepo orderClientRepo, ServiceProdRepo serviceProdRepo) {
        this.orderItemRepo = orderItemRepo;
        this.productRepo = productRepo;
        this.orderClientRepo = orderClientRepo;
        this.serviceProdRepo = serviceProdRepo;
    }
    /**
     * Method to add a product to an order item in the database only if the product and the order exist.
     * @param productId the product id
     * @param orderId the order id
     * @param orderItem the order item
     * @return the order item
     */
    @Override
    @Transactional
    public OrderItem addProductToOrder(Long productId, Long orderId, OrderItem orderItem) {
        Optional<Product> product = this.productRepo.findById(productId);
        if(product.isPresent()){
            Optional<OrderClient> orderClient = this.orderClientRepo.findById(orderId);
            if (orderClient.isPresent()){
                orderItem.setOrder(orderClient.get());
                orderItem.setProduct(product.get());
                return this.orderItemRepo.save(orderItem);
            }
        }
        return null;
    }
    /**
     * Method to add a service to an order item in the database only if the service and the order exist.
     * @param serviceId the service id
     * @param orderId the order id
     * @param orderItem the order item
     * @return the order item
     */
    @Override
    @Transactional
    public OrderItem addServiceToOrder(Long serviceId, Long orderId, OrderItem orderItem) {
        Optional<ServiceProd> serviceProd = this.serviceProdRepo.findById(serviceId);
        if(serviceProd.isPresent()){
            Optional<OrderClient> orderClient = this.orderClientRepo.findById(orderId);
            if (orderClient.isPresent()){
                orderItem.setOrder(orderClient.get());
                orderItem.setServiceProd(serviceProd.get());
                return this.orderItemRepo.save(orderItem);
            }
        }
        return null;
    }
}
