import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceProdService } from '../services/service-prod.service';
import { PhotoService } from '../services/photo.service';
import { ServiceProd } from '../models/serviceProd';
import { Photo } from '../models/photo';

@Component({
  selector: 'app-edit-service-prod',
  templateUrl: './edit-service-prod.component.html',
  styleUrl: './edit-service-prod.component.css'
})
export class EditServiceProdComponent implements OnInit{
  editServiceForm: FormGroup;
  priceForm: FormGroup;
  newPhotoUrl: string = '';
  serviceProdId!: number;
  priceUpdateMessage: string = '';
  saveUpdateMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private serviceProdService: ServiceProdService,
    private photoService: PhotoService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.editServiceForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      photos: this.fb.array([])
    });

    this.priceForm = this.fb.group({
      price: ['', [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void {
    this.serviceProdId = this.route.snapshot.params['id'];
    this.serviceProdService.getServiceById(this.serviceProdId).subscribe((serviceProd: ServiceProd) => {
      this.editServiceForm.patchValue({
        name: serviceProd.name,
        description: serviceProd.description
      });

      this.priceForm.patchValue({
        price: serviceProd.price
      });

      serviceProd.photos.forEach(photo => {
        this.photos.push(this.fb.group({
          id: [photo.id],
          url: [photo.url, Validators.required]
        }));
      });
    });
  }

  get photos(): FormArray {
    return this.editServiceForm.get('photos') as FormArray;
  }

  addPhoto(): void {
    if (this.newPhotoUrl.trim()) {
      const photo: Photo = {
        id: 0,
        url: this.newPhotoUrl,
        product: null,
        serviceProd: null
      };

      this.photoService.addPhotoToService(photo, this.serviceProdId).subscribe((addedPhoto: Photo) => {
        const newPhoto = this.fb.group({
          id: [addedPhoto.id],
          url: [addedPhoto.url, Validators.required]
        });
        this.photos.push(newPhoto);
        this.newPhotoUrl = '';
        console.log('Photo added to the database and form');
      });
    }
  }

  removePhoto(index: number): void {
    const photoToRemove = this.photos.at(index).value;
    if (photoToRemove.id) {
      this.photoService.deletePhoto(photoToRemove.id).subscribe(() => {
        console.log('Photo removed from the database');
      });
    }
    this.photos.removeAt(index);
  }

  updatePrice(): void {
    if (this.priceForm.valid) {
      const newPrice = this.priceForm.get('price')?.value;
      this.serviceProdService.updatePrice(this.serviceProdId, newPrice).subscribe(() => {
        this.priceUpdateMessage = 'Price updated successfully';
        setTimeout(() => this.priceUpdateMessage = '', 3000);
      });
    }
  }

  onSubmit(): void {
    if (this.editServiceForm.valid) {
      const updatedServiceProd: ServiceProd = {
        ...this.editServiceForm.value,
        id: this.serviceProdId,
        price: this.priceForm.get('price')?.value,
        photos: this.photos.value.map((photo: any) => ({
          id: photo.id || 0,
          url: photo.url,
          serviceProd: this.serviceProdId
        }))
      };

      this.serviceProdService.updateService(updatedServiceProd).subscribe(() => {
        console.log('Service updated successfully');
        this.saveUpdateMessage = 'Service updated successfully';
        setTimeout(() => this.saveUpdateMessage = '', 3000);
      });
    }
  }

  adminBack(): void {
    this.router.navigate(['/admin-panel']);
  }
}

