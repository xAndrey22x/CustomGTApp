package com.customGTApp.controller;

import com.customGTApp.model.OrderItem;
import com.customGTApp.service.OrderItemService;
import com.customGTApp.service.impl.OrderItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * /orderItem/addProduct/{productId}/{orderId}
 * /orderItem/addService/{serviceId}/{orderId}
 */
@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemServiceImpl orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/addProduct/{productId}/{orderId}")
    public ResponseEntity<OrderItem> addProductToOrder(@PathVariable("productId") Long productId, @PathVariable("orderId")
                                                       Long orderId, @RequestBody OrderItem orderItem){
        OrderItem orderItem1 = this.orderItemService.addProductToOrder(productId, orderId, orderItem);
        if(orderItem1 != null)
            return new ResponseEntity<>(orderItem, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("addService/{serviceId}/{orderId}")
    public ResponseEntity<OrderItem> addServiceToOrder(@PathVariable("serviceId") Long serviceId, @PathVariable("orderId")
    Long orderId, @RequestBody OrderItem orderItem){
        OrderItem orderItem1 = this.orderItemService.addServiceToOrder(serviceId, orderId, orderItem);
        if(orderItem1 != null)
            return new ResponseEntity<>(orderItem, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
