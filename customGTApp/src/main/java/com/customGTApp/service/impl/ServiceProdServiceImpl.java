package com.customGTApp.service.impl;

import com.customGTApp.model.Photo;
import com.customGTApp.model.ServiceProd;
import com.customGTApp.repository.PhotoRepo;
import com.customGTApp.repository.ServiceProdRepo;
import com.customGTApp.service.ServiceProdService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProdServiceImpl implements ServiceProdService {

    /**
     * The ServiceProdRepo object to handle the CRUD operations.
     */
    private final ServiceProdRepo serviceProdRepo;

    @Autowired
    public ServiceProdServiceImpl(ServiceProdRepo serviceProdRepo) {
        this.serviceProdRepo = serviceProdRepo;
    }
    /**
     * Method to get all the services
     * @return list of all services
     */
    @Override
    public List<ServiceProd> getAllServices() {
        return serviceProdRepo.findAll();
    }
    /**
     * Method to get a service based on the id
     * @param id the service id
     * @return the service
     */
    @Override
    public ServiceProd getServiceById(Long id) {
        return serviceProdRepo.findById(id).orElse(null);
    }
    /**
     * Method to add a new service
     * @param serviceProd the service we want to add
     * @return the added service
     */
    @Override
    public ServiceProd addService(ServiceProd serviceProd) {
        return serviceProdRepo.save(serviceProd);
    }
    /**
     * Method to update a service only if it already exists in the database
     * @param serviceProd the service we want to update
     * @return the updated service
     */
    @Override
    @Transactional
    public ServiceProd updateService(ServiceProd serviceProd) {
        Optional<ServiceProd> serviceProd1 = serviceProdRepo.findById(serviceProd.getId());
        if(serviceProd1.isPresent())
            return this.serviceProdRepo.save(serviceProd);
        return null;
    }
    /**
     * Method to delete a service based on the id
     * @param id the service id
     */
    @Override
    public void deleteServiceById(Long id) {
        this.serviceProdRepo.deleteById(id);
    }
    /**
     * Method to get all the photos of a service
     * @param serviceId the service id
     * @return list of all photos
     */
    @Override
    @Transactional
    public List<Photo> getAllServicePhotos(Long serviceId) {
        Optional<ServiceProd> serviceProd = this.serviceProdRepo.findById(serviceId);
        return serviceProd.map(ServiceProd::getPhotos).orElse(null);
    }

    /**
     * Method to update the price of a service
     * @param serviceId the service id
     * @param price the new price
     * @return the updated service
     */
    @Override
    @Transactional
    public ServiceProd updatePrice(Long serviceId, float price) {
        Optional<ServiceProd> serviceProd = this.serviceProdRepo.findById(serviceId);
        if(serviceProd.isPresent()){
            ServiceProd serviceProd1 = serviceProd.get();
            serviceProd1.setPrice(price);
            return this.serviceProdRepo.save(serviceProd1);
        }
        return null;
    }

}
