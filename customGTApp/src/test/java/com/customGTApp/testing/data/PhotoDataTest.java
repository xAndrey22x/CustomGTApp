package com.customGTApp.testing.data;

import com.customGTApp.data.PhotoContract;
import com.customGTApp.data.impljparepo.PhotoData;
import com.customGTApp.data.repository.PhotoRepo;
import com.customGTApp.model.Photo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class PhotoDataTest {

    /**
     * The PhotoContract dependency to be tested
     */
    private PhotoContract photoContract;

    /**
     * The mock needed for testing
     */
    @Mock
    private PhotoRepo photoRepo;

    /**
     * Method to set up the mock and the object to be tested
     */
    @Before
    public void setUp() {
        openMocks(this);
        photoContract = new PhotoData(photoRepo);
    }

    /**
     * Method to test the findAll method
     */
    @Test
    public void testFindAll(){
        Photo photo = new Photo(1, "url");

        when(photoRepo.findAll()).thenReturn(List.of(photo));

        List<Photo> photos = photoContract.findAll();

        verify(photoRepo).findAll();
        assertEquals(photos, List.of(photo));
    }

    /**
     * Method to test the findById method
     */
    @Test
    public void testFindById(){
        Photo photo = new Photo(1, "url");

        when(photoRepo.findById(1L)).thenReturn(java.util.Optional.of(photo));

        Optional<Photo> photoOptional = photoContract.findById(1L);

        verify(photoRepo).findById(1L);
        assertEquals(photoOptional, Optional.of(photo));
    }

    /**
     * Method to test the findById method when the photo is not found
     */
    @Test
    public void testFindByIdNotFound(){
        when(photoRepo.findById(1L)).thenReturn(Optional.empty());

        Optional<Photo> photoOptional = photoContract.findById(1L);

        verify(photoRepo).findById(1L);
        assertEquals(photoOptional, Optional.empty());
    }

    /**
     * Method to test the save method
     */
    @Test
    public void testSave(){
        Photo photo = new Photo(1, "url");

        when(photoRepo.save(photo)).thenReturn(photo);

        Photo savedPhoto = photoContract.save(photo);

        verify(photoRepo).save(photo);
        assertEquals(savedPhoto, photo);
    }

    /**
     * Method to test the deleteById method
     */
    @Test
    public void testDeleteById(){
        photoContract.deleteById(1L);

        verify(photoRepo).deleteById(1L);
    }
}
