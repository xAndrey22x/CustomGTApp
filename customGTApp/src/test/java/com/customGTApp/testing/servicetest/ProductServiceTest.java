package com.customGTApp.testing.servicetest;

import com.customGTApp.data.OrderClientContract;
import com.customGTApp.data.OrderItemContract;
import com.customGTApp.data.ProductContract;
import com.customGTApp.model.OrderItem;
import com.customGTApp.model.Photo;
import com.customGTApp.model.Product;
import com.customGTApp.observerservice.impl.EmailService;
import com.customGTApp.service.ProductService;
import com.customGTApp.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    /**
     * The ProductService object to test.
     */
    private ProductService productService;

    /**
     * All the mocks needed for the ProductService object.
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
        this.productService = new ProductServiceImpl(productContract, orderItemContract, orderClientContract, emailService);
    }

    /**
     * Method to test the addProduct method.
     */
    @Test
    public void testAddProduct(){
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        this.productService.addProduct(product);
        Mockito.verify(this.productContract).save(product);
    }

    /**
     * Method to test the findAllProducts method.
     */
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

    /**
     * Method to test the findProductById method.
     */
    @Test
    public void testFindProductById(){
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.of(product));

        Product result = this.productService.findProductById(1L);

        Mockito.verify(this.productContract).findById(1L);

        assertEquals(product, result);
    }

    /**
     * Method to test the findProductById method when the product is not found.
     */
    @Test
    public void testFindProductByIdNotFound(){
        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.empty());

        Product result = this.productService.findProductById(1L);

        Mockito.verify(this.productContract).findById(1L);

        assertNull(result);
    }


    /**
     * Method to test the updateProduct method.
     */
    @Test
    public void testUpdateProduct(){
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.of(product));
        when(this.productContract.save(product)).thenReturn(product);

        Product result = this.productService.updateProduct(product);

        Mockito.verify(this.productContract).save(product);

        assertNotNull(result);

    }

    /**
     * Method to test the updateProduct method when the product is not found.
     */
    @Test
    public void testUpdateProductNotFound(){
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.empty());

        Product result = this.productService.updateProduct(product);

        Mockito.verify(this.productContract, Mockito.never()).save(product);

        assertNull(result);

    }

    /**
     * Method to test the deleteAll method called from the deleteProductById method when order items exist.
     */
    @Test
    public void testDeleteProductByIdWhenOrderItemsExistDeleteOrderItemsCalled() {
        Long productId = 1L;
        List<OrderItem> orderItems = Arrays.asList(new OrderItem(), new OrderItem());
        when(orderItemContract.findByProductId(productId)).thenReturn(Optional.of(orderItems));

        productService.deleteProductById(productId);

        Mockito.verify(orderItemContract).deleteAll(orderItems);
    }

    /**
     * Method to test the deleteById method called from the deleteProductById method when order items exist.
     */
    @Test
    public void testDeleteProductByIdWhenOrderItemsExistDeleteProductCalled() {
        Long productId = 1L;
        List<OrderItem> orderItems = Arrays.asList(new OrderItem(), new OrderItem());
        when(orderItemContract.findByProductId(productId)).thenReturn(Optional.of(orderItems));

        productService.deleteProductById(productId);

        Mockito.verify(productContract).deleteById(productId);
    }

    /**
     * Method to test the deleteById method called from the deleteProductById method when no order items exist.
     */
    @Test
    public void testDeleteProductByIdWhenNoOrderItemsExistDeleteProductCalled() {
        Long productId = 1L;
        when(orderItemContract.findByProductId(productId)).thenReturn(Optional.empty());

        productService.deleteProductById(productId);

        Mockito.verify(productContract).deleteById(productId);
    }

    /**
     * Method to test the deleteAll method called from the deleteProductById method when no order items exist.
     */
    @Test
    public void testDeleteProductByIdWhenNoOrderItemsExistDeleteOrderItemsNotCalled() {
        Long productId = 1L;
        when(orderItemContract.findByProductId(productId)).thenReturn(Optional.empty());

        productService.deleteProductById(productId);

        Mockito.verify(orderItemContract, Mockito.never()).deleteAll(any());
    }

    /**
     * Method to test the findPhotosByProductId method.
     */
    @Test
    public void testFindPhotosByProductId(){
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.of(product));

        List<Photo> result = this.productService.findPhotosByProductId(1L);

        Mockito.verify(this.productContract).findById(1L);

        assertNotNull(result);
    }

    /**
     * Method to test the findPhotosByProductId method when the product is not found.
     */
    @Test
    public void testFindPhotosByProductIdNotFound(){
        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.empty());

        List<Photo> result = this.productService.findPhotosByProductId(1L);

        Mockito.verify(this.productContract).findById(1L);

        assertNull(result);
    }

    /**
     * Method to test the updateQuantity method.
     */
    @Test
    public void testUpdateQuantity(){
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.of(product));
        when(this.productContract.save(product)).thenReturn(product);

        Product result = this.productService.updateQuantity(1L, 20);

        Mockito.verify(this.productContract).save(product);

        assertEquals(20, result.getQuantity());
    }

    /**
     * Method to test the updateQuantity method when the product is not found.
     */
    @Test
    public void testUpdateQuantityNotFound(){
        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.empty());

        Product result = this.productService.updateQuantity(1L, 20);

        Mockito.verify(this.productContract, Mockito.never()).save(any());

        assertNull(result);
    }

    /**
     * Method to test the updatePrice method.
     */
    @Test
    public void testUpdatePrice(){
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.of(product));
        when(this.productContract.save(product)).thenReturn(product);

        Product result = this.productService.updatePrice(1L, 20);

        Mockito.verify(this.productContract).save(product);

        assertEquals(20, result.getPrice(), 0.0);
    }

    /**
     * Method to test the updatePrice method when the product is not found.
     */
    @Test
    public void testUpdatePriceNotFound(){
        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.empty());

        Product result = this.productService.updatePrice(1L, 20);

        Mockito.verify(this.productContract, Mockito.never()).save(any());

        assertNull(result);
    }

}
