package com.customGTApp.controller;

import com.customGTApp.model.OrderOption;
import com.customGTApp.service.OrderOptionService;
import com.customGTApp.service.impl.OrderOptionServiceImpl;
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
public class OrderOptionController {
    private final OrderOptionService orderOptionService;

    @Autowired
    public OrderOptionController(OrderOptionServiceImpl orderOptionsService) {
        this.orderOptionService = orderOptionsService;
    }

    /**
     * Method to add order options to an order and return it. Also calls the addOrderOptions method from the service layer
     * @param orderClientId the order client id
     * @param orderOption the order options
     * @return the order options
     */
    @PostMapping("/addOrderOptions/{orderClientId}")
    public ResponseEntity<OrderOption> addOrderOptions(@PathVariable("orderClientId") Long orderClientId, @RequestBody OrderOption orderOption) {
        OrderOption orderOption1 = orderOptionService.addOrderOptions(orderClientId, orderOption);
        if (orderOption1 != null)
            return new ResponseEntity<>(orderOption, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method to update newsletter for an order and return it. Also calls the updateNewsLetter method from the service layer
     * @param orderClientId the order client id
     * @param newsletter the newsletter
     * @return the order options
     */
    @PutMapping("/updateOrderNewsletter/{orderClientId}")
    public ResponseEntity<OrderOption> updateOrderNewsletter(@PathVariable("orderClientId") Long orderClientId, @RequestParam("newsletter") Boolean newsletter) {
        OrderOption orderOption = orderOptionService.updateNewsLetter(orderClientId, newsletter);
        if (orderOption != null)
            return new ResponseEntity<>(orderOption, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method to update order confirmation for an order and return it. Also calls the updateOrderConfirmation method from the service layer
     * @param orderClientId the order client id
     * @param orderConfirmed the order confirmation
     * @return the order options
     */
    @PutMapping("/updateOrderConfirmed/{orderClientId}")
    public ResponseEntity<OrderOption> updateOrderConfirmation(@PathVariable("orderClientId") Long orderClientId, @RequestParam("orderConfirmed") Boolean orderConfirmed) {
        OrderOption orderOption = orderOptionService.updateOrderConfirmation(orderClientId, orderConfirmed);
        if (orderOption != null)
            return new ResponseEntity<>(orderOption, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
