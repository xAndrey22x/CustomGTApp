package com.customGTApp.service.impl;

import com.customGTApp.model.OrderClient;
import com.customGTApp.model.OrderOptions;
import com.customGTApp.observerService.ClientOrderOptionObserver;
import com.customGTApp.observerService.ClientProductObserver;
import com.customGTApp.observerService.impl.ClientNotificationService;
import com.customGTApp.repository.OrderClientRepo;
import com.customGTApp.repository.OrderOptionsRepo;
import com.customGTApp.service.OrderOptionsService;
import com.customGTApp.service.observerManagement.OrderOptionsManage;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderOptionsServiceImpl implements OrderOptionsService, OrderOptionsManage {

    private final OrderOptionsRepo orderOptionsRepo;
    private final OrderClientRepo orderClientRepo;

    /**
     * List of observers that will be notified when a new order option is added
     */
    private final List<ClientOrderOptionObserver> observers = new ArrayList<>();
    /**
     * ClientNotificationService object to handle the observer operations and to be able to use the methods in
     * the service layer
     */
    private final ClientNotificationService clientNotificationService;

    @Autowired
    public OrderOptionsServiceImpl(OrderOptionsRepo orderOptionsRepo, OrderClientRepo orderClientRepo, ClientNotificationService clientNotificationService) {
        this.orderOptionsRepo = orderOptionsRepo;
        this.orderClientRepo = orderClientRepo;
        this.clientNotificationService = clientNotificationService;
    }

    @PostConstruct
    public void setupObservers(){
        addObserver(clientNotificationService);
    }

    /**
     * Method to add order options to an order
     * @param orderClientId the order client id
     * @param orderOptions the order options
     * @return the order options
     */
    @Override
    @Transactional
    public OrderOptions addOrderOptions(Long orderClientId, OrderOptions orderOptions) {
        Optional<OrderClient> orderClient = this.orderClientRepo.findById(orderClientId);
        if(orderClient.isPresent()){
            orderOptions.setOrderClient(orderClient.get());
            return this.orderOptionsRepo.save(orderOptions);
        }
        return null;
    }

    /**
     * Method to update newsletter for an order
     * @param orderClientId the order client id
     * @param newsLetter the newsletter
     * @return the order options
     */

    @Override
    @Transactional
    public OrderOptions updateNewsLetter(Long orderClientId, Boolean newsLetter) {
        Optional<OrderOptions> orderOptions = this.orderOptionsRepo.findByOrderClientId(orderClientId);
        if(orderOptions.isPresent()){
            orderOptions.get().setNewsletter(newsLetter);
            return this.orderOptionsRepo.save(orderOptions.get());
        }
        return null;
    }

    /**
     * Method to update order confirmation for an order
     * @param orderClientId the order client id
     * @param orderConfirmed the order confirmation
     * @return the order options
     */
    @Override
    @Transactional
    public OrderOptions updateOrderConfirmation(Long orderClientId, Boolean orderConfirmed) {
        Optional<OrderOptions> orderOptions = this.orderOptionsRepo.findByOrderClientId(orderClientId);
        if(orderOptions.isPresent()){
            orderOptions.get().setOrderConfirmed(orderConfirmed);
            if(orderConfirmed){
                notifyObservers(orderClientId);
            }
            return this.orderOptionsRepo.save(orderOptions.get());
        }
        return null;
    }


    /**
     * Method to add an observer to the list of observers
     * @param observer the observer to be added
     */
    @Override
    public void addObserver(ClientOrderOptionObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Method to remove an observer from the list of observers
     * @param observer the observer to be removed
     */
    @Override
    public void removeObserver(ClientOrderOptionObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * Method to notify all observers about the new added order option
     * @param id the id of the client that has that order option
     */
    @Override
    public void notifyObservers(Long id) {
        for(ClientOrderOptionObserver observer : observers){
            observer.update(id);
        }
    }
}
