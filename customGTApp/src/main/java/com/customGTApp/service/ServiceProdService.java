package com.customGTApp.service;

import com.customGTApp.model.Photo;
import com.customGTApp.model.ServiceProd;

import java.util.List;
/**
 * ServiceProdService interface that will be implemented by ServiceProdServiceImpl and other classes,
 * making it easier to manage the ServiceProd model. Also, making the code more readable and easier to maintain.
 */
public interface ServiceProdService {
    /**
     * Method to return all services
     * @return a list of all services
     */
    List<ServiceProd> getAllServices();
    /**
     * Method to find a service by id
     * @param id the id of the service we want to find
     * @return the service found
     */
    ServiceProd getServiceById(Long id);
    /**
     * Method to add a service
     * @param serviceProd the service that will be added
     * @return the service that was added
     */
    ServiceProd addService(ServiceProd serviceProd);
    /**
     * Method to update a service
     * @param serviceProd the service that will be updated
     * @return the service that was updated
     */
    ServiceProd updateService(ServiceProd serviceProd);
    /**
     * Method to delete a service by id
     * @param id the id of the service that will be deleted
     */
    void deleteServiceById(Long id);

    /**
     * Method to get all the photos of a service
     * @param serviceId the id of the service we want to get the photos
     * @return the photos list of the service
     */
    List<Photo> getAllServicePhotos(Long serviceId);

    /**
     * Method to update the price of a service
     * @param serviceId the id of the service we want to update the price
     * @param price the new price of the service
     * @return the service with the updated price
     */
    ServiceProd updatePrice(Long serviceId, float price);
}
