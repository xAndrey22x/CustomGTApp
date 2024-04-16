package com.customGTApp.testing.servicetest;

import com.customGTApp.data.OrderClientContract;
import com.customGTApp.data.OrderItemContract;
import com.customGTApp.data.ProductContract;
import com.customGTApp.model.Product;
import com.customGTApp.observerservice.impl.EmailService;
import com.customGTApp.service.ProductService;
import com.customGTApp.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductContract productContract;
    @Mock
    private OrderItemContract orderItemContract;
    @Mock
    private OrderClientContract orderClientContract;
    @Mock
    private EmailService emailService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.productService = new ProductServiceImpl(productContract, orderItemContract, orderClientContract, emailService);
    }

    @Test
    public void testAddProduct(){
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        this.productService.addProduct(product);
        Mockito.verify(this.productContract).save(product);
    }

    @Test
    public void testFindAllProducts(){
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        Product product2 = new Product(2, "name", "description", 10, "carModel", 1);
        List<Product> products = List.of(product, product2);
        when(this.productContract.findAll()).thenReturn(products);

        List<Product> result = this.productService.findAllProducts();

        Mockito.verify(this.productContract).findAll();

        assertEquals(products, result);


    }


}
