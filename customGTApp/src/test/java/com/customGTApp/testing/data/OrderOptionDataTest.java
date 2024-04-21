package com.customGTApp.testing.data;

import com.customGTApp.data.OrderOptionContract;
import com.customGTApp.data.impljparepo.OrderOptionData;
import com.customGTApp.data.repository.OrderOptionRepo;
import com.customGTApp.model.OrderOption;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class OrderOptionDataTest {

    /**
     * The OrderOptionContract dependency to be tested
     */
    private OrderOptionContract orderOptionContract;

    /**
     * The mock needed for the tests
     */
    @Mock
    private OrderOptionRepo orderOptionRepo;

    /**
     * Method to set up the needed data for the tests
     */
    @Before
    public void setUp() {
        openMocks(this);
        orderOptionContract = new OrderOptionData(orderOptionRepo);
    }

    /**
     * Test the findAll method
     */
    @Test
    public void testFindAll() {
        OrderOption orderOption = new OrderOption(1, false, false);
        List<OrderOption> orderOptions = List.of(orderOption);
        when(orderOptionRepo.findAll()).thenReturn(orderOptions);

        List<OrderOption> result = orderOptionContract.findAll();

        assertEquals(orderOptions, result);
        verify(orderOptionRepo).findAll();
    }

    /**
     * Test the findById method
     */
    @Test
    public void testFindById() {
        OrderOption orderOption = new OrderOption(1, false, false);
        when(orderOptionRepo.findById(1L)).thenReturn(Optional.of(orderOption));

        Optional<OrderOption> result = orderOptionContract.findById(1L);

        assertEquals(Optional.of(orderOption), result);
        verify(orderOptionRepo).findById(1L);
    }

    /**
     * Test the findById method when the order option is not found
     */
    @Test
    public void testFindByIdNotFound() {
        when(orderOptionRepo.findById(1L)).thenReturn(Optional.empty());

        Optional<OrderOption> result = orderOptionContract.findById(1L);

        assertEquals(Optional.empty(), result);
        verify(orderOptionRepo).findById(1L);
    }

    /**
     * Test the save method
     */
    @Test
    public void testSave() {
        OrderOption orderOption = new OrderOption(1, false, false);
        when(orderOptionRepo.save(orderOption)).thenReturn(orderOption);

        OrderOption result = orderOptionContract.save(orderOption);

        assertEquals(orderOption, result);
        verify(orderOptionRepo).save(orderOption);
    }

    /**
     * Test the deleteById method
     */
    @Test
    public void testDeleteById() {
        orderOptionContract.deleteById(1L);

        verify(orderOptionRepo).deleteById(1L);
    }

    /**
     * Test the findByOrderClientId method
     */
    @Test
    public void testFindByOrderClientId() {
        OrderOption orderOption = new OrderOption(1, false, false);
        when(orderOptionRepo.findByOrderClientId(1L)).thenReturn(Optional.of(orderOption));

        Optional<OrderOption> result = orderOptionContract.findByOrderClientId(1L);

        assertEquals(Optional.of(orderOption), result);
        verify(orderOptionRepo).findByOrderClientId(1L);
    }

    /**
     * Test the findByOrderClientId method when the order option is not found
     */
    @Test
    public void testFindByOrderClientIdNotFound() {
        when(orderOptionRepo.findByOrderClientId(1L)).thenReturn(Optional.empty());

        Optional<OrderOption> result = orderOptionContract.findByOrderClientId(1L);

        assertEquals(Optional.empty(), result);
        verify(orderOptionRepo).findByOrderClientId(1L);
    }

}
