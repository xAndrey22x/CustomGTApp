package com.customGTApp.data.impljparepo;

import com.customGTApp.data.PhotoContract;
import com.customGTApp.model.Photo;
import com.customGTApp.data.repository.PhotoRepo;
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

    /**
     * Constructor to inject the PhotoRepo dependency
     * @param photoRepo The PhotoRepo dependency
     */

    @Autowired
    public PhotoData(PhotoRepo photoRepo) {
        this.photoRepo = photoRepo;
    }

    /**
     * Method to get all the photos in the database using the JPA Repository method
     * @return List of all the photos
     */

    @Override
    public List<Photo> findAll() {
        return this.photoRepo.findAll();
    }

    /**
     * Method to get a photo based on the id using the JPA Repository method
     * @param id the photo id
     * @return the photo
     */

    @Override
    public Optional<Photo> findById(Long id) {
        return this.photoRepo.findById(id);
    }

    /**
     * Method to save a photo using the JPA Repository method
     * @param photo the photo to be saved
     * @return the saved photo
     */

    @Override
    public Photo save(Photo photo) {
        return this.photoRepo.save(photo);
    }

    /**
     * Method to delete a photo based on the id using the JPA Repository method
     * @param id the photo id
     */

    @Override
    public void deleteById(Long id) {
        this.photoRepo.deleteById(id);
    }
}
