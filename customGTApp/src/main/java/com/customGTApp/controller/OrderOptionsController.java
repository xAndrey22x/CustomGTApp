package com.customGTApp.controller;

import com.customGTApp.model.OrderOptions;
import com.customGTApp.service.OrderOptionsService;
import com.customGTApp.service.impl.OrderOptionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * /addOrderOptions/{orderClientId} - add order options to an order, the order options will be received as a request body
 * /updateOrderNewsletter/{orderClientId} - update newsletter for an order, the newsletter will be received as a request parameter
 *                                        - param newsletter
 * /updateOrderConfirmed/{orderClientId} - update order confirmation for an order, the order confirmation will be received as a request parameter
 *                                       - param orderConfirmed
 */
@RestController
public class OrderOptionsController {
    private final OrderOptionsService orderOptionsService;

    @Autowired
    public OrderOptionsController(OrderOptionsServiceImpl orderOptionsService) {
        this.orderOptionsService = orderOptionsService;
    }

    /**
     * Method to add order options to an order
     * @param orderClientId the order client id
     * @param orderOptions the order options
     * @return the order options
     */
    @PostMapping("/addOrderOptions/{orderClientId}")
    public ResponseEntity<OrderOptions> addOrderOptions(@PathVariable("orderClientId") Long orderClientId, @RequestBody OrderOptions orderOptions) {
        OrderOptions orderOptions1 = orderOptionsService.addOrderOptions(orderClientId, orderOptions);
        if (orderOptions1 != null)
            return new ResponseEntity<>(orderOptions, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method to update newsletter for an order
     * @param orderClientId the order client id
     * @param newsletter the newsletter
     * @return the order options
     */
    @PutMapping("/updateOrderNewsletter/{orderClientId}")
    public ResponseEntity<OrderOptions> updateOrderNewsletter(@PathVariable("orderClientId") Long orderClientId, @RequestParam("newsletter") Boolean newsletter) {
        OrderOptions orderOptions = orderOptionsService.updateNewsLetter(orderClientId, newsletter);
        if (orderOptions != null)
            return new ResponseEntity<>(orderOptions, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method to update order confirmation for an order
     * @param orderClientId the order client id
     * @param orderConfirmed the order confirmation
     * @return the order options
     */
    @PutMapping("/updateOrderConfirmed/{orderClientId}")
    public ResponseEntity<OrderOptions> updateOrderConfirmation(@PathVariable("orderClientId") Long orderClientId, @RequestParam("orderConfirmed") Boolean orderConfirmed) {
        OrderOptions orderOptions = orderOptionsService.updateOrderConfirmation(orderClientId, orderConfirmed);
        if (orderOptions != null)
            return new ResponseEntity<>(orderOptions, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
