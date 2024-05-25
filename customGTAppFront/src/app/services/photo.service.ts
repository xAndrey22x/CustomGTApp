import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Photo } from '../models/photo';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {
  private apiUrl = environment.apiUrl + '/photo';

  constructor(private http: HttpClient) { }

  public getAllPhotos(): Observable<Photo[]> {
    return this.http.get<Photo[]>(this.apiUrl + '/all');
  }

  public addPhotoToProduct(photo: Photo, productId: number): Observable<Photo> {
    return this.http.post<Photo>(this.apiUrl + '/addProduct/' + productId, photo);
  }

  public addPhotoToService(photo: Photo, serviceId: number): Observable<Photo> {
    return this.http.post<Photo>(this.apiUrl + '/addService/' + serviceId, photo);
  }

  public updatePhotoProduct(photo: Photo): Observable<Photo> {
    return this.http.put<Photo>(this.apiUrl + '/updateProduct', photo);
  }

  public updatePhotoService(photo: Photo): Observable<Photo> {
    return this.http.put<Photo>(this.apiUrl + '/updateService', photo);
  }

  public deletePhoto(id: number): Observable<Photo> {
    return this.http.delete<Photo>(this.apiUrl + '/delete/' + id);
  }

}
