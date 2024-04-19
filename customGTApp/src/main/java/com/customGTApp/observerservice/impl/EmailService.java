package com.customGTApp.observerservice.impl;

import com.customGTApp.model.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
     * Method to email the client about the new product that was added to the database using java mail sender
     * with mime message which allows to send html content in the email. Also checks if the email is set up in the
     * application.properties file.
     * @param to the email of the client
     * @param product the product that was added
     */
    public void sendEmailProductAdded(String to, Product product) throws MessagingException {
        if ("youremail@email.com".equals(fromEmail)) {
            System.out.println("No email set up, please set up the email in the application.properties file.\n");
            return;
        }

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject("New Product Alert!");

        String content = "<html>" +
                "<head>" +
                "<style>" +
                "body {font-family: 'Helvetica Neue', Arial, sans-serif; background-color: #ffffff; margin: 0; padding: 0;}" +
                "h1 {font-size: 24px; margin-top: 0; padding-top: 5px;}" +
                "h2 {font-size: 20px; margin-top: 0;}" +
                "p {font-size: 16px; margin-top: 0;}" +
                "div {width: auto; max-width: 600px; padding: 5px; text-align: left; box-shadow: 0 2px 4px rgba(0,0,0,0.1);}" +
                "a {color: #0066cc; text-decoration: none; font-weight: bold;}" +
                "a:hover {text-decoration: underline;}" +
                "footer {font-size: 12px; text-align: center; padding: 20px 0;}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div>" +
                "<h1>New Product Available!</h1>" +
                "<h2>" + product.getName() + "</h2>" +
                "<p><strong>Description:</strong> " + product.getDescription() + "</p>" +
                "<p><strong>Price:</strong> $" + product.getPrice() + "</p>" +
                "<p><strong>Available for:</strong> " + product.getCarModel() + "</p>" +
                "<p>Check it out in our <a href='https://www.google.ro/'>store</a>!</p>" +
                "</div>" +
                "<footer>© 2024 CustomGT.</footer>" +
                "</body>" +
                "</html>";


        helper.setText(content, true);

        emailSender.send(message);
    }

    /**
     * Method to email the client about the order that was confirmed using java mail sender with mime message
     * which allows to send html content in the email. Also checks if the email is set up in the application.properties file.
     * @param to the email of the client
     */
    public void sendEmailOrderConfirmed(String to) throws MessagingException {
        if (fromEmail.equals("youremail@email.com")) {
            System.out.println("No email set up, please set up the email in the application.properties file.\n");
            return;
        }

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject("Order Confirmed!");

        String content = "<html>" +
                "<head>" +
                "<style>" +
                "body {font-family: Arial, sans-serif; background-color: #ffffff; margin: 0; padding: 5px;}" +
                "h1 {font-size: 24px; margin-top: 0; padding-top: 5px;}" +
                "p {font-size: 16px; margin-top: 0; color: #000;}" +
                "div {background: white; padding: 5px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);}" +
                "a {color: #0066cc; text-decoration: none; font-weight: bold;}" +
                "a:hover {text-decoration: underline;}" +
                "footer {font-size: 12px; text-align: center; padding: 20px 0;}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div>" +
                "<h1>Order Confirmation</h1>" +
                "<p>Dear Customer,</p>" +
                "<p>Your order has been successfully confirmed!</p>" +
                "<p>We are preparing it for shipping and will notify you once it's on its way.</p>" +
                "<p>Best regards,<br>The CustomGTApp Team</p>" +
                "<p>Visit our <a href='https://www.google.ro/'>website</a> for more!</p>" +
                "</div>" +
                "<footer>© 2024 CustomGT.</footer>" +
                "</body>" +
                "</html>";


        helper.setText(content, true);

        emailSender.send(message);
    }

}
