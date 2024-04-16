package com.customGTApp.service;

import com.customGTApp.model.OrderOption;

public interface OrderOptionService {
    /**
     * Method to add order options to an order
     * @param orderClientId the id of the order client that the order options will be added to
     * @param orderOption the order options that will be added
     * @return the order options that were added
     */
    OrderOption addOrderOptions(Long orderClientId, OrderOption orderOption);
    /**
     * Method to update the newsletter option of an order
     * @param orderClientId the id of the order client that the newsletter option will be updated
     * @param newsLetter the new value of the newsletter option
     * @return the order options that were updated
     */
    OrderOption updateNewsLetter(Long orderClientId, Boolean newsLetter);
    /**
     * Method to update the order confirmation option of an order
     * @param orderClientId the id of the order client that the order confirmation option will be updated
     * @param orderConfirmed the new value of the order confirmation option
     * @return the order options that were updated
     */
    OrderOption updateOrderConfirmation(Long orderClientId, Boolean orderConfirmed);
}
