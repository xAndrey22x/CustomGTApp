package com.customGTApp.data.implJpaRepo;

import com.customGTApp.data.ServiceProdContract;
import com.customGTApp.model.ServiceProd;
import com.customGTApp.repository.ServiceProdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ServiceProdData implements ServiceProdContract {

    private final ServiceProdRepo serviceProdRepo;

    @Autowired
    public ServiceProdData(ServiceProdRepo serviceProdRepo) {
        this.serviceProdRepo = serviceProdRepo;
    }

    @Override
    public List<ServiceProd> findAll() {
        return this.serviceProdRepo.findAll();
    }

    @Override
    public Optional<ServiceProd> findById(Long id) {
        return this.serviceProdRepo.findById(id);
    }

    @Override
    public ServiceProd save(ServiceProd serviceProd) {
        return this.serviceProdRepo.save(serviceProd);
    }

    @Override
    public void deleteById(Long id) {
        this.serviceProdRepo.deleteById(id);
    }
}
