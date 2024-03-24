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

    private final ServiceProdRepo serviceProdRepo;
    private final PhotoRepo photoRepo;

    @Autowired
    public ServiceProdServiceImpl(ServiceProdRepo serviceProdRepo, PhotoRepo photoRepo) {
        this.serviceProdRepo = serviceProdRepo;
        this.photoRepo = photoRepo;
    }

    @Override
    public List<ServiceProd> getAllServices() {
        return serviceProdRepo.findAll();
    }

    @Override
    public ServiceProd getServiceById(Long id) {
        return serviceProdRepo.findById(id).orElse(null);
    }

    @Override
    public ServiceProd addService(ServiceProd serviceProd) {
        return serviceProdRepo.save(serviceProd);
    }

    @Override
    @Transactional
    public ServiceProd updateService(ServiceProd serviceProd) {
        Optional<ServiceProd> serviceProd1 = serviceProdRepo.findById(serviceProd.getId());
        if(serviceProd1.isPresent())
            return this.serviceProdRepo.save(serviceProd);
        return null;
    }

    @Override
    public void deleteServiceById(Long id) {
        this.serviceProdRepo.deleteById(id);
    }

    @Override
    @Transactional
    public List<Photo> getAllServicePhotos(Long serviceId) {
        Optional<ServiceProd> serviceProd = this.serviceProdRepo.findById(serviceId);
        if(serviceProd.isPresent()){
            return this.photoRepo.findByServiceProdId(serviceId);
        }
        return null;
    }

}
