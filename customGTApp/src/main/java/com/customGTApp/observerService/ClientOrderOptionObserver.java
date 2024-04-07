package com.customGTApp.observerService;

import com.customGTApp.model.OrderOptions;

public interface ClientOrderOptionObserver {
    /**
     * Update the observer about the new added order option
     * @param id the id of the client that has that order option
     */
    void update(Long id);
}
