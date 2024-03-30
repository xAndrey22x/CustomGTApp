package com.customGTApp.service.impl;

import com.customGTApp.model.OrderClient;
import com.customGTApp.repository.OrderClientRepo;
import com.customGTApp.service.OrderClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderClientServiceImpl implements OrderClientService {
    private final OrderClientRepo orderClientRepo;

    @Autowired
    public OrderClientServiceImpl(OrderClientRepo orderClientRepo) {
        this.orderClientRepo = orderClientRepo;
    }

    @Override
    public List<OrderClient> findAllOrders() {
        return this.orderClientRepo.findAll();
    }

    @Override
    public OrderClient addOrder(OrderClient orderClient) {
        return this.orderClientRepo.save(orderClient);
    }

    @Override
    public OrderClient updateOrder(OrderClient orderClient) {
        Optional<OrderClient> orderClient1 = this.orderClientRepo.findById(orderClient.getId());
        if(orderClient1.isPresent()){
            return this.orderClientRepo.save(orderClient);
        }
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {
        this.orderClientRepo.deleteById(orderId);
    }
}
