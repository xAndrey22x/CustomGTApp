package com.customGTApp.controller;

import com.customGTApp.model.Photo;
import com.customGTApp.service.PhotoService;
import com.customGTApp.service.impl.PhotoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * /photo/all - list all the photos
 * /photo/addProduct/{productId} - add a photo to a product based on his id, the photo will be received as a request body
 * /photo/addService/{serviceId} - add a photo to a service based on his id, the photo will be received as a request body
 * /photo/updateProduct - update photo of a product, the photo will be received as a request body
 * /photo/updateService - update photo of a service, the photo will be received as a request body
 * /photo/delete/{photoId} - delete photo based on the photo id
 */
@RestController
@RequestMapping("/photo")
public class PhotoController {

    /**
     * PhotoService object to handle the photo operations and to be able to use the methods in
     * the service layer
     */
    private final PhotoService photoService;

    public PhotoController(PhotoServiceImpl photoService) {
        this.photoService = photoService;
    }
    /**
     * Method to get all the photos from the database and return them as a list. Also calls the findAllPhotos method from the service layer
     * @return list of all photos
     */
    @GetMapping("/all")
    public ResponseEntity<List<Photo>> getAllPhotos() {
        List<Photo> photos = photoService.findAllPhotos();
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }
    /**
     * Method to add a photo to a product based on the product id and return it. Also calls the addPhotoToProduct method from the service layer
     * @param productId the product id
     * @param photo the photo we want to add
     * @return the added photo
     */
    @PostMapping("/addProduct/{productId}")
    public ResponseEntity<Photo> addPhotoProduct(@PathVariable("productId") Long productId, @RequestBody Photo photo) {
        Photo photo1 = photoService.addPhotoToProduct(productId, photo);
        if (photo1 != null)
            return new ResponseEntity<>(photo1, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * Method to add a photo to a service based on the service id and return it. Also calls the addPhotoToService method from the service layer
     * @param photo the photo we want to add
     * @return the added photo
     */
    @PostMapping("/addService/{serviceId}")
    public ResponseEntity<Photo> addPhotoService(@PathVariable("serviceId") Long productId, @RequestBody Photo photo) {
        Photo photo1 = photoService.addPhotoToService(productId, photo);
        if (photo1 != null)
            return new ResponseEntity<>(photo1, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * Method to update the photo of a product based on the product id and return it. Also calls the updatePhoto method from the service layer
     * @param photo the photo we want to update
     * @return the updated photo
     */
    @PutMapping("/updateProduct")
    public ResponseEntity<Photo> updatePhotoProduct(@RequestBody Photo photo){
        Photo photo1 = photoService.updatePhoto(photo, true);
        if (photo1 != null)
            return new ResponseEntity<>(photo1, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * Method to update the photo of a service based on the service id and return it. Also calls the updatePhoto method from the service layer
     * @param photo the photo we want to update
     * @return the updated photo
     */
    @PutMapping("/updateService")
    public ResponseEntity<Photo> updatePhotoService(@RequestBody Photo photo){
        Photo photo1 = photoService.updatePhoto(photo, false);
        if (photo1 != null)
            return new ResponseEntity<>(photo1, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * Method to delete a photo based on the photo id. Also calls the deletePhoto method from the service layer
     * @param photoId the photo id
     * @return status of the operation
     */
    @DeleteMapping("/delete/{photoId}")
    public ResponseEntity<?> deletePhoto(@PathVariable Long photoId) {
        photoService.deletePhoto(photoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
