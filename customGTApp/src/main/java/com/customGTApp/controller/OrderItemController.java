package com.customGTApp.controller;

import com.customGTApp.model.OrderItem;
import com.customGTApp.service.OrderItemService;
import com.customGTApp.service.impl.OrderItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * /orderItem/addProduct/{productId}/{orderId} - add a product to an order, the order item will be received as a request body
 * /orderItem/addService/{serviceId}/{orderId} - add a service to an order, the order item will be received as a request body
 */
@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    /**
     * OrderItemService object to handle the orderItem operations and to be able to use the methods in
     * the service layer
     */
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemServiceImpl orderItemService) {
        this.orderItemService = orderItemService;
    }
    /**
     * Method to add a product to an order
     * @param productId the product id
     * @param orderId the order id
     * @param orderItem the order item
     * @return the order item
     */
    @PostMapping("/addProduct/{productId}/{orderId}")
    public ResponseEntity<OrderItem> addProductToOrder(@PathVariable("productId") Long productId, @PathVariable("orderId")
                                                       Long orderId, @RequestBody OrderItem orderItem){
        OrderItem orderItem1 = this.orderItemService.addProductToOrder(productId, orderId, orderItem);
        if(orderItem1 != null)
            return new ResponseEntity<>(orderItem, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * Method to add a service to an order
     * @param serviceId the service id
     * @param orderId the order id
     * @param orderItem the order item
     * @return the order item
     */
    @PostMapping("addService/{serviceId}/{orderId}")
    public ResponseEntity<OrderItem> addServiceToOrder(@PathVariable("serviceId") Long serviceId, @PathVariable("orderId")
    Long orderId, @RequestBody OrderItem orderItem){
        OrderItem orderItem1 = this.orderItemService.addServiceToOrder(serviceId, orderId, orderItem);
        if(orderItem1 != null)
            return new ResponseEntity<>(orderItem, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
