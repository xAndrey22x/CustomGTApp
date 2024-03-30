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
 * /order/all
 * /order/add
 * /order/update
 * /order/delete/{id}
 */
@RestController
@RequestMapping("/order")
public class OrderClientController {
    private final OrderClientService orderClientService;

    @Autowired
    public OrderClientController(OrderClientServiceImpl orderClientService) {
        this.orderClientService = orderClientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderClient>> findAllOrder(){
        return new ResponseEntity<>(this.orderClientService.findAllOrders(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OrderClient> addOrder(@RequestBody OrderClient orderClient){
        return new ResponseEntity<>(this.orderClientService.addOrder(orderClient), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<OrderClient> updateOder(@RequestBody OrderClient orderClient){
        OrderClient orderClient1 = this.orderClientService.updateOrder(orderClient);
        if (orderClient1 != null)
            return new ResponseEntity<>(orderClient1, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id){
        this.orderClientService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
