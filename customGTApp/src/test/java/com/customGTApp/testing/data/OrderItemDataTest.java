package com.customGTApp.testing.data;

import com.customGTApp.data.OrderItemContract;
import com.customGTApp.data.impljparepo.OrderItemData;
import com.customGTApp.data.repository.OrderItemRepo;
import com.customGTApp.model.OrderItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class OrderItemDataTest {

    /**
     * The OrderItemContract dependency to be tested
     */
    private OrderItemContract orderItemContract;

    /**
     * The mock needed for the tests
     */
    @Mock
    private OrderItemRepo orderItemRepo;

    /**
     * Method to set up the needed objects for the tests
     */
    @Before
    public void setUp() {
        openMocks(this);
        orderItemContract = new OrderItemData(orderItemRepo);
    }

    /**
     * Test the findAll method
     */
    @Test
    public void testFindAll() {
        OrderItem orderItem = new OrderItem(1, 1,  10);
        List<OrderItem> orderItems = List.of(orderItem);
        when(orderItemRepo.findAll()).thenReturn(orderItems);

        List<OrderItem> result = orderItemContract.findAll();

        assertEquals(orderItems, result);
        verify(orderItemRepo).findAll();
    }

    /**
     * Test the findById method
     */
    @Test
    public void testFindById() {
        OrderItem orderItem = new OrderItem(1, 1,  10);
        when(orderItemRepo.findById(1L)).thenReturn(Optional.of(orderItem));

        Optional<OrderItem> result = orderItemContract.findById(1L);

        assertEquals(Optional.of(orderItem), result);
        verify(orderItemRepo).findById(1L);
    }

    /**
     * Test the findById method when the order item is not found
     */
    @Test
    public void testFindByIdNotFound() {
        when(orderItemRepo.findById(1L)).thenReturn(Optional.empty());

        Optional<OrderItem> result = orderItemContract.findById(1L);

        assertEquals(Optional.empty(), result);
        verify(orderItemRepo).findById(1L);
    }

    /**
     * Test the save method
     */
    @Test
    public void testSave() {
        OrderItem orderItem = new OrderItem(1, 1,  10);
        when(orderItemRepo.save(orderItem)).thenReturn(orderItem);

        OrderItem result = orderItemContract.save(orderItem);

        assertEquals(orderItem, result);
        verify(orderItemRepo).save(orderItem);
    }

    /**
     * Test the deleteById method
     */
    @Test
    public void testDeleteById() {
        orderItemContract.deleteById(1L);

        verify(orderItemRepo).deleteById(1L);
    }

    /**
     * Test the findByProductId method
     */
    @Test
    public void testFindByProductId() {
        OrderItem orderItem = new OrderItem(1, 1,  10);
        List<OrderItem> orderItems = List.of(orderItem);
        when(orderItemRepo.findByProductId(1L)).thenReturn(Optional.of(orderItems));

        Optional<List<OrderItem>> result = orderItemContract.findByProductId(1L);

        assertEquals(Optional.of(orderItems), result);
        verify(orderItemRepo).findByProductId(1L);
    }

    /**
     * Test the findByProductId method when the order item is not found
     */
    @Test
    public void testFindByProductIdNotFound(){
        when(orderItemRepo.findByProductId(1L)).thenReturn(Optional.empty());

        Optional<List<OrderItem>> result = orderItemContract.findByProductId(1L);

        assertEquals(Optional.empty(), result);
        verify(orderItemRepo).findByProductId(1L);
    }

    /**
     * Test the findByServiceProdId method
     */
    @Test
    public void testFindByServiceProdId() {
        OrderItem orderItem = new OrderItem(1, 1,  10);
        List<OrderItem> orderItems = List.of(orderItem);
        when(orderItemRepo.findByServiceProdId(1L)).thenReturn(Optional.of(orderItems));

        Optional<List<OrderItem>> result = orderItemContract.findByServiceProdId(1L);

        assertEquals(Optional.of(orderItems), result);
        verify(orderItemRepo).findByServiceProdId(1L);
    }

    /**
     * Test the findByServiceProdId method when the order item is not found
     */
    @Test
    public void testFindByServiceProdIdNotFound(){
        when(orderItemRepo.findByServiceProdId(1L)).thenReturn(Optional.empty());

        Optional<List<OrderItem>> result = orderItemContract.findByServiceProdId(1L);

        assertEquals(Optional.empty(), result);
        verify(orderItemRepo).findByServiceProdId(1L);
    }

    /**
     * Test the deleteAll method
     */
    @Test
    public void testDeleteAll() {
        OrderItem orderItem = new OrderItem(1, 1,  10);
        List<OrderItem> orderItems = List.of(orderItem);
        orderItemContract.deleteAll(orderItems);

        verify(orderItemRepo).deleteAll(orderItems);
    }

}
