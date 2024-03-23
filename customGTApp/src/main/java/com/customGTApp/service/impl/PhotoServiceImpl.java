package com.customGTApp.service.impl;

import com.customGTApp.model.Photo;
import com.customGTApp.model.Product;
import com.customGTApp.repository.PhotoRepo;
import com.customGTApp.repository.ProductRepo;
import com.customGTApp.service.PhotoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepo photoRepo;
    private final ProductRepo productRepo;

    @Autowired
    public PhotoServiceImpl(PhotoRepo photoRepo, ProductRepo productRepo){
        this.photoRepo = photoRepo;
        this.productRepo = productRepo;
    }

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
     * Update the photo information only if the photo already exists in dataBase
     */
    @Override
    @Transactional
    public Photo updatePhoto(Photo photo) {
        long photoId = photo.getId();
        Optional<Photo> photoOptional = photoRepo.findById(photoId);
        if(photoOptional.isPresent()) {
            Product product = photoOptional.get().getProduct();
            photo.setProduct(product);
            return photoRepo.save(photo);
        }
        return null;
    }

    @Override
    public void deletePhoto(Long photoId) {
        photoRepo.deleteById(photoId);
    }
}
