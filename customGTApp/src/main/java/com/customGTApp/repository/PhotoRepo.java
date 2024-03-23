package com.customGTApp.repository;

import com.customGTApp.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepo extends JpaRepository<Photo, Long> {
    /**
     * New method to find the photos by product id, so we can find all the photos of a product.
     * @param productId id of a product we want to find his photos
     * @return the photos of the product
     */
    List<Photo> findByProductId(Long productId);
}
