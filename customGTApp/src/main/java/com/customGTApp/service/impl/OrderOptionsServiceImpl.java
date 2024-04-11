package com.customGTApp.service.impl;

import com.customGTApp.data.OrderClientContract;
import com.customGTApp.data.OrderOptionsContract;
import com.customGTApp.model.OrderClient;
import com.customGTApp.model.OrderOptions;
import com.customGTApp.observerService.ClientOrderOptionObserver;
import com.customGTApp.observerService.impl.ClientNotificationService;
import com.customGTApp.service.OrderOptionsService;
import com.customGTApp.service.observerManagement.OrderOptionsManage;
import com.customGTApp.service.observerManagement.ProductObserverManage;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderOptionsServiceImpl implements OrderOptionsService, OrderOptionsManage {

    private final OrderOptionsContract orderOptionsContract;
    private final OrderClientContract orderClientContract;

    private final ProductObserverManage productService;

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
    public OrderOptionsServiceImpl(OrderOptionsContract orderOptionsContract, OrderClientContract orderClientContract, ProductServiceImpl productService, ClientNotificationService clientNotificationService) {
        this.orderOptionsContract = orderOptionsContract;
        this.orderClientContract = orderClientContract;
        this.productService = productService;
        this.clientNotificationService = clientNotificationService;
    }

    @PostConstruct
    public void setupObservers(){
        addObserver(clientNotificationService);
    }

    /**
     * Method to add order options to an order and add the observer to the list of observers if the newsletter is true
     * @param orderClientId the order client id
     * @param orderOptions the order options
     * @return the order options
     */
    @Override
    @Transactional
    public OrderOptions addOrderOptions(Long orderClientId, OrderOptions orderOptions) {
        Optional<OrderClient> orderClient = this.orderClientContract.findById(orderClientId);
        if(orderClient.isPresent()){
            orderOptions.setOrderClient(orderClient.get());
            if(orderOptions.isNewsletter()) {
                ClientNotificationService clientNotificationService1 = new ClientNotificationService(null);
                clientNotificationService1.setEmail(orderClient.get().getEmail());
                clientNotificationService1.setClientId(orderClient.get().getId());
                this.productService.addObserver(clientNotificationService1);
            }
            return this.orderOptionsContract.save(orderOptions);
        }
        return null;
    }

    /**
     * Method to update newsletter for an order and add the observer to the list of observers if the newsletter is true,
     * otherwise remove the observer from the list of observers
     * @param orderClientId the order client id
     * @param newsLetter the newsletter
     * @return the order options
     */

    @Override
    @Transactional
    public OrderOptions updateNewsLetter(Long orderClientId, Boolean newsLetter) {
        Optional<OrderOptions> orderOptions = this.orderOptionsContract.findByOrderClientId(orderClientId);
        if(orderOptions.isPresent()){
            if(newsLetter){
                ClientNotificationService clientNotificationService1 = new ClientNotificationService(null);
                clientNotificationService1.setEmail(orderOptions.get().getOrderClient().getEmail());
                clientNotificationService1.setClientId(orderOptions.get().getOrderClient().getId());
                this.productService.addObserver(clientNotificationService1);
            }
            else{
                this.productService.removeObserver(orderOptions.get().getOrderClient().getId());
            }
            orderOptions.get().setNewsletter(newsLetter);
            return this.orderOptionsContract.save(orderOptions.get());
        }
        return null;
    }

    /**
     * Method to update order confirmation for an order and notify the observers if the order is confirmed
     * @param orderClientId the order client id
     * @param orderConfirmed the order confirmation
     * @return the order options
     */
    @Override
    @Transactional
    public OrderOptions updateOrderConfirmation(Long orderClientId, Boolean orderConfirmed) {
        Optional<OrderOptions> orderOptions = this.orderOptionsContract.findByOrderClientId(orderClientId);
        if(orderOptions.isPresent()){
            orderOptions.get().setOrderConfirmed(orderConfirmed);
            if(orderConfirmed){
                Optional<OrderClient> orderClient = this.orderClientContract.findById(orderClientId);
                orderClient.ifPresent(client -> notifyObservers(client.getEmail()));
            }
            return this.orderOptionsContract.save(orderOptions.get());
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
     * @param email the email of the client that has that order option
     */
    @Override
    public void notifyObservers(String email) {
        for(ClientOrderOptionObserver observer : observers){
            observer.update(email);
        }
    }
}
