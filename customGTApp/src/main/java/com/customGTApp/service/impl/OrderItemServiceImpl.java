package com.customGTApp.service.impl;

import com.customGTApp.data.OrderClientContract;
import com.customGTApp.data.OrderItemContract;
import com.customGTApp.data.ProductContract;
import com.customGTApp.data.ServiceProdContract;
import com.customGTApp.model.OrderClient;
import com.customGTApp.model.OrderItem;
import com.customGTApp.model.Product;
import com.customGTApp.model.ServiceProd;
import com.customGTApp.service.OrderItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    /**
     * All the repositories needed to handle the CRUD operations.
     */
    private final OrderItemContract orderItemContract;
    private final ProductContract productContract;
    private final OrderClientContract orderClientContract;
    private final ServiceProdContract serviceProdContract;

    @Autowired
    public OrderItemServiceImpl(OrderItemContract orderItemContract, ProductContract productContract, OrderClientContract orderClientContract, ServiceProdContract serviceProdContract) {
        this.orderItemContract = orderItemContract;
        this.productContract = productContract;
        this.orderClientContract = orderClientContract;
        this.serviceProdContract = serviceProdContract;
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
        Optional<Product> product = this.productContract.findById(productId);
        if(product.isPresent()){
            Optional<OrderClient> orderClient = this.orderClientContract.findById(orderId);
            if (orderClient.isPresent()){
                orderItem.setOrder(orderClient.get());
                orderItem.setProduct(product.get());
                return this.orderItemContract.save(orderItem);
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
        Optional<ServiceProd> serviceProd = this.serviceProdContract.findById(serviceId);
        if(serviceProd.isPresent()){
            Optional<OrderClient> orderClient = this.orderClientContract.findById(orderId);
            if (orderClient.isPresent()){
                orderItem.setOrder(orderClient.get());
                orderItem.setServiceProd(serviceProd.get());
                return this.orderItemContract.save(orderItem);
            }
        }
        return null;
    }
}
