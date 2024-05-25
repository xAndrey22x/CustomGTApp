import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrderOption } from '../models/orderOption';

@Injectable({
  providedIn: 'root'
})
export class OrderOptionService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public addOrderOptions(orderOption: OrderOption, orderId: number): Observable<OrderOption> {
    return this.http.post<OrderOption>(this.apiUrl + '/addOrderOptions/' + orderId, orderOption);
  }

  public updateOrderNewsletter(orderId: number, newsletter: boolean): Observable<OrderOption> {
    const url = `${this.apiUrl}/updateOrderNewsletter/${orderId}?newsletter=${newsletter}`;
    return this.http.put<OrderOption>(url, null);
  }

  public updateOrderConfirmed(orderId: number, orderConfirmed: boolean): Observable<OrderOption> {
    const url = `${this.apiUrl}/updateOrderConfirmed/${orderId}?orderConfirmed=${orderConfirmed}`;
    return this.http.put<OrderOption>(url, null);
  }

}
