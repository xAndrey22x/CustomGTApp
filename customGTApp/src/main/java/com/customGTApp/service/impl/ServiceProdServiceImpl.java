package com.customGTApp.service.impl;

import com.customGTApp.data.OrderItemContract;
import com.customGTApp.data.ServiceProdContract;
import com.customGTApp.model.OrderItem;
import com.customGTApp.model.Photo;
import com.customGTApp.model.ServiceProd;
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
    private final ServiceProdContract serviceProdContract;
    private final OrderItemContract orderItemContract;

    @Autowired
    public ServiceProdServiceImpl(ServiceProdContract serviceProdContract, OrderItemContract orderItemContract) {
        this.serviceProdContract = serviceProdContract;
        this.orderItemContract = orderItemContract;
    }


    /**
     * Method to get all the services
     * @return list of all services
     */
    @Override
    public List<ServiceProd> getAllServices() {
        return serviceProdContract.findAll();
    }
    /**
     * Method to get a service based on the id
     * @param id the service id
     * @return the service
     */
    @Override
    public ServiceProd getServiceById(Long id) {
        return serviceProdContract.findById(id).orElse(null);
    }
    /**
     * Method to add a new service
     * @param serviceProd the service we want to add
     * @return the added service
     */
    @Override
    public ServiceProd addService(ServiceProd serviceProd) {
        return serviceProdContract.save(serviceProd);
    }
    /**
     * Method to update a service only if it already exists in the database
     * @param serviceProd the service we want to update
     * @return the updated service
     */
    @Override
    @Transactional
    public ServiceProd updateService(ServiceProd serviceProd) {
        Optional<ServiceProd> serviceProd1 = serviceProdContract.findById(serviceProd.getId());
        if(serviceProd1.isPresent())
            return this.serviceProdContract.save(serviceProd);
        return null;
    }
    /**
     * Method to delete a service based on the id and all the order items that contain this service
     * @param id the service id
     */
    @Override
    public void deleteServiceById(Long id) {
        Optional<List<OrderItem>> orderItems = this.orderItemContract.findByServiceProdId(id);
        orderItems.ifPresent(this.orderItemContract::deleteAll);
        this.serviceProdContract.deleteById(id);
    }
    /**
     * Method to get all the photos of a service
     * @param serviceId the service id
     * @return list of all photos
     */
    @Override
    @Transactional
    public List<Photo> getAllServicePhotos(Long serviceId) {
        Optional<ServiceProd> serviceProd = this.serviceProdContract.findById(serviceId);
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
        Optional<ServiceProd> serviceProd = this.serviceProdContract.findById(serviceId);
        if(serviceProd.isPresent()){
            ServiceProd serviceProd1 = serviceProd.get();
            serviceProd1.setPrice(price);
            return this.serviceProdContract.save(serviceProd1);
        }
        return null;
    }

}
