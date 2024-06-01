import { Component, OnInit } from '@angular/core';
import { OrderOptionService } from '../services/order-option.service';
import { OrderClientService } from '../services/order-client.service';
import { OrderClient } from '../models/orderClient';
import { Router } from '@angular/router';
import { ShoppingCartService } from '../services/shopping-cart.service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit{
  newsletterEmail: string = '';
  orders: OrderClient[] = [];
  newsletterMessage: string = '';
  newsletterError: string = '';
  cartItemCount: number = 0;

  constructor(
    private orderOptionService: OrderOptionService,
    private orderClientService: OrderClientService,
    private router: Router,
    private shoppingCartService: ShoppingCartService
  ) {}
  ngOnInit(): void {
    this.updateCartItemCount();
  }

  subscribeToNewsletter() {
    if (this.newsletterEmail) {
      this.orderClientService.getOrdersByEmail(this.newsletterEmail).subscribe({
        next: (orders) => {
          this.orders = orders;
          this.updateAllOrdersNewsletter(true);
        },
        error: (error) => {
          console.error('Subscription error', error);
          this.newsletterError = 'Email don\'t exist in our database';
          setTimeout(() => this.newsletterError = '', 3000);
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
          this.newsletterError = 'Email don\'t exist in our database';
          setTimeout(() => this.newsletterError = '', 3000);
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
    this.newsletterMessage = 'Newsletter subscription updated successfully';
    setTimeout(() => this.newsletterMessage = '', 3000);
  }

  goToCart(): void {
    this.router.navigate(['/cart']);
  }

  updateCartItemCount(): void {
    this.cartItemCount = this.shoppingCartService.getCartItemCount();
  }

}
