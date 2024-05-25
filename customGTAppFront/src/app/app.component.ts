import { Component, OnInit } from '@angular/core';
import { Product } from './models/product';
import { ProductService } from './services/product.service';
import { Photo } from './models/photo';
import { error } from 'console';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent{
  title = 'customGT';
  public Photos: Photo[] = [];
  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.getAllProductPhotos();
  }

  getAllProductPhotos() {
    const productId = 7; // Replace with the actual product ID you want to test with
    this.productService.getAllPhotosByProductId(productId).subscribe({
      next: (photos: Photo[]) => {
        this.Photos = photos;
      },
      error: (err) => {
        console.error('Error fetching photos:', err);
      },
      complete: () => {
        console.log('Photo fetching complete');
      }
    });
  }

}
