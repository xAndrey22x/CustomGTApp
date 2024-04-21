package com.customGTApp.testing.service;

import com.customGTApp.data.OrderClientContract;
import com.customGTApp.data.OrderOptionContract;
import com.customGTApp.model.OrderClient;
import com.customGTApp.model.OrderOption;
import com.customGTApp.observerservice.impl.ClientNotification;
import com.customGTApp.observerservice.impl.EmailService;
import com.customGTApp.service.OrderOptionService;
import com.customGTApp.service.impl.OrderOptionServiceImpl;
import com.customGTApp.service.observermanagement.ProductObserverManage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class OrderOptionServiceTest {

    /**
     * The service to be tested
     */
    private OrderOptionService orderOptionService;

    /**
     * The mocks to be used in the tests
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
     * Method to set up the mocks and the service to be tested
     */
    @Before
    public void setup(){
        openMocks(this);
        this.orderOptionService = new OrderOptionServiceImpl(orderOptionContract, orderClientContract, productService, emailService);
    }

    /**
     * Method to test if the findBy it's called from addOrderOption method
     */
    @Test
    public void testAddOrderOptionFindBy(){
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 123, 123.0f);
        OrderOption orderOption = new OrderOption(1, false, false);

        when(this.orderClientContract.findById(1L)).thenReturn(Optional.of(orderClient));

        this.orderOptionService.addOrderOptions(1L, orderOption);

        verify(this.orderClientContract).findById(1L);
    }

    /**
     * Method to test if the observer is added to the list of observers if the newsletter is true
     */
    @Test
    public void testAddOrderOptionNewsletter(){
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 123, 123.0f);
        OrderOption orderOption = new OrderOption(1, true, false);
        ClientNotification clientNotification = new ClientNotification(orderClient.getId(), orderClient.getEmail(), emailService);

        when(this.orderClientContract.findById(1L)).thenReturn(Optional.of(orderClient));

        this.orderOptionService.addOrderOptions(1L, orderOption);

        verify(this.productService).addObserver(clientNotification);
    }

    /**
     * Method to test the functionality of the addOrderOption method and if the relation is set correctly
     */
    @Test
    public void testAddOrderOption(){
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 123, 123.0f);
        OrderOption orderOption = new OrderOption(1, true, false);

        when(this.orderClientContract.findById(1L)).thenReturn(Optional.of(orderClient));
        when(this.orderOptionContract.save(orderOption)).thenReturn(orderOption);

        OrderOption result = this.orderOptionService.addOrderOptions(1L, orderOption);

        verify(this.orderOptionContract).save(orderOption);
        assertEquals(orderOption.getOrderClient(), result.getOrderClient());
    }

    /**
     * Method to test if the observer is not added to the list of observers if the newsletter is false
     */
    @Test
    public void testAddOrderOptionNoNewsletter(){
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 123, 123.0f);
        OrderOption orderOption = new OrderOption(1, false, false);

        when(this.orderClientContract.findById(1L)).thenReturn(Optional.of(orderClient));
        when(this.orderOptionContract.save(orderOption)).thenReturn(orderOption);

        OrderOption result = this.orderOptionService.addOrderOptions(1L, orderOption);

        verify(this.productService, never()).addObserver(any());
        assertEquals(orderOption, result);
    }

    /**
     * Method to test the addOrderOption method when the client is not found
     */
    @Test
    public void testAddOrderOptionClientNotFound(){
        OrderOption orderOption = new OrderOption(1, false, false);

        when(this.orderClientContract.findById(1L)).thenReturn(Optional.empty());

        OrderOption result = this.orderOptionService.addOrderOptions(1L, orderOption);

        verify(this.orderOptionContract, never()).save(orderOption);
        assertNull(result);
    }

    /**
     * Method to test if the findBy it's called from updateNewsLetter method
     */
    @Test
    public void testUpdateNewsLetterFindBy(){
        OrderOption orderOption = new OrderOption(1, false, false);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 123, 123.0f);
        orderOption.setOrderClient(orderClient);

        when(this.orderOptionContract.findByOrderClientId(1L)).thenReturn(Optional.of(orderOption));

        this.orderOptionService.updateNewsLetter(1L, true);

        verify(this.orderOptionContract).findByOrderClientId(1L);
    }

    /**
     * Method to test if the observer is added to the list of observers if the newsletter is true
     */
    @Test
    public void testUpdateNewsLetterTrue(){
        OrderOption orderOption = new OrderOption(1, false, false);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 123, 123.0f);
        orderOption.setOrderClient(orderClient);
        ClientNotification clientNotification = new ClientNotification(orderClient.getId(), orderClient.getEmail(), emailService);

        when(this.orderOptionContract.findByOrderClientId(1L)).thenReturn(Optional.of(orderOption));

        this.orderOptionService.updateNewsLetter(1L, true);

        verify(this.productService).addObserver(clientNotification);
    }

    /**
     * Method to test if the observer is removed from the list of observers if the newsletter is false
     */
    @Test
    public void testUpdateNewsLetterFalse(){
        OrderOption orderOption = new OrderOption(1, true, false);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 123, 123.0f);
        orderOption.setOrderClient(orderClient);

        when(this.orderOptionContract.findByOrderClientId(1L)).thenReturn(Optional.of(orderOption));

        this.orderOptionService.updateNewsLetter(1L, false);

        verify(this.productService).removeObserver(orderClient.getId());
    }

    /**
     * Method to test the updateNewsLetter method and if the newsletter is set correctly
     */
    @Test
    public void testUpdateNewsLetterResulTrue(){
        OrderOption orderOption = new OrderOption(1, false, false);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 123, 123.0f);
        orderOption.setOrderClient(orderClient);

        when(this.orderOptionContract.findByOrderClientId(1L)).thenReturn(Optional.of(orderOption));
        when(this.orderOptionContract.save(orderOption)).thenReturn(orderOption);

        OrderOption result = this.orderOptionService.updateNewsLetter(1L, true);

        verify(this.orderOptionContract).save(orderOption);
        assertTrue(result.isNewsletter());
    }

    /**
     * Method to test the updateNewsLetter method and if the newsletter is set correctly
     */
    @Test
    public void testUpdateNewsLetterResulFalse(){
        OrderOption orderOption = new OrderOption(1, true, false);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 123, 123.0f);
        orderOption.setOrderClient(orderClient);

        when(this.orderOptionContract.findByOrderClientId(1L)).thenReturn(Optional.of(orderOption));
        when(this.orderOptionContract.save(orderOption)).thenReturn(orderOption);

        OrderOption result = this.orderOptionService.updateNewsLetter(1L, false);

        verify(this.orderOptionContract).save(orderOption);
        assertFalse(result.isNewsletter());
    }

    /**
     * Method to test the updateNewsLetter method when the order option is not found
     */
    @Test
    public void testUpdateNewsLetterNotFound(){
        when(this.orderOptionContract.findByOrderClientId(1L)).thenReturn(Optional.empty());

        OrderOption result = this.orderOptionService.updateNewsLetter(1L, true);

        verify(this.orderOptionContract, never()).save(any());
        assertNull(result);
    }

    /**
     * Method to test if the findBy of the order option it's called from updateOrderConfirmation method
     */
    @Test
    public void testUpdateOrderConfirmationFindBy(){
        OrderOption orderOption = new OrderOption(1, false, false);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 123, 123.0f);
        orderOption.setOrderClient(orderClient);

        when(this.orderOptionContract.findByOrderClientId(1L)).thenReturn(Optional.of(orderOption));

        this.orderOptionService.updateOrderConfirmation(1L, true);

        verify(this.orderOptionContract).findByOrderClientId(1L);
    }

    /**
     * Method to test if the findBy of the client which has that order option it's called from updateOrderConfirmation method
     */
    @Test
    public void testUpdateOrderConfirmationFindByClient(){
        OrderOption orderOption = new OrderOption(1, false, false);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 123, 123.0f);
        orderOption.setOrderClient(orderClient);

        when(this.orderOptionContract.findByOrderClientId(1L)).thenReturn(Optional.of(orderOption));
        when(this.orderClientContract.findById(1L)).thenReturn(Optional.of(orderClient));

        this.orderOptionService.updateOrderConfirmation(1L, true);

        verify(this.orderClientContract).findById(1L);
    }

    /**
     * Method to test the updateOrderConfirmation method and if the order is confirmed correctly
     */
    @Test
    public void testUpdateOrderConfirmationTrue(){
        OrderOption orderOption = new OrderOption(1, false, false);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 123, 123.0f);
        orderOption.setOrderClient(orderClient);

        when(this.orderOptionContract.findByOrderClientId(1L)).thenReturn(Optional.of(orderOption));
        when(this.orderClientContract.findById(1L)).thenReturn(Optional.of(orderClient));
        when(this.orderOptionContract.save(orderOption)).thenReturn(orderOption);

        OrderOption result = this.orderOptionService.updateOrderConfirmation(1L, true);

        verify(this.orderOptionContract).save(orderOption);
        assertTrue(result.isOrderConfirmed());
    }

    /**
     * Method to test the updateOrderConfirmation method and if the order is not confirmed correctly
     */
    @Test
    public void testUpdateOrderConfirmationFalse(){
        OrderOption orderOption = new OrderOption(1, true, false);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 123, 123.0f);
        orderOption.setOrderClient(orderClient);

        when(this.orderOptionContract.findByOrderClientId(1L)).thenReturn(Optional.of(orderOption));
        when(this.orderClientContract.findById(1L)).thenReturn(Optional.of(orderClient));
        when(this.orderOptionContract.save(orderOption)).thenReturn(orderOption);

        OrderOption result = this.orderOptionService.updateOrderConfirmation(1L, false);

        verify(this.orderOptionContract).save(orderOption);
        assertFalse(result.isOrderConfirmed());
    }

    /**
     * Method to test the updateOrderConfirmation method when the order option is not found
     */
    @Test
    public void testUpdateOrderConfirmationNotFound(){
        when(this.orderOptionContract.findByOrderClientId(1L)).thenReturn(Optional.empty());

        OrderOption result = this.orderOptionService.updateOrderConfirmation(1L, true);

        verify(this.orderOptionContract, never()).save(any());
        assertNull(result);
    }

}
