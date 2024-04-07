package com.customGTApp.service.observerManagement;

import com.customGTApp.model.OrderOptions;
import com.customGTApp.observerService.ClientOrderOptionObserver;

public interface OrderOptionsManage {
    /**
     * Add an observer to the list of observers
     * @param observer the observer to be added
     */
    void addObserver(ClientOrderOptionObserver observer);
    /**
     * Remove an observer from the list of observers
     * @param observer the observer to be removed
     */
    void removeObserver(ClientOrderOptionObserver observer);
    /**
     * Notify all observers about the new added order option
     * @param email the email of the client that has that order option
     */
    void notifyObservers(String email);
}
