package com.customGTApp.testing.observerservice;

import com.customGTApp.model.Product;
import com.customGTApp.observerservice.impl.ClientNotification;
import com.customGTApp.observerservice.impl.EmailService;
import jakarta.mail.MessagingException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ClientNotificationTest {

    /**
     * The ClientNotification object to be tested.
     */
    private ClientNotification clientNotification;

    /**
     * The mock needed for the tests.
     */
    @Mock
    private EmailService emailService;

    /**
     * Method to set up the tests.
     */
    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        this.clientNotification = new ClientNotification(1L, "email@email.com", emailService);
    }

    /**
     * Method to test the update method with a product.
     */
    @Test
    public void testUpdateWithProduct(){
        Product product = new Product(1, "name", "description", 10,"carModel" ,10);
        this.clientNotification.update(product);

        try {
            verify(emailService).sendEmailProductAdded("email@email.com", product);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to test the update method with an email.
     */
    @Test
    public void testUpdateWithEmail(){
        this.clientNotification.update("email1@email.com");

        try {
            verify(emailService).sendEmailOrderConfirmed("email1@email.com");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
