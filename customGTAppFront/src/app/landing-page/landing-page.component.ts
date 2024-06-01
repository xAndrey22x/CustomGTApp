import { Component } from '@angular/core';
import { OrderOptionService } from '../services/order-option.service';
import { OrderClientService } from '../services/order-client.service';
import { OrderClient } from '../models/orderClient';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {
  newsletterEmail: string = '';
  orders: OrderClient[] = [];

  constructor(
    private orderOptionService: OrderOptionService,
    private orderClientService: OrderClientService
  ) {}

  subscribeToNewsletter() {
    if (this.newsletterEmail) {
      this.orderClientService.getOrdersByEmail(this.newsletterEmail).subscribe({
        next: (orders) => {
          this.orders = orders;
          this.updateAllOrdersNewsletter(true);
        },
        error: (error) => {
          console.error('Subscription error', error);
        }
      });
    }
  }

  unsubscribeFromNewsletter() {
    if (this.newsletterEmail) {
      this.orderClientService.getOrdersByEmail(this.newsletterEmail).subscribe({
        next: (orders) => {
          this.orders = orders;
          this.updateAllOrdersNewsletter(false);
        },
        error: (error) => {
          console.error('Unsubscription error', error);
        }
      });
    }
  }

  private updateAllOrdersNewsletter(newsletter: boolean) {
    this.orders.forEach(order => {
      this.orderOptionService.updateOrderNewsletter(order.id, newsletter).subscribe({
        next: (orderOption) => {
          console.log(`Order ${order.id} updated successfully`);
        },
        error: (error) => {
          console.error(`Error updating order ${order.id}`, error);
        }
      });
    });
  }
}
