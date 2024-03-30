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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

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

    @Override
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

    @Override
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
