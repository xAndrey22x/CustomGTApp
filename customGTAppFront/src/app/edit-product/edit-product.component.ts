import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../services/product.service';
import { PhotoService } from '../services/photo.service';
import { Product } from '../models/product';
import { Photo } from '../models/photo';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  editProductForm: FormGroup;
  priceForm: FormGroup;
  quantityForm: FormGroup;
  newPhotoUrl: string = '';
  productId!: number;
  priceUpdateMessage: string = '';
  quantityUpdateMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private photoService: PhotoService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.editProductForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      carModel: ['', Validators.required],
      photos: this.fb.array([])
    });

    this.priceForm = this.fb.group({
      price: ['', [Validators.required, Validators.min(0)]]
    });

    this.quantityForm = this.fb.group({
      quantity: ['', [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void {
    this.productId = this.route.snapshot.params['id'];
    this.productService.getProductById(this.productId).subscribe((product: Product) => {
      this.editProductForm.patchValue({
        name: product.name,
        description: product.description,
        carModel: product.carModel
      });

      this.priceForm.patchValue({
        price: product.price
      });

      this.quantityForm.patchValue({
        quantity: product.quantity
      });

      product.photos.forEach(photo => {
        this.photos.push(this.fb.group({
          id: [photo.id],
          url: [photo.url, Validators.required]
        }));
      });
    });
  }

  get photos(): FormArray {
    return this.editProductForm.get('photos') as FormArray;
  }

  addPhoto(): void {
    if (this.newPhotoUrl.trim()) {
      const photo: Photo = {
        id: 0,
        url: this.newPhotoUrl,
        product: null,
        serviceProd: null
      };

      this.photoService.addPhotoToProduct(photo, this.productId).subscribe((addedPhoto: Photo) => {
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
      this.productService.updatePrice(this.productId, newPrice).subscribe(() => {
        this.priceUpdateMessage = 'Price updated successfully';
        setTimeout(() => this.priceUpdateMessage = '', 3000);
      });
    }
  }

  updateQuantity(): void {
    if (this.quantityForm.valid) {
      const newQuantity = this.quantityForm.get('quantity')?.value;
      this.productService.updateQuantity(this.productId, newQuantity).subscribe(() => {
        this.quantityUpdateMessage = 'Quantity updated successfully';
        setTimeout(() => this.quantityUpdateMessage = '', 3000);
      });
    }
  }

  onSubmit(): void {
    if (this.editProductForm.valid) {
      const updatedProduct: Product = {
        ...this.editProductForm.value,
        id: this.productId,
        price: this.priceForm.get('price')?.value, 
        quantity: this.quantityForm.get('quantity')?.value, 
        photos: this.photos.value.map((photo: any) => ({
          id: photo.id || 0,
          url: photo.url,
          product: this.productId,
          serviceProd: null
        }))
      };

      this.productService.updateProduct(updatedProduct).subscribe(() => {
        this.router.navigate(['/admin-panel']);
      });
    }
  }
}
