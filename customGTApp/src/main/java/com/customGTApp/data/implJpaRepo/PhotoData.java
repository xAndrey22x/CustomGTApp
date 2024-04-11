package com.customGTApp.data.implJpaRepo;

import com.customGTApp.data.PhotoContract;
import com.customGTApp.model.Photo;
import com.customGTApp.repository.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PhotoData implements PhotoContract {

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
