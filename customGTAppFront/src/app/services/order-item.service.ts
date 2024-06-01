import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrderItem } from '../models/orderItem';

@Injectable({
  providedIn: 'root'
})
export class OrderItemService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public addProductToOrder(quantity: number, productId: number, orderId: number): Observable<OrderItem> {
    const url = `${this.apiUrl}/addOrderProduct/${productId}/${orderId}?quantity=${quantity}`;
    return this.http.post<OrderItem>(url, null);
  }

  public addServiceToOrder(quantity: number, serviceId: number, orderId: number): Observable<OrderItem> {
    const url = `${this.apiUrl}/addOrderService/${serviceId}/${orderId}?quantity=${quantity}`;
    return this.http.post<OrderItem>(url, null);
  }
}
