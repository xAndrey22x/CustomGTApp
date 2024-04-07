package com.customGTApp.observerService.impl;

import com.customGTApp.model.OrderClient;
import com.customGTApp.model.OrderOptions;
import com.customGTApp.model.Product;
import com.customGTApp.observerService.ClientOrderOptionObserver;
import com.customGTApp.observerService.ClientProductObserver;
import com.customGTApp.repository.OrderClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientNotificationService implements ClientProductObserver, ClientOrderOptionObserver {

    private final OrderClientRepo orderClientRepo;

    private JavaMailSender emailSender;

    @Autowired
    public ClientNotificationService(OrderClientRepo orderClientRepo, JavaMailSender emailSender) {
        this.orderClientRepo = orderClientRepo;
        this.emailSender = emailSender;
    }

    /**
     * Update the observer about the new added product
     * @param product the product that was added
     */
    @Override
    public void update(Product product) {
        List<OrderClient> orderClients = orderClientRepo.findByOrderOptionsNewsletterTrue();
        for(OrderClient orderClient : orderClients){
            System.out.println("###################################################################################");
            System.out.println("Sending email to: " + orderClient.getEmail() + " about the new product: " + product.getName());
            System.out.println("###################################################################################");
        }
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
    public void update(Long id) {
        OrderClient orderClient = orderClientRepo.findById(id).orElse(null);
        if(orderClient != null){
            System.out.println("###################################################################################");
            System.out.println("Sending email to: " + orderClient.getEmail() + " that the order has been confirmed!");
            System.out.println("###################################################################################");
        }
    }
}
