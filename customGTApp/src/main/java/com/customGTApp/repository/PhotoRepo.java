package com.customGTApp.repository;

import com.customGTApp.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepo extends JpaRepository<Photo, Long> {

}
