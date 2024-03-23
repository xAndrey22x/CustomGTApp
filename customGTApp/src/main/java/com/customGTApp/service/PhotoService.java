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
    Photo updatePhoto(Photo p);
    void deletePhoto(Long id);
}
