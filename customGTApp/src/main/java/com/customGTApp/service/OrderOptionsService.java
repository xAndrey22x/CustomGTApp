package com.customGTApp.service;

import com.customGTApp.model.OrderOptions;

public interface OrderOptionsService {
    OrderOptions addOrderOptions(Long orderClientId, OrderOptions orderOptions);
    OrderOptions updateNewsLetter(Long orderClientId, Boolean newsLetter);
    OrderOptions updateOrderConfirmation(Long orderClientId, Boolean orderConfirmed);
}
