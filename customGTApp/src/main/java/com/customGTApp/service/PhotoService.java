package com.customGTApp.service;

import com.customGTApp.model.Photo;

import java.util.List;

public interface PhotoService {
    List<Photo> findAllPhotos();

    /**
     * Method to add a photo to product based on the id
     * @param productId id of the product we want to add the photo
     * @param photo the photo we want to add
     * @return returns the added photo
     */
    Photo addPhotoToProduct(Long productId, Photo photo);
    Photo addPhotoToService(Long serviceProdId, Photo photo);

    /**
     * Method to update the photo information, and it can be a photo for a product or for a service
     * @param p new photo information
     * @param isProduct if it's for a product or for a service
     * @return the photo updated
     */
    Photo updatePhoto(Photo p, boolean isProduct);
    void deletePhoto(Long id);
}
