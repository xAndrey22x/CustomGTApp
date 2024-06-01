import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../models/product';
import { ProductService } from '../services/product.service';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent {
  products: Product[] = [];
  filteredProducts: Product[] = [];
  searchTerm: string = '';

  constructor(private productService: ProductService, private router: Router) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getAllProducts().subscribe({
      next: (products) => {
        this.products = products;
        this.products = this.products.filter(product => product.quantity > 0);
        this.filteredProducts = this.products;
      },
      error: (error) => {
        console.error('Failed to load products', error);
      }
    });
  }

  searchProducts(): void {
    if (!this.searchTerm) {
      this.filteredProducts = this.products;
      return;
    }

    this.filteredProducts = this.products.filter(product =>
      product.name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      product.carModel.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  onProductClick(productId: number): void {
    this.router.navigate(['/product', productId]);
  }

  getFirstPhoto(product: Product): string {
    if (product.photos && product.photos.length > 0) {
      return product.photos[0].url;
    }
    return 'assets/placeholder.png';
  }

  goToHome(): void {
    this.router.navigate(['/']);
  }

}
