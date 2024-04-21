package com.customGTApp.testing.servicetest;

import com.customGTApp.data.OrderClientContract;
import com.customGTApp.model.OrderClient;
import com.customGTApp.service.OrderClientService;
import com.customGTApp.service.impl.OrderClientServiceImpl;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class OrderClientTest {

    /**
     * The service to be tested
     */
    private OrderClientService orderClientService;

    /**
     * The mocks needed for the tests
     */
    @Mock
    private OrderClientContract orderClientContract;

    /**
     * Method to set up the tests
     */
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.orderClientService = new OrderClientServiceImpl(orderClientContract);
    }

    /**
     * Method to test the find all orders method
     */
    @Test
    public void testFindAllOrders() {
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        OrderClient orderClient1 = new OrderClient(2, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        when(orderClientContract.findAll()).thenReturn(java.util.List.of(orderClient, orderClient1));
        List<OrderClient> orderClients = this.orderClientService.findAllOrders();
        verify(orderClientContract).findAll();
        assertEquals(orderClients, List.of(orderClient, orderClient1));
    }

    /**
     * Method to test the add order method
     */
    @Test
    public void testAddOrder() {
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        when(orderClientContract.save(orderClient)).thenReturn(orderClient);

        this.orderClientService.addOrder(orderClient);

        verify(orderClientContract).save(orderClient);

    }

    /**
     * Method to test from update order if the findById method is called
     */
    @Test
    public void testUpdateOrderFindBy() {
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        when(orderClientContract.findById(1L)).thenReturn(java.util.Optional.of(orderClient));

        this.orderClientService.updateOrder(orderClient);

        verify(orderClientContract).findById(1L);
    }

    /**
     * Method to test the update order method
     */
    @Test
    public void testUpdateOrder() {
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        when(orderClientContract.findById(1L)).thenReturn(java.util.Optional.of(orderClient));
        when(orderClientContract.save(orderClient)).thenReturn(orderClient);

        OrderClient result = this.orderClientService.updateOrder(orderClient);

        verify(orderClientContract).save(orderClient);
        assertEquals(orderClient, result);
    }

    /**
     * Method to test the update order method when the order is not found
     */
    @Test
    public void testUpdateOrderNotFound(){
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        when(orderClientContract.findById(1L)).thenReturn(java.util.Optional.empty());

        OrderClient result = this.orderClientService.updateOrder(orderClient);

        verify(orderClientContract, never()).save(orderClient);
        assertNull(result);
    }

    /**
     * Method to test the delete order method
     */
    @Test
    public void testDeleteOrder() {
        this.orderClientService.deleteOrder(1L);
        verify(orderClientContract).deleteById(1L);
    }

    /**
     * Method to test the find all orders confirmed method
     */
    @Test
    public void testFindAllOrderConfirmed() {
        this.orderClientService.findAllOrderConfirmed();
        verify(orderClientContract).findByOrderOptionOrderConfirmedTrue();
    }

    /**
     * Method to test the find all orders not confirmed method
     */
    @Test
    public void testFindAllOrderNotConfirmed() {
        this.orderClientService.findAllOrderNotConfirmed();
        verify(orderClientContract).findByOrderOptionOrderConfirmedFalse();
    }

}
