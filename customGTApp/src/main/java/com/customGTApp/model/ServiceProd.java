package com.customGTApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Service model of our database who will hold information about our services.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ServiceProd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private float price;

    /**
     * OneToMany connection between the service and photos. In the photos list we will hold all the photos connected
     * between the primary key of a service 'id' and the foreign key of photo 'serviceId'.
     */
    @OneToMany(mappedBy = "serviceProd", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    public ServiceProd(){

    }

    public ServiceProd(long id, String name, String description, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void addPhoto(Photo photo){
        this.photos.add(photo);
        photo.setServiceProd(this);
    }

    public void removePhoto(Photo photo){
        this.photos.remove(photo);
        photo.setServiceProd(null);
    }

}
