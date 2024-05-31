import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderClientService } from '../services/order-client.service';
import { OrderOptionService } from '../services/order-option.service';
import { OrderClient } from '../models/orderClient';

@Component({
  selector: 'app-view-order',
  templateUrl: './view-order.component.html',
  styleUrls: ['./view-order.component.css']
})
export class ViewOrderComponent implements OnInit {
  order: OrderClient | undefined;
  message: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private orderClientService: OrderClientService,
    private orderOptionService: OrderOptionService
  ) {}

  ngOnInit(): void {
    const orderId = +this.route.snapshot.paramMap.get('id')!;
    this.orderClientService.getOrderById(orderId).subscribe((order) => {
      console.log('Loaded order:', order);
      this.order = order;
    });
  }

  toggleOrderConfirmed(): void {
    if (this.order && this.order.orderOptions) {
      const newStatus = !this.order.orderOptions.orderConfirmed;
      this.orderOptionService.updateOrderConfirmed(this.order.id, newStatus).subscribe(() => {
        if (this.order && this.order.orderOptions) {
          this.order.orderOptions.orderConfirmed = newStatus;
          console.log('Order status updated to:', newStatus);
        }
      });
    }
  }

  deleteOrder(): void {
    if (this.order && window.confirm('Are you sure you want to delete this order?')) {
      this.orderClientService.deleteOrderById(this.order.id).subscribe(() => {
        this.message = 'Order deleted successfully';
        setTimeout(() => this.router.navigate(['/admin-panel']), 2000);
      });
    }
  }

  adminBack(): void {
    this.router.navigate(['/admin-panel']);
  }
}
