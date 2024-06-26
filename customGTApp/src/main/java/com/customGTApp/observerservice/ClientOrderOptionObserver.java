package com.customGTApp.observerservice;

public interface ClientOrderOptionObserver {
    /**
     * Update the observer about the new added order option
     * @param email the email of the client that has that order option
     */
    void update(String email);
}
