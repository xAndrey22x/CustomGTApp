import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductService } from '../services/product.service';
import { PhotoService } from '../services/photo.service';
import { Product } from '../models/product';
import { Photo } from '../models/photo';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  addProductForm: FormGroup;
  newPhotoUrl: string = '';
  addProductMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private photoService: PhotoService,
    private router: Router
  ) {
    this.addProductForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      carModel: ['', Validators.required],
      quantity: ['', [Validators.required, Validators.min(0)]],
      photos: this.fb.array([])
    });
  }

  ngOnInit(): void {}

  get photos(): FormArray {
    return this.addProductForm.get('photos') as FormArray;
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
    if (this.addProductForm.valid) {
      const newProduct: Product = {
        ...this.addProductForm.value,
        id: 0,
        photos: []
      };

      this.productService.addProduct(newProduct).subscribe((createdProduct) => {
        this.addPhotosToProduct(createdProduct.id).then(() => {
          console.log('Product added successfully');
          this.addProductMessage = 'Product added successfully';
          setTimeout(() => this.addProductMessage = '', 3000);
        });
      });
    }
  }

  async addPhotosToProduct(productId: number): Promise<void> {
    const photos = this.photos.value;
    for (const photo of photos) {
      const newPhoto: Photo = {
        id: 0,
        url: photo.url,
        product: null,
        serviceProd: null
      };
      await this.photoService.addPhotoToProduct(newPhoto, productId).toPromise();
    }
  }

  adminBack(): void {
    this.router.navigate(['/admin-panel']);
  }

}
