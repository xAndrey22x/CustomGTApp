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

  public addProductToOrder(orderItem: OrderItem, productId: number, orderId: number): Observable<OrderItem> {
    return this.http.post<OrderItem>(this.apiUrl + '/addOrderProduct/' + productId + '/' + orderId, orderItem);
  }

  public addServiceToOrder(orderItem: OrderItem, serviceId: number, orderId: number): Observable<OrderItem> {
    return this.http.post<OrderItem>(this.apiUrl + '/addOrderService/' + serviceId + '/' + orderId, orderItem);
  }
}
