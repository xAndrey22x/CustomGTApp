package com.customGTApp.testing.data;

import com.customGTApp.data.ProductContract;
import com.customGTApp.data.impljparepo.ProductData;
import com.customGTApp.data.repository.ProductRepo;
import com.customGTApp.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class ProductDataTest {

    /**
     * The ProductData instance to be tested
     */
    private ProductContract productContract;

    /**
     * The mock needed for the ProductContract dependency
     */
    @Mock
    private ProductRepo productRepo;

    /**
     * Method to set up the testing environment
     */
    @Before
    public void setUp() {
        openMocks(this);
        productContract = new ProductData(productRepo);
    }

    /**
     * Test the findAll method
     */
    @Test
    public void testFindAll() {
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        when(productRepo.findAll()).thenReturn(List.of(product));

        List<Product> products = this.productContract.findAll();

        verify(this.productRepo).findAll();

        assertEquals(products, List.of(product));
    }

    /**
     * Test the findById method
     **/
    @Test
    public void testFindById() {
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        when(productRepo.findById(1L)).thenReturn(java.util.Optional.of(product));

        Optional<Product> productOptional = this.productContract.findById(1L);

        verify(this.productRepo).findById(1L);

        assertEquals(productOptional, java.util.Optional.of(product));
    }

    /**
     * Test the findById method when the product is not found
     **/
    @Test
    public void testFindByIdNotFound() {
        when(productRepo.findById(1L)).thenReturn(Optional.empty());

        Optional<Product> productOptional = this.productContract.findById(1L);

        verify(this.productRepo).findById(1L);

        assertEquals(productOptional, Optional.empty());
    }

    /**
     * Test the save method
     **/
    @Test
    public void testSave() {
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        when(productRepo.save(product)).thenReturn(product);

        Product savedProduct = this.productContract.save(product);

        verify(this.productRepo).save(product);

        assertEquals(savedProduct, product);
    }

    /**
     * Test the deleteById method
     **/
    @Test
    public void testDeleteById() {
        this.productContract.deleteById(1L);

        verify(this.productRepo).deleteById(1L);
    }

}
