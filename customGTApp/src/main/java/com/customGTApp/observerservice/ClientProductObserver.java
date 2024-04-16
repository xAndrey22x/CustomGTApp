package com.customGTApp.observerservice;

import com.customGTApp.model.Product;

public interface ClientProductObserver {
    /**
     * Update the observer about the new added product
     * @param product the product that was added
     */
    void update(Product product);
}
