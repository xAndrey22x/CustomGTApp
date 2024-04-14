package com.customGTApp.data.repository;

import com.customGTApp.model.ServiceProd;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for holding all the CRUD operations for a DataBase we want to use.
 */
public interface ServiceProdRepo extends JpaRepository<ServiceProd, Long> {
}
