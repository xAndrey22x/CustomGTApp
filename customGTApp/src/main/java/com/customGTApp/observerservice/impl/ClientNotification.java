package com.customGTApp.observerservice.impl;

import com.customGTApp.model.Product;
import com.customGTApp.observerservice.ClientOrderOptionObserver;
import com.customGTApp.observerservice.ClientProductObserver;
import jakarta.mail.MessagingException;

import java.util.Objects;

/**
 * The ClientNotification class is an observer service that sends emails to the clients
 * about the new products that were added to the database and if an order has been confirmed
 */
public class ClientNotification implements ClientProductObserver, ClientOrderOptionObserver {

    private Long clientId;
    private String email;

    /**
     * Email Service to be able to send emails to the clients.
     */
    private final EmailService emailService;

    /**
     * The email address from which the email will be sent set in the application.properties file
     */

    public ClientNotification(EmailService emailService){
        this.emailService = emailService;
    }

    public ClientNotification(Long clientId, String email, EmailService emailService) {
        this.clientId = clientId;
        this.email = email;
        this.emailService = emailService;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    /**
     * Update the observer about the new added product and email the client
     * @param product the product that was added
     */
    @Override
    public void update(Product product) {
        System.out.println("###################################################################################");
        System.out.println("Sending email to: " + this.email + " about the new product: " + product.getName());
        System.out.println("###################################################################################\n");
        try {
            emailService.sendEmailProductAdded(this.email, product);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("###################################################################################");
        System.out.println("Email was sent");
        System.out.println("###################################################################################\n");
    }

    /**
     * Update the observer about the order that has been confirmed and email the client
     * @param email the email of the client that has the order confirmed
     */
    @Override
    public void update(String email) {
        System.out.println("###################################################################################");
        System.out.println("Sending email to: " + email + " that the order has been confirmed!");
        System.out.println("###################################################################################\n");
        try {
            emailService.sendEmailOrderConfirmed(email);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("###################################################################################");
        System.out.println("Email was sent");
        System.out.println("###################################################################################\n");
    }

    /**
     * Method to check if two ClientNotification objects are equal
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientNotification that)) return false;
        return Objects.equals(clientId, that.clientId);
    }

    /**
     * Method to get the hash code of the ClientNotification object
     * @return the hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(clientId);
    }
}
