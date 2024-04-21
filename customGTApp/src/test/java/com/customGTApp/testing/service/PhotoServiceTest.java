package com.customGTApp.testing.service;

import com.customGTApp.data.PhotoContract;
import com.customGTApp.data.ProductContract;
import com.customGTApp.data.ServiceProdContract;
import com.customGTApp.model.Photo;
import com.customGTApp.model.Product;
import com.customGTApp.model.ServiceProd;
import com.customGTApp.service.PhotoService;
import com.customGTApp.service.impl.PhotoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

public class PhotoServiceTest {

    /**
     * The PhotoService object to be tested.
     */
    private PhotoService photoService;

    /**
     * All the mocks needed for the testing of the PhotoService object.
     */
    @Mock
    private PhotoContract photoContract;
    @Mock
    private ProductContract productContract;
    @Mock
    private ServiceProdContract serviceProdContract;

    /**
     * Method to set up the mocks and the object to be tested.
     */
    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        this.photoService = new PhotoServiceImpl(photoContract, productContract, serviceProdContract);
    }

    /**
     * Method to test the findAllPhotos method.
     */
    @Test
    public void testFindAllPhotos(){
        this.photoService.findAllPhotos();
        Mockito.verify(this.photoContract).findAll();
    }

    /**
     * Method to test the addPhotoToProduct if the find by id is called.
     */
    @Test
    public void testAddPhotoToProductFindBy(){
        Photo photo = new Photo(1, "url");
        this.photoService.addPhotoToProduct(1L, photo);

        Mockito.verify(this.productContract).findById(1L);
    }

    /**
     * Method to test the addPhotoToProduct if the product is found.
     */
    @Test
    public void testAddPhotoToProductFound(){
        Photo photo = new Photo(1, "url");
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        when(this.productContract.findById(1L)).thenReturn(Optional.of(product));
        when(this.photoContract.save(photo)).thenReturn(photo);

        this.photoService.addPhotoToProduct(1L, photo);

        Mockito.verify(this.photoContract).save(photo);

        assertEquals(photo.getProduct(), product);
    }

    /**
     * Method to test the addPhotoToProduct if the product is not found.
     */
    @Test
    public void testAddPhotoToProductNotFound(){
        Photo photo = new Photo(1, "url");
        when(this.productContract.findById(1L)).thenReturn(Optional.empty());
        when(this.photoContract.save(photo)).thenReturn(photo);

        Photo result = this.photoService.addPhotoToProduct(1L, photo);

        Mockito.verify(this.productContract).findById(1L);
        assertNull(result);
    }

    /**
     * Method to test the addPhotoToService if the find by id is called.
     */
    @Test
    public void testAddPhotoToServiceFindBy(){
        Photo photo = new Photo(1, "url");
        this.photoService.addPhotoToService(1L, photo);

        Mockito.verify(this.serviceProdContract).findById(1L);
    }

    /**
     * Method to test the addPhotoToService if the service is found.
     */
    @Test
    public void testAddPhotoToServiceFound(){
        Photo photo = new Photo(1, "url");
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        when(this.serviceProdContract.findById(1L)).thenReturn(Optional.of(serviceProd));
        when(this.photoContract.save(photo)).thenReturn(photo);

        this.photoService.addPhotoToService(1L, photo);

        Mockito.verify(this.photoContract).save(photo);

        assertEquals(photo.getServiceProd(), serviceProd);
    }

    /**
     * Method to test the addPhotoToService if the service is not found.
     */
    @Test
    public void testAddPhotoToServiceNotFound(){
        Photo photo = new Photo(1, "url");
        when(this.serviceProdContract.findById(1L)).thenReturn(Optional.empty());
        when(this.photoContract.save(photo)).thenReturn(photo);

        Photo result = this.photoService.addPhotoToService(1L, photo);

        Mockito.verify(this.serviceProdContract).findById(1L);
        assertNull(result);
    }

    /**
     * Method to test the updatePhoto if the find by id is called.
     */
    @Test
    public void testUpdatePhotoFindBy(){
        Photo photo = new Photo(1, "url");
        when(this.photoContract.findById(1L)).thenReturn(Optional.of(photo));

        this.photoService.updatePhoto(photo, true);

        Mockito.verify(this.photoContract).findById(1L);
    }


    /**
     * Method to test the updatePhoto to product.
     */
    @Test
    public void testUpdatePhotoProduct(){
        Photo photo = new Photo(1, "url");
        Product product = new Product(1, "name", "description", 10, "carModel", 1);
        photo.setProduct(product);

        Photo newPhoto = new Photo(1, "newUrl");
        newPhoto.setProduct(product);

        when(this.photoContract.findById(1L)).thenReturn(Optional.of(photo));
        when(this.photoContract.save(newPhoto)).thenReturn(newPhoto);

        Photo result = this.photoService.updatePhoto(newPhoto, true);

        Mockito.verify(this.photoContract).save(newPhoto);

        assertEquals(result.getProduct(), product);
    }

    /**
     * Method to test the updatePhoto to service.
     */
    @Test
    public void testUpdatePhotoService(){
        Photo photo = new Photo(1, "url");
        ServiceProd serviceProd = new ServiceProd(1, "name", "description", 10);
        photo.setServiceProd(serviceProd);

        Photo newPhoto = new Photo(1, "newUrl");
        newPhoto.setServiceProd(serviceProd);

        when(this.photoContract.findById(1L)).thenReturn(Optional.of(photo));
        when(this.photoContract.save(newPhoto)).thenReturn(newPhoto);

        Photo result = this.photoService.updatePhoto(newPhoto, false);

        Mockito.verify(this.photoContract).save(newPhoto);

        assertEquals(result.getServiceProd(), serviceProd);
    }

    /**
     * Method to test the updatePhoto if the photo is not found.
     */
    @Test
    public void testUpdatePhotoNotFound(){
        Photo photo = new Photo(1, "url");
        when(this.photoContract.findById(1L)).thenReturn(Optional.empty());

        Photo result = this.photoService.updatePhoto(photo, true);

        Mockito.verify(this.photoContract, never()).save(photo);
        assertNull(result);
    }

    /**
     * Method to test the deletePhoto.
     */
    @Test
    public void testDeletePhoto(){
        this.photoService.deletePhoto(1L);
        Mockito.verify(this.photoContract).deleteById(1L);
    }

}
