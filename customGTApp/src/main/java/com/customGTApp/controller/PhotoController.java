package com.customGTApp.controller;

import com.customGTApp.model.Photo;
import com.customGTApp.model.Product;
import com.customGTApp.service.PhotoService;
import com.customGTApp.service.impl.PhotoServiceImpl;
import com.customGTApp.service.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    private final PhotoServiceImpl photoService;

    public PhotoController(PhotoServiceImpl photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Photo>> getAllPhotos() {
        List<Photo> photos = photoService.findAllPhotos();
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<Photo> addPhoto(@PathVariable("productId") Long productId, @RequestBody Photo photo) {
        Photo photo1 = photoService.addPhotoToProduct(productId, photo);
        if (photo1 != null)
            return new ResponseEntity<>(photo1, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<Photo> updatePhoto(@RequestBody Photo photo){
        Photo photo1 = photoService.updatePhoto(photo);
        if (photo1 != null)
            return new ResponseEntity<>(photo1, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{photoId}")
    public ResponseEntity<?> deletePhoto(@PathVariable Long photoId) {
        photoService.deletePhoto(photoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
