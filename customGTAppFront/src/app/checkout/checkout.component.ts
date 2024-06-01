import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ShoppingCartService } from '../services/shopping-cart.service';
import { OrderClient} from '../models/orderClient';
import { OrderItem } from '../models/orderItem';
import { OrderOption } from '../models/orderOption';
import { OrderClientService } from '../services/order-client.service';
import { OrderItemService } from '../services/order-item.service';
import { OrderOptionService } from '../services/order-option.service';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css'
})
export class CheckoutComponent {
  checkoutForm: FormGroup;
  cartItems: OrderItem[] = [];
  totalPrice: number = 0;

  constructor(
    private fb: FormBuilder,
    private shoppingCartService: ShoppingCartService,
    private orderClientService: OrderClientService,
    private orderItemService: OrderItemService,
    private orderOptionService: OrderOptionService,
    private productService: ProductService,
    private router: Router
  ) {
    this.checkoutForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', Validators.required],
      county: ['', Validators.required],
      city: ['', Validators.required],
      address: ['', Validators.required],
      postalCode: ['', Validators.required],
      newsletter: [false]
    });
  }

  ngOnInit(): void {
    this.cartItems = this.shoppingCartService.getCartItems();
    this.totalPrice = this.getTotalPrice();
  }

  getTotalPrice(): number {
    return this.cartItems.reduce((total, item) => total + item.price, 0);
  }

  getFirstPhoto(item: OrderItem): string {
    return item.product?.photos.length ? item.product.photos[0].url : '/assets/placeholder.png';
  }


  goHome(): void {
    this.router.navigate(['/']);
  }

  onSubmit(): void {
    if (this.checkoutForm.invalid || this.cartItems.length === 0) {
      return;
    }

    const orderClient: OrderClient = {
      ...this.checkoutForm.value,
      totalPrice: this.totalPrice,
      orderItems: [],
      orderOptions: null
    };

    this.orderClientService.addOrder(orderClient).subscribe({
      next: (createdOrder: OrderClient) => {
        const orderOptions: OrderOption = {
          id: 0,
          newsletter: this.checkoutForm.get('newsletter')?.value,
          orderConfirmed: false,
          orderClient: createdOrder
        };

        this.orderOptionService.addOrderOptions(orderOptions, createdOrder.id).subscribe({
          next: () => {
            this.addProductsSequentially(this.cartItems, createdOrder.id);
          },
          error: (error) => console.error('Error adding order options', error)
        });
      },
      error: (error) => console.error('Error creating order', error)
    });
  }

  addProductsSequentially(items: OrderItem[], orderId: number, index: number = 0): void {
    if (index >= items.length) {
      this.shoppingCartService.clearCart();
      this.router.navigate(['/']);
      return;
    }

    const item = items[index];
    this.productService.updateQuantity(item.product?.id, (item.product.quantity - item.quantity)).subscribe({
      next: () => {},
      error: (error) => console.error(`Error updating product quantity ${item.product?.id}`, error)
    });
    this.orderItemService.addProductToOrder(item.quantity, item.product?.id, orderId).subscribe({
      next: () => this.addProductsSequentially(items, orderId, index + 1),
      error: (error) => console.error(`Error adding product to order ${item.product?.id || item.serviceProd?.id}`, error)
    });
  }
}
