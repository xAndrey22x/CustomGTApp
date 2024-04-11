package com.customGTApp.data.implJpaRepo;

import com.customGTApp.data.PhotoContract;
import com.customGTApp.model.Photo;
import com.customGTApp.repository.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the PhotoContract interface to provide the basic CRUD operations for the Photo entity
 */
@Repository
public class PhotoData implements PhotoContract {

    /**
     * Usage of JPA Repository to handle the database operations for the Photo entity
     */
    private final PhotoRepo photoRepo;

    @Autowired
    public PhotoData(PhotoRepo photoRepo) {
        this.photoRepo = photoRepo;
    }

    @Override
    public List<Photo> findAll() {
        return this.photoRepo.findAll();
    }

    @Override
    public Optional<Photo> findById(Long id) {
        return this.photoRepo.findById(id);
    }

    @Override
    public Photo save(Photo photo) {
        return this.photoRepo.save(photo);
    }

    @Override
    public void deleteById(Long id) {
        this.photoRepo.deleteById(id);
    }
}
