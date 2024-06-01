import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../models/product';
import { ProductService } from '../services/product.service';
import { ShoppingCartService } from '../services/shopping-cart.service';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent {
  products: Product[] = [];
  filteredProducts: Product[] = [];
  searchTerm: string = '';
  selectedModel: string = '';
  carModels: string[] = [];
  cartItemCount: number = 0;

  constructor(private productService: ProductService, private router: Router,
    private shoppingCartService: ShoppingCartService
  ) {}

  ngOnInit(): void {
    this.loadProducts();
    this.updateCartItemCount();
  }

  loadProducts(): void {
    this.productService.getAllProducts().subscribe({
      next: (products) => {
        this.products = products;
        this.products = this.products.filter(product => product.quantity > 0);
        this.filteredProducts = this.products;
        this.carModels = Array.from(new Set(products.map(product => product.carModel)));
      },
      error: (error) => {
        console.error('Failed to load products', error);
      }
    });
  }

  searchProducts(): void {
    this.filteredProducts = this.products.filter(product =>
      product.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
    if (this.selectedModel) {
      this.filteredProducts = this.filteredProducts.filter(product => product.carModel === this.selectedModel);
    }
  }

  filterByModel(): void {
    this.filteredProducts = this.products.filter(product => 
      this.selectedModel ? product.carModel === this.selectedModel : true
    );
    if (this.searchTerm) {
      this.filteredProducts = this.filteredProducts.filter(product =>
        product.name.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    }
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

  updateCartItemCount(): void {
    this.cartItemCount = this.shoppingCartService.getCartItemCount();
  }

  goToCart(): void {
    this.router.navigate(['/cart']);
  }

}
