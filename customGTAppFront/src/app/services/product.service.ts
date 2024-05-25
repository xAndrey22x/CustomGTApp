import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product';
import { Observable } from 'rxjs';
import { Photo } from '../models/photo';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = environment.apiUrl + '/product';

  constructor(private http: HttpClient) { }

  public getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl + '/all');
  }

  public getAllPhotosByProductId(productId: number): Observable<Photo[]> {
    return this.http.get<Photo[]>(this.apiUrl + '/' + productId + '/photos');
  }

  public getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(this.apiUrl + '/find/' + id);
  }

  public addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.apiUrl + '/add', product);
  }

  public updateProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(this.apiUrl + '/update', product);
  }

  public deleteProductById(id: number): Observable<Product> {
    return this.http.delete<Product>(this.apiUrl + '/delete/' + id);
  }

  public updateQuantity(productId: number, quantity: number): Observable<Product> {
    const url = `${this.apiUrl}/updateQuantity/${productId}?quantity=${quantity}`;
    return this.http.put<Product>(url, null);
  }

  public updatePrice(productId: number, price: number): Observable<Product> {
    const url = `${this.apiUrl}/updatePrice/${productId}?price=${price}`;
    return this.http.put<Product>(url, null);
  }

}
