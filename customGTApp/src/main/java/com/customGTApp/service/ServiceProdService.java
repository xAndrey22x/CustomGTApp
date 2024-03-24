package com.customGTApp.service;

import com.customGTApp.model.Photo;
import com.customGTApp.model.ServiceProd;

import java.util.List;

public interface ServiceProdService {
    List<ServiceProd> getAllServices();
    ServiceProd getServiceById(Long id);
    ServiceProd addService(ServiceProd serviceProd);
    ServiceProd updateService(ServiceProd serviceProd);
    void deleteServiceById(Long id);
    List<Photo> getAllServicePhotos(Long serviceId);
}
