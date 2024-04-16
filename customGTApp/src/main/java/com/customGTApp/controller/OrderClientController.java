package com.customGTApp.controller;

import com.customGTApp.model.OrderClient;
import com.customGTApp.service.OrderClientService;
import com.customGTApp.service.impl.OrderClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * /order/all - list all the orders.
 * /order/add - add an order, the order will be received as a request body
 * /order/update - update an order, the order will be received as a request body
 * /order/delete/{id} - delete an order based on the id provided
 */
@RestController
@RequestMapping("/order")
public class OrderClientController {
    /**
     * OrderClientService object to handle the orderClient operations and to be able to use the methods in
     * the service layer
     */
    private final OrderClientService orderClientService;

    @Autowired
    public OrderClientController(OrderClientServiceImpl orderClientService) {
        this.orderClientService = orderClientService;
    }

    /**
     * Method to get all the orders from the database and return them as a list. Also calls the findAllOrders method from the service layer
     * @return list of all orders
     */
    @GetMapping("/all")
    public ResponseEntity<List<OrderClient>> findAllOrder(){
        return new ResponseEntity<>(this.orderClientService.findAllOrders(), HttpStatus.OK);
    }

    /**
     * Method to add a new order to the database and return it. Also calls the addOrder method from the service layer
     * @param orderClient the order we want to add
     * @return the added order
     */
    @PostMapping("/add")
    public ResponseEntity<OrderClient> addOrder(@RequestBody OrderClient orderClient){
        return new ResponseEntity<>(this.orderClientService.addOrder(orderClient), HttpStatus.OK);
    }

    /**
     * Method to update an order in the database and return it. Also calls the updateOrder method from the service layer
     * @param orderClient the order we want to update
     * @return the updated order
     */
    @PutMapping("/update")
    public ResponseEntity<OrderClient> updateOder(@RequestBody OrderClient orderClient){
        OrderClient orderClient1 = this.orderClientService.updateOrder(orderClient);
        if (orderClient1 != null)
            return new ResponseEntity<>(orderClient1, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * Method to delete an order from the database. Also calls the deleteOrder method from the service layer
     * @param id the id of the order we want to delete
     * @return status of the operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id){
        this.orderClientService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
