import { Component, OnInit } from '@angular/core';
import { OrderClient } from '../models/orderClient';
import { ServiceProd } from '../models/serviceProd';
import { Product } from '../models/product';
import { ProductService } from '../services/product.service';
import { ServiceProdService } from '../services/service-prod.service';
import { OrderClientService } from '../services/order-client.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrl: './admin-panel.component.css'
})
export class AdminPanelComponent implements OnInit{
  products: Product[] = [];
  serviceProds: ServiceProd[] = [];
  orders: OrderClient[] = [];
  selectedTable: string = 'products';

  constructor(private productService: ProductService
    , private serviceProdService: ServiceProdService
    , private orderService: OrderClientService
    , private router: Router
  ) {}

  selectTable(tab: string) {
    this.selectedTable = tab;
  }

  ngOnInit(): void {
    this.loadProducts();
    this.loadServiceProds();
    this.loadOrders();
  }

  loadProducts(): void {
    this.productService.getAllProducts().subscribe({
      next: (products) => {
        this.products = products;
      },
      error: (err) => {
        console.error('Error loading products', err);
      },
      complete: () => {
        console.log('Product loading completed');
      }
    });
  }

  loadServiceProds(): void {
    this.serviceProdService.getAllServices().subscribe({
      next: (serviceProds) => {
        this.serviceProds = serviceProds;
      },
      error: (err) => {
        console.error('Error loading service products', err);
      },
      complete: () => {
        console.log('Service product loading completed');
      }
    });
  }

  loadOrders(): void {
    this.orderService.getAllOrders().subscribe({
      next: (order) => {
        this.orders = order;
      },
      error: (err) => {
        console.log('Error loading orders', err);
      },
      complete: () => {
        console.log('Orders loading completed');
      }
    })
  }

    addProduct() {
      // Implement add product logic here
    }

    editProduct(product: Product) {
      this.router.navigate(['/edit-product', product.id]);
    }

    deleteProduct(productId: number): void {
      this.productService.deleteProductById(productId).subscribe({
        next: () => {
          this.products = this.products.filter(product => product.id !== productId);
          console.log('Product deleted successfully');
        },
        error: (err) => {
          console.error('Error deleting product', err);
        }
      });
    }

    confirmDeleteProduct(productId: number): void {
      if (window.confirm('Are you sure you want to delete this product?')) {
        this.deleteProduct(productId);
      }
    }

  addServiceProd() {
    // Implement add service product logic here
  }

  editServiceProd(serviceProd: ServiceProd) {
    this.router.navigate(['/edit-service-prod', serviceProd.id]);
  }

  deleteServiceProd(serviceProdId: number) {
    // Implement delete service product logic here
  }

  confirmDeleteServiceProd(serviceProdId: number): void {
  }

  viewOrder(order: OrderClient) {
    // Implement view order details logic here
  }

  deleteOrder(orderId: number) {
    // Implement delete order logic here
  }

  confirmDeleteOrder(orderId: number): void {

  }

}
