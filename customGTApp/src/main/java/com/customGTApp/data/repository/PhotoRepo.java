package com.customGTApp.data.repository;

import com.customGTApp.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for holding all the CRUD operations for a DataBase we want to use.
 */
public interface PhotoRepo extends JpaRepository<Photo, Long> {

}
