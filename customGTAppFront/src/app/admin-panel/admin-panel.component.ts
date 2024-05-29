import { Component, OnInit } from '@angular/core';
import { OrderClient } from '../models/orderClient';
import { ServiceProd } from '../models/serviceProd';
import { Product } from '../models/product';
import { ProductService } from '../services/product.service';
import { ServiceProdService } from '../services/service-prod.service';
import { OrderClientService } from '../services/order-client.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TableStateService } from '../services/table-state.service';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {
  products: Product[] = [];
  filteredProducts: Product[] = [];
  serviceProds: ServiceProd[] = [];
  filteredServiceProds: ServiceProd[] = [];
  orders: OrderClient[] = [];
  filteredOrders: OrderClient[] = [];
  selectedTable: string = '';
  searchQuery: string = '';

  constructor(
    private productService: ProductService,
    private serviceProdService: ServiceProdService,
    private orderService: OrderClientService,
    private router: Router,
    private tableStateService: TableStateService
  ) {}

  selectTable(tab: string) {
    this.selectedTable = tab;
    this.tableStateService.setSelectedTable(tab);
    this.onSearch();
  }

  ngOnInit(): void {
    this.selectedTable = this.tableStateService.getSelectedTable();
    this.loadProducts();
    this.loadServiceProds();
    this.loadOrders();
  }

  loadProducts(): void {
    this.productService.getAllProducts().subscribe({
      next: (products) => {
        this.products = products;
        this.filteredProducts = products;
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
        this.filteredServiceProds = serviceProds;
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
      next: (orders) => {
        this.orders = orders;
        this.filteredOrders = orders;
      },
      error: (err) => {
        console.log('Error loading orders', err);
      },
      complete: () => {
        console.log('Orders loading completed');
      }
    });
  }

  addProduct() {
    this.router.navigate(['/add-product']);
  }

  editProduct(product: Product) {
    this.router.navigate(['/edit-product', product.id]);
  }

  deleteProduct(productId: number): void {
    this.productService.deleteProductById(productId).subscribe({
      next: () => {
        this.products = this.products.filter(product => product.id !== productId);
        this.filteredProducts = this.filteredProducts.filter(product => product.id !== productId);
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
    this.router.navigate(['/add-service-prod']);
  }

  editServiceProd(serviceProd: ServiceProd) {
    this.router.navigate(['/edit-service-prod', serviceProd.id]);
  }

  deleteServiceProd(serviceProdId: number) {
    this.serviceProdService.deleteServiceById(serviceProdId).subscribe({
      next: () => {
        this.serviceProds = this.serviceProds.filter(serviceProd => serviceProd.id !== serviceProdId);
        this.filteredServiceProds = this.filteredServiceProds.filter(serviceProd => serviceProd.id !== serviceProdId);
        console.log('Service product deleted successfully');
      },
      error: (err) => {
        console.error('Error deleting service product', err);
      }
    });
  }

  confirmDeleteServiceProd(serviceProdId: number): void {
    if (window.confirm('Are you sure you want to delete this service product?')) {
      this.deleteServiceProd(serviceProdId);
    }
  }

  viewOrder(order: OrderClient) {
    // Implement view order details logic here
  }

  deleteOrder(orderId: number) {
    this.orderService.deleteOrderById(orderId).subscribe({
      next: () => {
        this.orders = this.orders.filter(order => order.id !== orderId);
        this.filteredOrders = this.filteredOrders.filter(order => order.id !== orderId);
        console.log('Order deleted successfully');
      },
      error: (err) => {
        console.error('Error deleting order', err);
      }
    });
  }

  confirmDeleteOrder(orderId: number): void {
    if (window.confirm('Are you sure you want to delete this order?')) {
      this.deleteOrder(orderId);
    }
  }

  onSearch(): void {
    const query = this.searchQuery.toLowerCase();
    if (this.selectedTable === 'products') {
      this.filteredProducts = this.products.filter(product =>
        product.name.toLowerCase().includes(query)
      );
    } else if (this.selectedTable === 'serviceProds') {
      this.filteredServiceProds = this.serviceProds.filter(serviceProd =>
        serviceProd.name.toLowerCase().includes(query)
      );
    } else if (this.selectedTable === 'orders') {
      this.filteredOrders = this.orders.filter(order =>
        order.email.toLowerCase().includes(query)
      );
    }
  }
}