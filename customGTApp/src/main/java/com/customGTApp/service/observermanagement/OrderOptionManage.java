package com.customGTApp.service.observermanagement;

import com.customGTApp.observerservice.ClientOrderOptionObserver;

public interface OrderOptionManage {

    /**
     * Method to set up the observers for the notifications about an order option when it's added
     */
    void setupObservers();

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
