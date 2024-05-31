package com.customGTApp.controller;

import com.customGTApp.model.OrderItem;
import com.customGTApp.service.OrderItemService;
import com.customGTApp.service.impl.OrderItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * /addOrderProduct/{productId}/{orderId} - add a product to an order, only the quantity will be received as a request
 * parameter
 * /addOrderService/{serviceId}/{orderId} - add a service to an order, only the quantity will be received as a request
 * parameter
 */
@RestController
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
     * Method to add a product to an order and return it. Also calls the addProductToOrder method from the service layer
     * @param productId the product id
     * @param orderId the order id
     * @param quantity the quantity of item, price will be calculated based on the product price
     * @return the order item
     */
    @PostMapping("/addOrderProduct/{productId}/{orderId}")
    public ResponseEntity<OrderItem> addProductToOrder(@PathVariable("productId") Long productId, @PathVariable("orderId")
                                                       Long orderId, @RequestParam("quantity") int quantity){
        OrderItem orderItem1 = this.orderItemService.addProductToOrder(productId, orderId, quantity);
        if(orderItem1 != null)
            return new ResponseEntity<>(orderItem1, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * Method to add a service to an order and return it. Also calls the addServiceToOrder method from the service layer
     * @param serviceId the service id
     * @param orderId the order id
     * @param quantity the quantity of item, price will be calculated based on the service price
     * @return the order item
     */
    @PostMapping("addOrderService/{serviceId}/{orderId}")
    public ResponseEntity<OrderItem> addServiceToOrder(@PathVariable("serviceId") Long serviceId, @PathVariable("orderId")
    Long orderId, @RequestParam("quantity") int quantity){
        OrderItem orderItem1 = this.orderItemService.addServiceToOrder(serviceId, orderId, quantity);
        if(orderItem1 != null)
            return new ResponseEntity<>(orderItem1, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
