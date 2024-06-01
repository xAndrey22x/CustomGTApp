import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceProd } from '../models/serviceProd';
import { ServiceProdService } from '../services/service-prod.service';

@Component({
  selector: 'app-service-details',
  templateUrl: './service-details.component.html',
  styleUrl: './service-details.component.css'
})
export class ServiceDetailsComponent {
  service: ServiceProd | undefined;

  constructor(
    private route: ActivatedRoute,
    private serviceService: ServiceProdService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const serviceId = +this.route.snapshot.paramMap.get('id')!;
    this.serviceService.getServiceById(serviceId).subscribe({
      next: (service) => {
        this.service = service;
      },
      error: (error) => {
        console.error('Failed to load service', error);
      }
    });
  }

  goToServices(): void {
    this.router.navigate(['/services']);
  }

  getSecondPhoto(service: ServiceProd): string {
    return service.photos.length > 1 ? service.photos[1].url : '/assets/placeholder.png';
  }
}
