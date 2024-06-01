import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../models/product';
import { ProductService } from '../services/product.service'; 
import { ShoppingCartService } from '../services/shopping-cart.service';
import { OrderItem } from '../models/orderItem';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent {
  product: Product | undefined;
  quantity: number = 1;
  currentSlide: number = 0;
  cartItemCount: number = 0;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private router: Router,
    private shoppingCartService: ShoppingCartService
  ) {}

  ngOnInit(): void {
    const productId = +this.route.snapshot.paramMap.get('id')!;
    this.productService.getProductById(productId).subscribe({
      next: (product) => {
        this.product = product;
        if (this.product.photos.length > 0) {
          this.currentSlide = 0;
        }
        this.updateCartItemCount();
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
    if (this.product) {
      const orderItem: OrderItem = {
        id: 0,
        quantity: this.quantity,
        price: this.product.price * this.quantity,
        product: this.product,
        serviceProd: null,
        orderClient: null
      };
      this.shoppingCartService.addCartItem(orderItem);
      this.updateCartItemCount();
    }
  }

  changeSlide(n: number): void {
    if (this.product && this.product.photos.length > 0) {
      this.currentSlide = (this.currentSlide + n + this.product.photos.length) % this.product.photos.length;
    }
  }

  photoSize(): number {
    return this.product ? this.product.photos.length : 0;
  }

  updateCartItemCount(): void {
    this.cartItemCount = this.shoppingCartService.getCartItemCount();
  }

  goToCart(): void {
    this.router.navigate(['/cart']);
  }

}
