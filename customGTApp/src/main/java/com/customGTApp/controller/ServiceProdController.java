package com.customGTApp.controller;

import com.customGTApp.model.Photo;
import com.customGTApp.model.ServiceProd;
import com.customGTApp.service.ServiceProdService;
import com.customGTApp.service.impl.ServiceProdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * /service/all - list all the services
 * /service/find/{id} - find a service based on the id provided
 * /service/add - add a service, the service will be received as a request body
 * /service/update - update a service, the new service will be received as a request body
 * /service/delete/{id} - delete a service based on the id provided
 * /service/{serviceId}/photos - list all the photos of a service based on the service id
 * /service/updatePrice/{serviceId} - update the price of a service based on the service id
 *                                  - param price
 */
@RestController
@RequestMapping("/service")
public class ServiceProdController {

    /**
     * ServiceProdService object to handle the serviceProd operations and to be able to use the methods in
     * the service layer
     */
    private final ServiceProdService serviceProdService;

    @Autowired
    public ServiceProdController(ServiceProdServiceImpl serviceProdService) {
        this.serviceProdService = serviceProdService;
    }
    /**
     * Method to get all the services from the database and return them as a list. Also calls the getAllServices method from the service layer
     * @return list of all services
     */
    @GetMapping("/all")
    public ResponseEntity<List<ServiceProd>> getAllServices(){
        return new ResponseEntity<>(this.serviceProdService.getAllServices(), HttpStatus.OK);
    }
    /**
     * Method to get a service based on the id provided and return it. Also calls the getServiceById method from the service layer
     * @param id the service id
     * @return the service
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<ServiceProd> getServiceById(@PathVariable("id") Long id){
        ServiceProd serviceProd = this.serviceProdService.getServiceById(id);
        if(serviceProd != null)
            return new ResponseEntity<>(serviceProd,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * Method to add a new service to the database and return it. Also calls the addService method from the service layer
     * @param serviceProd the service we want to add
     * @return the added service
     */
    @PostMapping("/add")
    public ResponseEntity<ServiceProd> addService(@RequestBody ServiceProd serviceProd){
        return new ResponseEntity<>(serviceProdService.addService(serviceProd),HttpStatus.OK);
    }
    /**
     * Method to update a service in the database and return it. Also calls the updateService method from the service layer
     * @param serviceProd the service we want to update
     * @return the updated service
     */
    @PutMapping("/update")
    public ResponseEntity<ServiceProd> updateService(@RequestBody ServiceProd serviceProd){
        ServiceProd serviceProd1 = serviceProdService.updateService(serviceProd);
        if(serviceProd1 != null)
            return new ResponseEntity<>(serviceProd1, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * Method to delete a service from the database. Also calls the deleteServiceById method from
     * @param id the id of the service we want to delete
     * @return status of the operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteService(@PathVariable("id") Long id){
        this.serviceProdService.deleteServiceById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /**
     * Method to get all the photos of a service based on the list of photos from a service, and it calls the
     * findPhotosByServiceId method from the ServiceProdService class
     * @param serviceId the service id
     * @return list of all photos
     */
    @GetMapping("{serviceId}/photos")
    public ResponseEntity<List<Photo>> getAllPhotosByServiceId(@PathVariable("serviceId") Long serviceId){
        List<Photo> photos = serviceProdService.getAllServicePhotos(serviceId);
        if (photos != null)
            return new ResponseEntity<>(photos, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method to update the price of a service based on the service id provided and return it. Also calls the updatePrice method from the service layer
     * @param serviceId the service id
     * @param price the new price
     * @return the updated service
     */
    @PutMapping("/updatePrice/{serviceId}")
    public ResponseEntity<ServiceProd> updatePrice(@PathVariable("serviceId") Long serviceId, @RequestParam("price") float price){
        ServiceProd serviceProd = this.serviceProdService.updatePrice(serviceId, price);
        if (serviceProd != null)
            return new ResponseEntity<>(serviceProd, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
