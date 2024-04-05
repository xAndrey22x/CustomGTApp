package com.customGTApp.service.impl;

import com.customGTApp.model.Photo;
import com.customGTApp.model.Product;
import com.customGTApp.model.ServiceProd;
import com.customGTApp.repository.PhotoRepo;
import com.customGTApp.repository.ProductRepo;
import com.customGTApp.repository.ServiceProdRepo;
import com.customGTApp.service.PhotoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    /**
     * All the repositories needed to handle the CRUD operations.
     */
    private final PhotoRepo photoRepo;
    private final ProductRepo productRepo;
    private final ServiceProdRepo serviceProdRepo;

    @Autowired
    public PhotoServiceImpl(PhotoRepo photoRepo, ProductRepo productRepo, ServiceProdRepo serviceProdRepo){
        this.photoRepo = photoRepo;
        this.productRepo = productRepo;
        this.serviceProdRepo = serviceProdRepo;
    }

    /**
     * Method to get all the photos
     * @return list of all photos
     */
    @Override
    public List<Photo> findAllPhotos() {
        return this.photoRepo.findAll();
    }

    /**
     * Implementation of the method to add a photo to the product. Firstly it checks if the product where we want to add
     * the photo already exists and if exists it adds the photo to his list.
     * @param productId id of the product we want to add the photo
     * @param photo the photo we want to add
     * @return the added photo
     */
    @Override
    @Transactional
    public Photo addPhotoToProduct(Long productId, Photo photo) {
        Optional<Product> productOptional = productRepo.findById(productId);
        if (productOptional.isPresent()) {
            photo.setProduct(productOptional.get());
            return photoRepo.save(photo);
        }
        return null;
    }

    /**
     * Same as the method for adding to Product
     */
    @Override
    @Transactional
    public Photo addPhotoToService(Long serviceProdId, Photo photo) {
        Optional<ServiceProd> serviceProd = serviceProdRepo.findById(serviceProdId);
        if(serviceProd.isPresent()){
            photo.setServiceProd(serviceProd.get());
            return this.photoRepo.save(photo);
        }

        return null;
    }

    /**
     * Update the photo information only if the photo already exists in dataBase and also don't forget the reference
     * for product or service id.
     */
    @Override
    @Transactional
    public Photo updatePhoto(Photo photo, boolean isProduct) {
        long photoId = photo.getId();
        Optional<Photo> photoOptional = photoRepo.findById(photoId);
        if(photoOptional.isPresent()) {
            if(isProduct) {
                Product product = photoOptional.get().getProduct();
                photo.setProduct(product);
            }
            else {
                ServiceProd serviceProd = photoOptional.get().getServiceProd();
                photo.setServiceProd(serviceProd);
            }
            return photoRepo.save(photo);
        }
        return null;
    }
    /**
     * Method to delete a photo based on the id
     * @param photoId the photo id
     */
    @Override
    public void deletePhoto(Long photoId) {
        photoRepo.deleteById(photoId);
    }
}
