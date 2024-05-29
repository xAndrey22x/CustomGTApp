import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceProdService } from '../services/service-prod.service';
import { PhotoService } from '../services/photo.service';
import { ServiceProd } from '../models/serviceProd';
import { Photo } from '../models/photo';

@Component({
  selector: 'app-add-service-prod',
  templateUrl: './add-service-prod.component.html',
  styleUrl: './add-service-prod.component.css'
})
export class AddServiceProdComponent {
  addServiceForm: FormGroup;
  newPhotoUrl: string = '';
  addServiceMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private serviceProdService: ServiceProdService,
    private photoService: PhotoService,
    private router: Router
  ) {
    this.addServiceForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      photos: this.fb.array([])
    });
  }

  ngOnInit(): void {}

  get photos(): FormArray {
    return this.addServiceForm.get('photos') as FormArray;
  }

  addPhoto(): void {
    if (this.newPhotoUrl.trim()) {
      const newPhoto = this.fb.group({
        url: [this.newPhotoUrl, Validators.required]
      });
      this.photos.push(newPhoto);
      this.newPhotoUrl = '';
    }
  }

  removePhoto(index: number): void {
    this.photos.removeAt(index);
  }

  onSubmit(): void {
    if (this.addServiceForm.valid) {
      const newService: ServiceProd = {
        ...this.addServiceForm.value,
        id: 0,
        photos: []
      };

      this.serviceProdService.addService(newService).subscribe((createdService) => {
        this.addPhotosToService(createdService.id).then(() => {
          console.log('Service added successfully');
          this.addServiceMessage = 'Service added successfully';
          setTimeout(() => this.addServiceMessage = '', 3000);
        });
      });
    }
  }

  async addPhotosToService(serviceProdId: number): Promise<void> {
    const photos = this.photos.value;
    for (const photo of photos) {
      const newPhoto: Photo = {
        id: 0,
        url: photo.url,
        product: null,
        serviceProd: null
      };
      await this.photoService.addPhotoToService(newPhoto, serviceProdId).toPromise();
    }
  }

  adminBack(): void {
    this.router.navigate(['/admin-panel']);
  }
}
