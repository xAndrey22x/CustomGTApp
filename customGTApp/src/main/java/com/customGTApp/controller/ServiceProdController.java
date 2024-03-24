package com.customGTApp.controller;

import com.customGTApp.model.Photo;
import com.customGTApp.model.ServiceProd;
import com.customGTApp.service.impl.ServiceProdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * /service/all
 * /service/find/{id}
 * /service/add
 * /service/update
 * /service/delete/{id}
 * /service/{serviceId}/photos
 */
@RestController
@RequestMapping("/service")
public class ServiceProdController {

    private final ServiceProdServiceImpl serviceProdService;

    @Autowired
    public ServiceProdController(ServiceProdServiceImpl serviceProdService) {
        this.serviceProdService = serviceProdService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceProd>> getAllServices(){
        return new ResponseEntity<>(this.serviceProdService.getAllServices(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ServiceProd> getServiceById(@PathVariable("id") Long id){
        ServiceProd serviceProd = this.serviceProdService.getServiceById(id);
        if(serviceProd != null)
            return new ResponseEntity<>(serviceProd,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<ServiceProd> addService(@RequestBody ServiceProd serviceProd){
        return new ResponseEntity<>(serviceProdService.addService(serviceProd),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ServiceProd> updateService(@RequestBody ServiceProd serviceProd){
        ServiceProd serviceProd1 = serviceProdService.updateService(serviceProd);
        if(serviceProd1 != null)
            return new ResponseEntity<>(serviceProd1, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteService(@PathVariable("id") Long id){
        this.serviceProdService.deleteServiceById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{serviceId}/photos")
    public ResponseEntity<List<Photo>> getAllPhotosByServiceId(@PathVariable("serviceId") Long serviceId){
        List<Photo> photos = serviceProdService.getAllServicePhotos(serviceId);
        if (photos != null)
            return new ResponseEntity<>(photos, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
