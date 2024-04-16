package com.customGTApp.service;

import com.customGTApp.model.Photo;

import java.util.List;
/**
 * PhotoService interface that will be implemented by PhotoServiceImpl and other classes,
 * making it easier to manage the Photo model. Also, making the code more readable and easier to maintain.
 */
public interface PhotoService {
    List<Photo> findAllPhotos();

    /**
     * Method to add a photo to product based on the id
     * @param productId id of the product we want to add the photo
     * @param photo the photo we want to add
     * @return returns the added photo
     */
    Photo addPhotoToProduct(Long productId, Photo photo);
    /**
     * Method to add a photo to service based on the id
     * @param serviceProdId id of the service we want to add the photo
     * @param photo the photo we want to add
     * @return returns the added photo
     */
    Photo addPhotoToService(Long serviceProdId, Photo photo);

    /**
     * Method to update the photo information, and it can be a photo for a product or for a service
     * @param p new photo information
     * @param isProduct if it's for a product or for a service
     * @return the photo updated
     */
    Photo updatePhoto(Photo p, boolean isProduct);
    /**
     * Method to delete a photo based on the id
     * @param id id of the photo we want to delete
     */
    void deletePhoto(Long id);
}
