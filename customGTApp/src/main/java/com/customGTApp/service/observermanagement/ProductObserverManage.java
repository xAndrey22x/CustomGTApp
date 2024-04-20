package com.customGTApp.service.observermanagement;

import com.customGTApp.model.Product;
import com.customGTApp.observerservice.ClientProductObserver;

public interface ProductObserverManage {

    /**
     * Method to set up the observers for the notifications about a product when it's added
     */
    void setupObservers();

    /**
     * Add an observer to the list of observers
     * @param observer the observer to be added
     */
    void addObserver(ClientProductObserver observer);
    /**
     * Remove an observer from the list of observers
     * @param id the id of the observer to be removed
     */
    void removeObserver(Long id);
    /**
     * Notify all observers about the new added product
     * @param product the product that was added
     */
    void notifyObservers(Product product);
}
