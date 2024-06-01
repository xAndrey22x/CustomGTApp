import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../models/product';
import { ProductService } from '../services/product.service'; 

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent {
  product: Product | undefined;
  quantity: number = 1;
  currentSlide: number = 0;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const productId = +this.route.snapshot.paramMap.get('id')!;
    this.productService.getProductById(productId).subscribe({
      next: (product) => {
        this.product = product;
        if (this.product.photos.length > 0) {
          this.currentSlide = 0;
        }
      },
      error: (error) => {
        console.error('Failed to load product', error);
      }
    });
  }

  goToProducts(): void {
    this.router.navigate(['/products']);
  }

  decreaseQuantity(): void {
    if (this.quantity > 1) {
      this.quantity--;
    }
  }

  increaseQuantity(): void {
    if (this.product && this.quantity < this.product.quantity) {
      this.quantity++;
    }
  }

  addToCart(): void {
    console.log(`Added ${this.quantity} of ${this.product?.name} to the cart.`);
    // Implement add to cart functionality later
  }

  changeSlide(n: number): void {
    if (this.product && this.product.photos.length > 0) {
      this.currentSlide = (this.currentSlide + n + this.product.photos.length) % this.product.photos.length;
    }
  }

}
