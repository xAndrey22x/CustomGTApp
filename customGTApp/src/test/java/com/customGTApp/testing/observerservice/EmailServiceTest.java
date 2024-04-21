package com.customGTApp.testing.observerservice;

import com.customGTApp.model.Product;
import com.customGTApp.observerservice.impl.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import org.springframework.test.util.ReflectionTestUtils;


public class EmailServiceTest {

    /**
     * The EmailService object to be tested.
     */
    private EmailService emailService;

    /**
     * The mocks needed for the tests.
     */
    @Mock
    private JavaMailSender emailSender;

    @Mock
    private MimeMessage mimeMessage;

    /**
     * Method to set up the mocks and the object to be tested.
     */
    @Before
    public void setUp(){
        openMocks(this);
        this.emailService = new EmailService(emailSender);
        when(emailSender.createMimeMessage()).thenReturn(mimeMessage);
    }

    /**
     * Method to test the sendEmailProductAdded method especially the createMimeMessage if it is called.
     */
    @Test
    public void testSendEmailProductAddedCreateMime() throws MessagingException {
        ReflectionTestUtils.setField(emailService, "fromEmail", "email@email.com");

        String toEmail = "client@example.com";
        Product product = new Product(1L, "name", "description", 10, "carModel", 1);

        emailService.sendEmailProductAdded(toEmail, product);

        verify(emailSender).createMimeMessage();
    }

    /**
     * Method to test the sendEmailProductAdded method.
     */
    @Test
    public void testSendEmailProductAdded() throws MessagingException {
        ReflectionTestUtils.setField(emailService, "fromEmail", "email@email.com");

        String toEmail = "client@email.com";
        Product product = new Product(1L, "name", "description", 10, "carModel", 1);

        emailService.sendEmailProductAdded(toEmail, product);

        verify(emailSender).send(mimeMessage);
    }

    /**
     * Method to test the sendEmailProductAdded method when the email is not set up.
     */
    @Test
    public void testSendEmailProductAddedNoEmail() throws MessagingException {
        ReflectionTestUtils.setField(emailService, "fromEmail", "youremail@email.com");

        String toEmail = "client@email.com";
        Product product = new Product(1L, "name", "description", 10, "carModel", 1);

        emailService.sendEmailProductAdded(toEmail, product);

        verify(emailSender, never()).send(mimeMessage);
    }

    /**
     * Method to test the sendEmailOrderConfirmed method if the createMimeMessage is called.
     */
    @Test
    public void testSendEmailOrderConfirmedCreateMime() throws MessagingException {
        ReflectionTestUtils.setField(emailService, "fromEmail", "email@email.com");

        String toEmail = "client@email.com";

        emailService.sendEmailOrderConfirmed(toEmail);

        verify(emailSender).createMimeMessage();
    }

    /**
     * Method to test the sendEmailOrderConfirmed method.
     */
    @Test
    public void testSendEmailOrderConfirmed() throws MessagingException {
        ReflectionTestUtils.setField(emailService, "fromEmail", "email@email.com");

        String toEmail = "client@email.com";

        emailService.sendEmailOrderConfirmed(toEmail);

        verify(emailSender).send(mimeMessage);
    }

    /**
     * Method to test the sendEmailOrderConfirmed method when the email is not set up.
     */
    @Test
    public void testSendEmailOrderConfirmedNoEmail() throws MessagingException {
        ReflectionTestUtils.setField(emailService, "fromEmail", "youremail@email.com");

        String toEmail = "client@email.com";

        emailService.sendEmailOrderConfirmed(toEmail);

        verify(emailSender, never()).send(mimeMessage);
    }

}
