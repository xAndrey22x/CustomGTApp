package com.customGTApp.service.impl;

import com.customGTApp.data.OrderClientContract;
import com.customGTApp.data.OrderOptionContract;
import com.customGTApp.model.OrderClient;
import com.customGTApp.model.OrderOption;
import com.customGTApp.observerservice.ClientOrderOptionObserver;
import com.customGTApp.observerservice.impl.ClientNotification;
import com.customGTApp.observerservice.impl.EmailService;
import com.customGTApp.service.OrderOptionService;
import com.customGTApp.service.observermanagement.OrderOptionManage;
import com.customGTApp.service.observermanagement.ProductObserverManage;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderOptionServiceImpl implements OrderOptionService, OrderOptionManage {

    private final OrderOptionContract orderOptionContract;
    private final OrderClientContract orderClientContract;

    private final ProductObserverManage productService;

    /**
     * List of observers that will be notified when a new order option is added
     */
    private final List<ClientOrderOptionObserver> observers = new ArrayList<>();

    /**
     * Email Service to be able to send emails to the clients about the new products that were added to the database via
     * the observer pattern.
     */
    private final EmailService emailService;

    @Autowired
    public OrderOptionServiceImpl(OrderOptionContract orderOptionContract, OrderClientContract orderClientContract, ProductObserverManage productService, EmailService emailService) {
        this.orderOptionContract = orderOptionContract;
        this.orderClientContract = orderClientContract;
        this.productService = productService;
        this.emailService = emailService;
    }

    @PostConstruct
    public void setupObservers(){
        addObserver(new ClientNotification(emailService));
    }

    /**
     * Method to add order options to an order and add the observer to the list of observers if the newsletter is true
     * using the data layer and the contract
     * @param orderClientId the order client id
     * @param orderOption the order options
     * @return the order options
     */
    @Override
    @Transactional
    public OrderOption addOrderOptions(Long orderClientId, OrderOption orderOption) {
        Optional<OrderClient> orderClient = this.orderClientContract.findById(orderClientId);
        if(orderClient.isPresent()){
            orderOption.setOrderClient(orderClient.get());
            if(orderOption.isNewsletter()) {
                ClientNotification clientNotification1 = new ClientNotification(orderClient.get().getId(),
                        orderClient.get().getEmail(), emailService);
                this.productService.addObserver(clientNotification1);
            }
            return this.orderOptionContract.save(orderOption);
        }
        return null;
    }

    /**
     * Method to update newsletter for an order and add the observer to the list of observers if the newsletter is true,
     * otherwise remove the observer from the list of observers using the data layer and the contract.
     * @param orderClientId the order client id
     * @param newsLetter the newsletter
     * @return the order options
     */

    @Override
    @Transactional
    public OrderOption updateNewsLetter(Long orderClientId, Boolean newsLetter) {
        Optional<OrderOption> orderOptions = this.orderOptionContract.findByOrderClientId(orderClientId);
        if(orderOptions.isPresent()){
            if(newsLetter){
                ClientNotification clientNotification1 = new ClientNotification(orderOptions.get().getOrderClient().getId(),
                        orderOptions.get().getOrderClient().getEmail(), emailService);
                this.productService.addObserver(clientNotification1);
            }
            else{
                this.productService.removeObserver(orderOptions.get().getOrderClient().getId());
            }
            orderOptions.get().setNewsletter(newsLetter);
            return this.orderOptionContract.save(orderOptions.get());
        }
        return null;
    }

    /**
     * Method to update order confirmation for an order and notify the observers if the order is confirmed
     * using the data layer and the contract.
     * @param orderClientId the order client id
     * @param orderConfirmed the order confirmation
     * @return the order options
     */
    @Override
    @Transactional
    public OrderOption updateOrderConfirmation(Long orderClientId, Boolean orderConfirmed) {
        Optional<OrderOption> orderOptions = this.orderOptionContract.findByOrderClientId(orderClientId);
        if(orderOptions.isPresent()){
            orderOptions.get().setOrderConfirmed(orderConfirmed);
            if(orderConfirmed){
                Optional<OrderClient> orderClient = this.orderClientContract.findById(orderClientId);
                orderClient.ifPresent(client -> notifyObservers(client.getEmail()));
            }
            return this.orderOptionContract.save(orderOptions.get());
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
