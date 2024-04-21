package com.customGTApp.testing.data;

import com.customGTApp.data.OrderClientContract;
import com.customGTApp.data.impljparepo.OrderClientData;
import com.customGTApp.data.repository.OrderClientRepo;
import com.customGTApp.model.OrderClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class OrderClientDataTest {

    /**
     * The OrderClientContract dependency to be tested
     */
    private OrderClientContract orderClientContract;

    /**
     * The mock needed for the OrderClientContract dependency
     */
    @Mock
    private OrderClientRepo orderClientRepo;

    /**
     * Method to test the findAll method of the OrderClientData class
     */
    @Before
    public void setUp() {
        openMocks(this);
        orderClientContract = new OrderClientData(orderClientRepo);
    }

    /**
     * Method to test the findAll method of the OrderClientData class
     */
    @Test
    public void testFindAll() {
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        List<OrderClient> orderClients = List.of(orderClient);
        when(orderClientRepo.findAll()).thenReturn(orderClients);

        List<OrderClient> result = orderClientContract.findAll();

        assertEquals(orderClients, result);
        verify(orderClientRepo).findAll();
    }

    /**
     * Method to test the findById method of the OrderClientData class
     */
    @Test
    public void testFindById() {
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        when(orderClientRepo.findById(1L)).thenReturn(Optional.of(orderClient));

        Optional<OrderClient> result = orderClientContract.findById(1L);

        assertEquals(Optional.of(orderClient), result);
        verify(orderClientRepo).findById(1L);
    }

    /**
     * Method to test the findById method of the OrderClientData class when the order client is not found
     */
    @Test
    public void testFindByIdNotFound() {
        when(orderClientRepo.findById(1L)).thenReturn(Optional.empty());

        Optional<OrderClient> result = orderClientContract.findById(1L);

        assertEquals(Optional.empty(), result);
        verify(orderClientRepo).findById(1L);
    }

    /**
     * Method to test the save method of the OrderClientData class
     */
    @Test
    public void testSave() {
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        when(orderClientRepo.save(orderClient)).thenReturn(orderClient);

        OrderClient result = orderClientContract.save(orderClient);

        assertEquals(orderClient, result);
        verify(orderClientRepo).save(orderClient);
    }

    /**
     * Method to test the deleteById method of the OrderClientData class
     */
    @Test
    public void testDeleteById() {
        orderClientContract.deleteById(1L);

        verify(orderClientRepo).deleteById(1L);
    }

    /**
     * Method to test the findByOrderOptionNewsletterTrue method of the OrderClientData class
     */
    @Test
    public void testFindByOrderOptionNewsletterTrue() {
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        List<OrderClient> orderClients = List.of(orderClient);
        when(orderClientRepo.findByOrderOptionNewsletterTrue()).thenReturn(orderClients);

        List<OrderClient> result = orderClientContract.findByOrderOptionNewsletterTrue();

        assertEquals(orderClients, result);
        verify(orderClientRepo).findByOrderOptionNewsletterTrue();
    }

    /**
     * Method to test the findByOrderOptionOrderConfirmedTrue method of the OrderClientData class
     */
    @Test
    public void testFindByOrderOptionConfirmedTrue() {
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        List<OrderClient> orderClients = List.of(orderClient);
        when(orderClientRepo.findByOrderOptionOrderConfirmedTrue()).thenReturn(orderClients);

        List<OrderClient> result = orderClientContract.findByOrderOptionOrderConfirmedTrue();

        assertEquals(orderClients, result);
        verify(orderClientRepo).findByOrderOptionOrderConfirmedTrue();
    }

    /**
     * Method to test the findByOrderOptionOrderConfirmedFalse method of the OrderClientData class
     */
    @Test
    public void testFindByOrderOptionConfirmedFalse() {
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        List<OrderClient> orderClients = List.of(orderClient);
        when(orderClientRepo.findByOrderOptionOrderConfirmedFalse()).thenReturn(orderClients);

        List<OrderClient> result = orderClientContract.findByOrderOptionOrderConfirmedFalse();

        assertEquals(orderClients, result);
        verify(orderClientRepo).findByOrderOptionOrderConfirmedFalse();
    }

}
