package com.customGTApp.service;

import com.customGTApp.model.OrderOptions;

public interface OrderOptionsService {
    /**
     * Method to add order options to an order
     * @param orderClientId the id of the order client that the order options will be added to
     * @param orderOptions the order options that will be added
     * @return the order options that were added
     */
    OrderOptions addOrderOptions(Long orderClientId, OrderOptions orderOptions);
    /**
     * Method to update the newsletter option of an order
     * @param orderClientId the id of the order client that the newsletter option will be updated
     * @param newsLetter the new value of the newsletter option
     * @return the order options that were updated
     */
    OrderOptions updateNewsLetter(Long orderClientId, Boolean newsLetter);
    /**
     * Method to update the order confirmation option of an order
     * @param orderClientId the id of the order client that the order confirmation option will be updated
     * @param orderConfirmed the new value of the order confirmation option
     * @return the order options that were updated
     */
    OrderOptions updateOrderConfirmation(Long orderClientId, Boolean orderConfirmed);
}
