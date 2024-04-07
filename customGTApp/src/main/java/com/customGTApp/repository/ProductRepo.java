package com.customGTApp.repository;

import com.customGTApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for holding all the CRUD operations for a DataBase we want to use.
 */

public interface ProductRepo extends JpaRepository<Product, Long> {
}
