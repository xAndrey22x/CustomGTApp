package com.customGTApp.testing.service;

import com.customGTApp.data.OrderClientContract;
import com.customGTApp.data.OrderItemContract;
import com.customGTApp.data.ProductContract;
import com.customGTApp.data.ServiceProdContract;
import com.customGTApp.model.OrderClient;
import com.customGTApp.model.OrderItem;
import com.customGTApp.model.Product;
import com.customGTApp.model.ServiceProd;
import com.customGTApp.service.OrderItemService;
import com.customGTApp.service.impl.OrderItemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class OrderItemServiceTest {

    /**
     * The service to be tested
     */
    private OrderItemService orderItemService;

    /**
     * The mocks needed for the tests
     */
    @Mock
    private OrderItemContract orderItemContract;
    @Mock
    private ProductContract productContract;
    @Mock
    private OrderClientContract orderClientContract;
    @Mock
    private ServiceProdContract serviceProdContract;

    /**
     * Method to set up the tests
     */
    @Before
    public void setUp() {
        openMocks(this);
        orderItemService = new OrderItemServiceImpl(orderItemContract, productContract, orderClientContract, serviceProdContract);
    }

    /**
     * Method to test from the add product method if the findById method of product is called
     */
    @Test
    public void testAddProductToOrderFindByProduct(){
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        OrderItem orderItem = new OrderItem(1, 1, 10);
        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.of(product));

        this.orderItemService.addProductToOrder(1L, 1L, orderItem);

        verify(this.productContract).findById(1L);

    }
    /**
     * Method to test from the add product method if the findById method of order is called
     */
    @Test
    public void testAddProductToOrderFindByOrder(){
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        OrderItem orderItem = new OrderItem(1, 1, 10);
        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.of(product));
        when(this.orderClientContract.findById(1L)).thenReturn(java.util.Optional.of(orderClient));

        this.orderItemService.addProductToOrder(1L, 1L, orderItem);

        verify(this.orderClientContract).findById(1L);
    }

    /**
     * Method to test the addProductToOrder method and if the relation between product and orderItem is set
     */
    @Test
    public void testAddProductToOrderCheckProduct(){
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        OrderItem orderItem = new OrderItem(1, 1, 10);

        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.of(product));
        when(this.orderClientContract.findById(1L)).thenReturn(java.util.Optional.of(orderClient));
        when(this.orderItemContract.save(orderItem)).thenReturn(orderItem);

        OrderItem result = this.orderItemService.addProductToOrder(1L, 1L, orderItem);

        verify(this.orderItemContract).save(orderItem);
        assertEquals(orderItem.getProduct(), result.getProduct());
    }

    /**
     * Method to test the addProductToOrder method and if the relation between order and orderItem is set
     */
    @Test
    public void testAddProductToOrderCheckOrder(){
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        OrderItem orderItem = new OrderItem(1, 1, 10);

        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.of(product));
        when(this.orderClientContract.findById(1L)).thenReturn(java.util.Optional.of(orderClient));
        when(this.orderItemContract.save(orderItem)).thenReturn(orderItem);

        OrderItem result = this.orderItemService.addProductToOrder(1L, 1L, orderItem);

        verify(this.orderItemContract).save(orderItem);
        assertEquals(orderItem.getOrder(), result.getOrder());
    }

    /**
     * Method to test the addProductToOrder when the product is not found
     */
    @Test
    public void testAddProductToOrderProductNotFound(){
        OrderItem orderItem = new OrderItem(1, 1, 10);
        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.empty());

        OrderItem result = this.orderItemService.addProductToOrder(1L, 1L, orderItem);

        verify(this.orderItemContract, never()).save(orderItem);
        assertNull(result);
    }

    /**
     * Method to test the addProductToOrder when the order is not found
     */
    @Test
    public void testAddProductToOrderOrderNotFound(){
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        OrderItem orderItem = new OrderItem(1, 1, 10);
        when(this.productContract.findById(1L)).thenReturn(java.util.Optional.of(product));
        when(this.orderClientContract.findById(1L)).thenReturn(java.util.Optional.empty());

        OrderItem result = this.orderItemService.addProductToOrder(1L, 1L, orderItem);

        verify(this.orderItemContract, never()).save(orderItem);
        assertNull(result);
    }

    /**
     * Method to test from the add service method if the findById method of service is called
     */
    @Test
    public void testAddServiceToOrderFindByService(){
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        OrderItem orderItem = new OrderItem(1, 1, 10);
        when(this.serviceProdContract.findById(1L)).thenReturn(java.util.Optional.of(serviceProd));

        this.orderItemService.addServiceToOrder(1L, 1L, orderItem);

        verify(this.serviceProdContract).findById(1L);
    }

    /**
     * Method to test from the add service method if the findById method of order is called
     */
    @Test
    public void testAddServiceToOrderFindByOrder(){
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        OrderItem orderItem = new OrderItem(1, 1, 10);
        when(this.serviceProdContract.findById(1L)).thenReturn(java.util.Optional.of(serviceProd));
        when(this.orderClientContract.findById(1L)).thenReturn(java.util.Optional.of(orderClient));

        this.orderItemService.addServiceToOrder(1L, 1L, orderItem);

        verify(this.orderClientContract).findById(1L);
    }

    /**
     * Method to test the addServiceToOrder method and if the relation between service and orderItem is set
     */
    @Test
    public void testAddServiceToOrderCheckService(){
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        OrderItem orderItem = new OrderItem(1, 1, 10);

        when(this.serviceProdContract.findById(1L)).thenReturn(java.util.Optional.of(serviceProd));
        when(this.orderClientContract.findById(1L)).thenReturn(java.util.Optional.of(orderClient));
        when(this.orderItemContract.save(orderItem)).thenReturn(orderItem);

        OrderItem result = this.orderItemService.addServiceToOrder(1L, 1L, orderItem);

        verify(this.orderItemContract).save(orderItem);
        assertEquals(orderItem.getServiceProd(), result.getServiceProd());
    }

    /**
     * Method to test the addServiceToOrder method and if the relation between order and orderItem is set
     */
    @Test
    public void testAddServiceToOrderCheckOrder(){
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        OrderClient orderClient = new OrderClient(1, "name", "email", "phoneNumber", "county", "city", "address", 1, 10);
        OrderItem orderItem = new OrderItem(1, 1, 10);

        when(this.serviceProdContract.findById(1L)).thenReturn(java.util.Optional.of(serviceProd));
        when(this.orderClientContract.findById(1L)).thenReturn(java.util.Optional.of(orderClient));
        when(this.orderItemContract.save(orderItem)).thenReturn(orderItem);

        OrderItem result = this.orderItemService.addServiceToOrder(1L, 1L, orderItem);

        verify(this.orderItemContract).save(orderItem);
        assertEquals(orderItem.getOrder(), result.getOrder());
    }

    /**
     * Method to test the addServiceToOrder when the service is not found
     */
    @Test
    public void testAddServiceToOrderServiceNotFound(){
        OrderItem orderItem = new OrderItem(1, 1, 10);
        when(this.serviceProdContract.findById(1L)).thenReturn(java.util.Optional.empty());

        OrderItem result = this.orderItemService.addServiceToOrder(1L, 1L, orderItem);

        verify(this.orderItemContract, never()).save(orderItem);
        assertNull(result);
    }

    /**
     * Method to test the addServiceToOrder when the order is not found
     */
    @Test
    public void testAddServiceToOrderOrderNotFound(){
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        OrderItem orderItem = new OrderItem(1, 1, 10);
        when(this.serviceProdContract.findById(1L)).thenReturn(java.util.Optional.of(serviceProd));
        when(this.orderClientContract.findById(1L)).thenReturn(java.util.Optional.empty());

        OrderItem result = this.orderItemService.addServiceToOrder(1L, 1L, orderItem);

        verify(this.orderItemContract, never()).save(orderItem);
        assertNull(result);
    }


}
