import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrderClient } from '../models/orderClient';

@Injectable({
  providedIn: 'root'
})
export class OrderClientService {

  private apiUrl = environment.apiUrl + '/order';

  constructor(private http: HttpClient) { }

  public getAllOrders(): Observable<OrderClient[]> {
    return this.http.get<OrderClient[]>(this.apiUrl + '/all');
  }

  public getOrderById(id: number): Observable<OrderClient> {
    return this.http.get<OrderClient>(this.apiUrl + '/find/' + id);
  }

  public addOrder(order: OrderClient): Observable<OrderClient> {
    return this.http.post<OrderClient>(this.apiUrl + '/add', order);
  }

  public updateOrder(order: OrderClient): Observable<OrderClient> {
    return this.http.put<OrderClient>(this.apiUrl + '/update', order);
  }

  public deleteOrderById(id: number): Observable<OrderClient> {
    return this.http.delete<OrderClient>(this.apiUrl + '/delete/' + id);
  }

  public findAllOrderConfirmed(): Observable<OrderClient[]> {
    return this.http.get<OrderClient[]>(this.apiUrl + '/confirmed');
  }

  public findAllOrderNotConfirmed(): Observable<OrderClient[]> {
    return this.http.get<OrderClient[]>(this.apiUrl + '/notConfirmed');
  }

  public findNewsletterStatus(id: number): Observable<number> {
    return this.http.get<number>(this.apiUrl + '/newsletterStatus/' + id);
  }

}
