package com.customGTApp.service;

import com.customGTApp.model.Photo;
import com.customGTApp.model.ServiceProd;

import java.util.List;
/**
 * ServiceProdService interface that will be implemented by ServiceProdServiceImpl and other classes,
 * making it easier to manage the ServiceProd model. Also, making the code more readable and easier to maintain.
 */
public interface ServiceProdService {
    List<ServiceProd> getAllServices();
    ServiceProd getServiceById(Long id);
    ServiceProd addService(ServiceProd serviceProd);
    ServiceProd updateService(ServiceProd serviceProd);
    void deleteServiceById(Long id);
    List<Photo> getAllServicePhotos(Long serviceId);
}
