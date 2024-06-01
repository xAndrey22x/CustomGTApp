import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceProd } from '../models/serviceProd'; // Adjust the path as needed
import { ServiceProdService } from '../services/service-prod.service'; // Adjust the path as needed


@Component({
  selector: 'app-service-prods',
  templateUrl: './service-prods.component.html',
  styleUrl: './service-prods.component.css'
})
export class ServiceProdsComponent {
  services: ServiceProd[] = [];
  filteredServices: ServiceProd[] = [];
  searchTerm: string = '';

  constructor(private ServiceProdService: ServiceProdService, private router: Router) {}

  ngOnInit(): void {
    this.loadServices();
  }

  loadServices(): void {
    this.ServiceProdService.getAllServices().subscribe({
      next: (services) => {
        this.services = services;
        this.filteredServices = services;
      },
      error: (error) => {
        console.error('Failed to load services', error);
      }
    });
  }

  searchServices(): void {
    if (!this.searchTerm) {
      this.filteredServices = this.services;
      return;
    }

    this.filteredServices = this.services.filter(service =>
      service.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  onServiceClick(serviceId: number): void {
    this.router.navigate(['/service', serviceId]);
  }

  getFirstPhoto(service: ServiceProd): string {
    if (service.photos && service.photos.length > 0) {
      return service.photos[0].url;
    }
    return 'assets/placeholder.png';
  }

  goToHome(): void {
    this.router.navigate(['/']);
  }
  
}
