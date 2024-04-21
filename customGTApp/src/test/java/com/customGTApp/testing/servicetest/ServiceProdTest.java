package com.customGTApp.testing.servicetest;

import com.customGTApp.data.OrderItemContract;
import com.customGTApp.data.ServiceProdContract;
import com.customGTApp.model.OrderItem;
import com.customGTApp.model.Photo;
import com.customGTApp.model.ServiceProd;
import com.customGTApp.service.ServiceProdService;
import com.customGTApp.service.impl.ServiceProdServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ServiceProdTest {
    /**
     * The ServiceProdService object to be tested.
     */
    private ServiceProdService serviceProd;

    /**
     * All the mocks needed for the testing of the ServiceProdService object.
     */
    @Mock
    private ServiceProdContract serviceProdContract;
    @Mock
    private OrderItemContract orderItemContract;

    /**
     * Method to set up the mocks and the object to be tested.
     */
    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        this.serviceProd = new ServiceProdServiceImpl(serviceProdContract, orderItemContract);
    }

    /**
     * Method to test the addService method.
     */
    @Test
    public void testGetAllServices(){
        ServiceProd serviceProd1 = new ServiceProd(1, "name", "description", 10);
        ServiceProd serviceProd2 = new ServiceProd(2, "name2", "description2", 20);
        when(this.serviceProdContract.findAll()).thenReturn(List.of(serviceProd1, serviceProd2));
        List<ServiceProd> serviceProdList = this.serviceProd.getAllServices();
        Mockito.verify(this.serviceProdContract).findAll();
        assertEquals(serviceProdList, List.of(serviceProd1, serviceProd2));
    }

    /**
     * Method to test the getServiceById method when the service is found.
     */
    @Test
    public void testGetServiceById(){
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        when(this.serviceProdContract.findById(1L)).thenReturn(Optional.of(serviceProd));
        
        ServiceProd result = this.serviceProd.getServiceById(1L);

        Mockito.verify(this.serviceProdContract).findById(1L);
        assertEquals(result, serviceProd);
    }

    /**
     * Method to test the getServiceById method when the service is not found.
     */
    @Test
    public void testGetServiceByIdNotFound(){
        when(this.serviceProdContract.findById(1L)).thenReturn(Optional.empty());

        ServiceProd result = this.serviceProd.getServiceById(1L);

        Mockito.verify(this.serviceProdContract).findById(1L);
        assertNull(result);
    }

    /**
     * Method to test the addService method.
     */
    @Test
    public void testAddService(){
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        when(this.serviceProdContract.save(serviceProd)).thenReturn(serviceProd);

        ServiceProd result = this.serviceProd.addService(serviceProd);

        Mockito.verify(this.serviceProdContract).save(serviceProd);
        assertEquals(result, serviceProd);
    }

    /**
     * Method to test the addService method if the find by id is called.
     */
    @Test
    public void testUpdateServiceFindBy(){
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        when(this.serviceProdContract.findById(1L)).thenReturn(Optional.of(serviceProd));

        this.serviceProd.updateService(serviceProd);

        Mockito.verify(this.serviceProdContract).findById(1L);
    }

    /**
     * Method to test the updateService method when the service is found.
     */
    @Test
    public void testUpdateService(){
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        ServiceProd serviceProd1 = new ServiceProd(2, "2", "description2", 20);
        when(this.serviceProdContract.findById(1L)).thenReturn(Optional.of(serviceProd));
        when(this.serviceProdContract.save(serviceProd)).thenReturn(serviceProd1);

        ServiceProd result = this.serviceProd.updateService(serviceProd);

        Mockito.verify(this.serviceProdContract).save(serviceProd);
        assertEquals(result, serviceProd1);
    }

    /**
     * Method to test the updateService method when the service is not found.
     */
    @Test
    public void testUpdateServiceNotFound(){
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        when(this.serviceProdContract.findById(1L)).thenReturn(Optional.empty());

        ServiceProd result = this.serviceProd.updateService(serviceProd);

        Mockito.verify(this.serviceProdContract, Mockito.never()).save(serviceProd);
        assertNull(result);
    }

    /**
     * Method to test the deleteAllOrderItems method when the order items exist from the deleteServiceById method.
     */
    @Test
    public void testDeleteServiceByIdWhenOrderItemsExistDeleteOrderItemsCalled(){
        List<OrderItem> orderItems = List.of(new OrderItem(), new OrderItem());
        when(this.orderItemContract.findByServiceProdId(1L)).thenReturn(Optional.of(orderItems));

        this.serviceProd.deleteServiceById(1L);

        Mockito.verify(this.orderItemContract).deleteAll(orderItems);
    }

    /**
     * Method to test the deleteServiceById method when the order items exist.
     */
    @Test
    public void testDeleteServiceByIdWhenOrderItemsExistDeleteServiceCalled(){
        List<OrderItem> orderItems = List.of(new OrderItem(), new OrderItem());
        when(this.orderItemContract.findByServiceProdId(1L)).thenReturn(Optional.of(orderItems));

        this.serviceProd.deleteServiceById(1L);

        Mockito.verify(this.serviceProdContract).deleteById(1L);
    }

    /**
     * Method to test the deleteServiceById method when the order items do not exist.
     */
    @Test
    public void testDeleteServiceByIdWhenOrderItemsDoNotExist(){
        when(this.orderItemContract.findByServiceProdId(1L)).thenReturn(Optional.empty());

        this.serviceProd.deleteServiceById(1L);

        Mockito.verify(this.serviceProdContract).deleteById(1L);
    }

    /**
     * Method to test the deleteAllOrderItems method when the order items do not exist from the deleteServiceById method.
     */
    @Test
    public void testDeleteServiceByIdWhenOrderItemsDoNotExistDeleteOrderItemsNotCalled(){
        when(this.orderItemContract.findByServiceProdId(1L)).thenReturn(Optional.empty());

        this.serviceProd.deleteServiceById(1L);

        Mockito.verify(this.orderItemContract, Mockito.never()).deleteAll(any());
    }

    /**
     * Method to test the getAllServicePhotos method when the service is found.
     */
    @Test
    public void testGetAllServicePhotos(){
        ServiceProd serviceProd1 = new ServiceProd(1, "name", "description", 10);
        Photo photo1 = new Photo(1, "url");
        serviceProd1.addPhoto(photo1);

        when(this.serviceProdContract.findById(1L)).thenReturn(Optional.of(serviceProd1));

        List<Photo> photos = this.serviceProd.getAllServicePhotos(1L);

        Mockito.verify(this.serviceProdContract).findById(1L);

        assertEquals(photos, serviceProd1.getPhotos());
    }

    /**
     * Method to test the getAllServicePhotos method when the service is not found.
     */
    @Test
    public void testGetAllServicePhotosNotFound(){
        when(this.serviceProdContract.findById(1L)).thenReturn(Optional.empty());

        List<Photo> photos = this.serviceProd.getAllServicePhotos(1L);

        Mockito.verify(this.serviceProdContract).findById(1L);

        assertNull(photos);
    }

    /**
     * Method to test the updatePrice method.
     */
    @Test
    public void testUpdatePrice(){
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        when(this.serviceProdContract.findById(1L)).thenReturn(Optional.of(serviceProd));
        when(this.serviceProdContract.save(serviceProd)).thenReturn(serviceProd);

        ServiceProd result = this.serviceProd.updatePrice(1L, 20);

        Mockito.verify(this.serviceProdContract).save(serviceProd);

        assertEquals(result.getPrice(), 20, 0.0);
    }

    /**
     * Method to test the updatePrice method when the service is not found.
     */
    @Test
    public void testUpdatePriceNotFound(){
        when(this.serviceProdContract.findById(1L)).thenReturn(Optional.empty());

        this.serviceProd.updatePrice(1L, 20);

        Mockito.verify(this.serviceProdContract, Mockito.never()).save(any());
    }

}
