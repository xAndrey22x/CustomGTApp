package com.customGTApp.data.impljparepo;

import com.customGTApp.data.ServiceProdContract;
import com.customGTApp.model.ServiceProd;
import com.customGTApp.data.repository.ServiceProdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ServiceProdContract interface to provide the basic CRUD operations for the ServiceProd entity
 */
@Repository
public class ServiceProdData implements ServiceProdContract {

    /**
     * Usage of JPA Repository to handle the database operations for the ServiceProd entity
     */
    private final ServiceProdRepo serviceProdRepo;

    /**
     * Constructor to inject the ServiceProdRepo dependency
     * @param serviceProdRepo The ServiceProdRepo dependency
     */

    @Autowired
    public ServiceProdData(ServiceProdRepo serviceProdRepo) {
        this.serviceProdRepo = serviceProdRepo;
    }

    /**
     * Method to get all the service prods in the database using the JPA Repository method
     * @return List of all the service prods
     */

    @Override
    public List<ServiceProd> findAll() {
        return this.serviceProdRepo.findAll();
    }

    /**
     * Method to get a service prod based on the id using the JPA Repository method
     * @param id the service prod id
     * @return the service prod
     */

    @Override
    public Optional<ServiceProd> findById(Long id) {
        return this.serviceProdRepo.findById(id);
    }

    /**
     * Method to save a service prod using the JPA Repository method
     * @param serviceProd the service prod to be saved
     * @return the saved service prod
     */

    @Override
    public ServiceProd save(ServiceProd serviceProd) {
        return this.serviceProdRepo.save(serviceProd);
    }

    /**
     * Method to delete a service prod based on the id using the JPA Repository method
     * @param id the service prod id
     */

    @Override
    public void deleteById(Long id) {
        this.serviceProdRepo.deleteById(id);
    }
}
