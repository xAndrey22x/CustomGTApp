import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { ServiceProd } from '../models/serviceProd';
import { Photo } from '../models/photo';

@Injectable({
  providedIn: 'root'
})
export class ServiceProdService {
  private apiUrl = environment.apiUrl + '/service';

  constructor(private http: HttpClient) { }

  public getAllServices(): Observable<ServiceProd[]> {
    return this.http.get<ServiceProd[]>(this.apiUrl + '/all');
  }

  public getServiceById(id: number): Observable<ServiceProd> {
    return this.http.get<ServiceProd>(this.apiUrl + '/find/' + id);
  }

  public addService(service: ServiceProd): Observable<ServiceProd> {
    return this.http.post<ServiceProd>(this.apiUrl + '/add', service);
  }

  public updateService(service: ServiceProd): Observable<ServiceProd> {
    return this.http.put<ServiceProd>(this.apiUrl + '/update', service);
  }

  public deleteServiceById(id: number): Observable<ServiceProd> {
    return this.http.delete<ServiceProd>(this.apiUrl + '/delete/' + id);
  }

  public getAllPhotosByServiceId(serviceId: number): Observable<Photo[]> {
    return this.http.get<Photo[]>(this.apiUrl + '/' + serviceId + '/photos');
  }

  public updatePrice(serviceId: number, price: number): Observable<ServiceProd> {
    const url = `${this.apiUrl}/updatePrice/${serviceId}?price=${price}`;
    return this.http.put<ServiceProd>(url, null);
  }

}
