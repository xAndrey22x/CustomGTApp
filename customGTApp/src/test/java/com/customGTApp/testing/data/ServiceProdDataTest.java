package com.customGTApp.testing.data;

import com.customGTApp.data.ServiceProdContract;
import com.customGTApp.data.impljparepo.ServiceProdData;
import com.customGTApp.data.repository.ServiceProdRepo;
import com.customGTApp.model.ServiceProd;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class ServiceProdDataTest {

    /**
     * The ServiceProdData instance to be tested
     */
    private ServiceProdContract serviceProdContract;

    /**
     * The ServiceProdRepo mock to be used in the tests
     */
    @Mock
    private ServiceProdRepo serviceProdRepo;

    /**
     * Method to set up the ServiceProd instance and the ServiceProdRepo mock
     */
    @Before
    public void setUp() {
        openMocks(this);
        this.serviceProdContract = new ServiceProdData(this.serviceProdRepo);
    }

    /**
     * Test the findAll method
     */
    @Test
    public void testFindAll() {
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        when(this.serviceProdRepo.findAll()).thenReturn(List.of(serviceProd));

        List<ServiceProd> serviceProds = this.serviceProdContract.findAll();

        verify(this.serviceProdRepo).findAll();
        assertEquals(serviceProds, List.of(serviceProd));
    }

    /**
     * Test the findById method
     */
    @Test
    public void testFindById() {
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        when(this.serviceProdRepo.findById(1L)).thenReturn(java.util.Optional.of(serviceProd));

        Optional<ServiceProd> serviceProdOptional = this.serviceProdContract.findById(1L);

        verify(this.serviceProdRepo).findById(1L);
        assertEquals(serviceProdOptional, Optional.of(serviceProd));
    }

    /**
     * Test the findById method when the service prod is not found
     */
    @Test
    public void testFindByIdNotFound() {
        when(this.serviceProdRepo.findById(1L)).thenReturn(Optional.empty());

        Optional<ServiceProd> serviceProdOptional = this.serviceProdContract.findById(1L);

        verify(this.serviceProdRepo).findById(1L);
        assertEquals(serviceProdOptional, Optional.empty());
    }

    /**
     * Test the save method
     */
    @Test
    public void testSave() {
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        when(this.serviceProdRepo.save(serviceProd)).thenReturn(serviceProd);

        ServiceProd savedServiceProd = this.serviceProdContract.save(serviceProd);

        verify(this.serviceProdRepo).save(serviceProd);
        assertEquals(savedServiceProd, serviceProd);
    }

    /**
     * Test the deleteById method
     */
    @Test
    public void testDeleteById() {
        this.serviceProdContract.deleteById(1L);

        verify(this.serviceProdRepo).deleteById(1L);
    }

}
