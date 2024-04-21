package com.customGTApp.testing.service;

import com.customGTApp.data.OrderClientContract;
import com.customGTApp.data.OrderOptionContract;
import com.customGTApp.observerservice.impl.ClientNotification;
import com.customGTApp.observerservice.impl.EmailService;
import com.customGTApp.service.impl.OrderOptionServiceImpl;
import com.customGTApp.service.observermanagement.OrderOptionManage;
import com.customGTApp.service.observermanagement.ProductObserverManage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.MockitoAnnotations.openMocks;

public class OrderOptionObserverTest {

    /**
     * The OrderOptionManage object to be tested.
     */
    private OrderOptionManage orderOptionService;

    /**
     * All the mocks needed for the testing of the OrderOptionManage object.
     */
    @Mock
    private OrderOptionContract orderOptionContract;
    @Mock
    private OrderClientContract orderClientContract;
    @Mock
    private ProductObserverManage productService;
    @Mock
    private EmailService emailService;

    /**
     * Method to set up the mocks and the object to be tested.
     */
    @Before
    public void setUp(){
        openMocks(this);
        this.orderOptionService = new OrderOptionServiceImpl(orderOptionContract, orderClientContract, productService, emailService);
    }

    /**
     * Method to test the addObserver method.
     */
    @Test
    public void testAddObserver(){
        ClientNotification observer = Mockito.mock(ClientNotification.class);

        this.orderOptionService.addObserver(observer);
        this.orderOptionService.notifyObservers("email@email.com");

        Mockito.verify(observer).update("email@email.com");
    }

    /**
     * Method to test the removeObserver method.
     */
    @Test
    public void testRemoveObserver(){
        ClientNotification observer = Mockito.mock(ClientNotification.class);

        this.orderOptionService.addObserver(observer);
        this.orderOptionService.removeObserver(observer);
        this.orderOptionService.notifyObservers("email@email.com");

        Mockito.verify(observer, Mockito.never()).update("email@email.com");
    }

    /**
     * Method to test the notifyObservers method.
     */
    @Test
    public void testNotifyObservers() {
        ClientNotification observer = Mockito.mock(ClientNotification.class);

        this.orderOptionService.notifyObservers("email@email.com");

        Mockito.verify(observer, Mockito.never()).update("email@email.com");

    }
}
