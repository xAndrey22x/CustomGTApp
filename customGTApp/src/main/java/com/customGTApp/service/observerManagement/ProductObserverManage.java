package com.customGTApp.service.observerManagement;

import com.customGTApp.model.Product;
import com.customGTApp.observerService.ClientProductObserver;

public interface ProductObserverManage {
    /**
     * Add an observer to the list of observers
     * @param observer the observer to be added
     */
    void addObserver(ClientProductObserver observer);
    /**
     * Remove an observer from the list of observers
     * @param observer the observer to be removed
     */
    void removeObserver(ClientProductObserver observer);
    /**
     * Notify all observers about the new added product
     * @param product the product that was added
     */
    void notifyObservers(Product product);
}
