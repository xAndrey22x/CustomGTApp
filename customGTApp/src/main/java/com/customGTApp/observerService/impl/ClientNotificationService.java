package com.customGTApp.observerService.impl;

import com.customGTApp.model.Product;
import com.customGTApp.observerService.ClientOrderOptionObserver;
import com.customGTApp.observerService.ClientProductObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class ClientNotificationService implements ClientProductObserver, ClientOrderOptionObserver {

    private Long clientId;
    private String email;

    private final JavaMailSender emailSender;


    @Autowired
    public ClientNotificationService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
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
     * Update the observer about the new added product
     * @param product the product that was added
     */
    @Override
    public void update(Product product) {
        System.out.println("###################################################################################");
        System.out.println("Sending email to: " + this.email + " about the new product: " + product.getName());
        System.out.println("###################################################################################\n");
    }

    private void sendEmail(String to, Product product) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("email.com");
        message.setTo(to);
        message.setSubject("New Product Alert!");
        message.setText("Dear Customer,\n\nCheck out our new product: " + product.getName() + "!\n\nBest,\nCustomGTApp Team");
        emailSender.send(message);
    }

    @Override
    public void update(String email) {
        System.out.println("###################################################################################");
        System.out.println("Sending email to: " + email + " that the order has been confirmed!");
        System.out.println("###################################################################################\n");
    }
}
