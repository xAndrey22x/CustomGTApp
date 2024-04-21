package com.customGTApp.testing.service;

import com.customGTApp.data.OrderClientContract;
import com.customGTApp.data.OrderItemContract;
import com.customGTApp.data.ProductContract;
import com.customGTApp.model.OrderClient;
import com.customGTApp.model.Product;
import com.customGTApp.observerservice.impl.ClientNotification;
import com.customGTApp.observerservice.impl.EmailService;
import com.customGTApp.service.impl.ProductServiceImpl;
import com.customGTApp.service.observermanagement.ProductObserverManage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class ProductObserverTest {
    /**
     * The ProductObserverManage object to test.
     */
    private ProductObserverManage productObserverManage;

    /**
     * All the mocks needed for the ProductObserverManage object.
     */
    @Mock
    private ProductContract productContract;
    @Mock
    private OrderItemContract orderItemContract;
    @Mock
    private OrderClientContract orderClientContract;
    @Mock
    private EmailService emailService;

    /**
     * Method to set up the mocks and the object to test.
     */
    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        this.productObserverManage = new ProductServiceImpl(productContract, orderItemContract, orderClientContract, emailService);
    }

    /**
     * Method to test the addObserver method when it should add a new observer.
     */
    @Test
    public void testAddObserverNewObserverShouldAdd() {
        ClientNotification observer = Mockito.mock(ClientNotification.class);
        when(observer.getEmail()).thenReturn("example@example.com");

        Product product = new Product(1, "name", "description", 10, "carModel", 1);

        this.productObserverManage.addObserver(observer);
        this.productObserverManage.notifyObservers(product);

        Mockito.verify(observer).update(product);

    }

    /**
     * Method to test the addObserver method when it should not add an existing observer.
     */
    @Test
    public void testAddObserverExistingObserverShouldNotAdd() {
        ClientNotification observer1 = Mockito.mock(ClientNotification.class);
        when(observer1.getEmail()).thenReturn("duplicate@example.com");
        this.productObserverManage.addObserver(observer1);

        ClientNotification observer2 = Mockito.mock(ClientNotification.class);
        when(observer2.getEmail()).thenReturn("duplicate@example.com");
        this.productObserverManage.addObserver(observer2);

        Product product = new Product(1, "name", "description", 10, "carModel", 1);

        this.productObserverManage.notifyObservers(product);

        Mockito.verify(observer2, never()).update(product);
    }

    /**
     * Method to test the removeObserver method when it should remove an existing observer.
     */
    @Test
    public void testRemoveObserverExistingObserverShouldRemove() {
        ClientNotification observer = Mockito.mock(ClientNotification.class);
        when(observer.getClientId()).thenReturn(1L);
        this.productObserverManage.addObserver(observer);

        this.productObserverManage.removeObserver(1L);

        Product product = new Product(1, "name", "description", 10, "carModel", 1);

        this.productObserverManage.notifyObservers(product);

        Mockito.verify(observer, never()).update(product);
    }


    /**
     * Method to test the removeObserver method when it should not remove a non-existing observer.
     */
    @Test
    public void testRemoveObserverNotExistingObserverShouldNotRemove() {
        ClientNotification observer = Mockito.mock(ClientNotification.class);
        when(observer.getClientId()).thenReturn(1L);
        this.productObserverManage.addObserver(observer);

        this.productObserverManage.removeObserver(2L);

        Product product = new Product(1, "name", "description", 10, "carModel", 1);

        this.productObserverManage.notifyObservers(product);

        Mockito.verify(observer).update(product);
    }

    /**
     * Method to test the notifyObservers method.
     */
    @Test
    public void testNotifyObservers() {
        ClientNotification observer1 = Mockito.mock(ClientNotification.class);

        when(observer1.getEmail()).thenReturn("email1@email.com");

        this.productObserverManage.addObserver(observer1);

        Product product = new Product(1, "name", "description", 10, "carModel", 1);

        this.productObserverManage.notifyObservers(product);

        Mockito.verify(observer1).update(product);
    }

    /**
     * Method to test the setupObservers method, especially the findByOrderOptionNewsletterTrue method.
     */
    @Test
    public void testSetupObserversFindByNewsletter() {
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        OrderClient orderClient1 = new OrderClient(2, "name2", "email2", "phoneNumber2", "county2", "city2", "address2", 2, 20);
        when(this.orderClientContract.findByOrderOptionNewsletterTrue()).thenReturn(List.of(orderClient, orderClient1));
        this.productObserverManage.setupObservers();
        Mockito.verify(this.orderClientContract).findByOrderOptionNewsletterTrue();
    }

}
