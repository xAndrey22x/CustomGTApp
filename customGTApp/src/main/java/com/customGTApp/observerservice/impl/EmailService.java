package com.customGTApp.observerservice.impl;

import com.customGTApp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    /**
     * JavaMailSender object to send emails
     */
    private final JavaMailSender emailSender;

    /**
     * The email address from which the email will be sent set in the application.properties file
     */
    @Value("${app.mail.username}")
    private String fromEmail;

    /**
     * Constructor for the EmailService class to inject the JavaMailSender object
     * @param emailSender the JavaMailSender object
     */
    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    /**
     * Method to email the client about the new product that was added to the database
     * @param to the email of the client
     * @param product the product that was added
     */

    public void sendEmailProductAdded(String to, Product product) {
        if ("youremail@email.com".equals(fromEmail)) {
            System.out.println("No email set up, please set up the email in the application.properties file.\n");
            return;
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject("New Product Alert!");
        message.setText("Dear Customer,\n\nCheck out our new product: " + product.getName() + "!\n\nBest,\nCustomGTApp Team");
        emailSender.send(message);
    }

    /**
     * Method to email the client about the order that was confirmed
     * @param to the email of the client
     */
    public void sendEmailOrderConfirmed(String to){
        if ("youremail@email.com".equals(fromEmail)) {
            System.out.println("No email set up, please set up the email in the application.properties file.\n");
            return;
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject("Order Confirmed");
        message.setText("Dear Customer,\n\nYour order has been confirmed!\n\nBest,\nCustomGTApp Team");
        emailSender.send(message);
    }

}
