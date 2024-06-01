import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { ShoppingCartService } from '../services/shopping-cart.service';
import { OrderItem } from '../models/orderItem';
import { filter } from 'rxjs/operators';
import { NavigationService } from '../services/navigation.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems: OrderItem[] = [];

  constructor(private shoppingCartService: ShoppingCartService, private router: Router,
    private navigationService: NavigationService
  ) {}

  ngOnInit(): void {
    this.cartItems = this.shoppingCartService.getCartItems();
  }

  getFirstPhoto(item: OrderItem): string {
    return item.product?.photos.length ? item.product.photos[0].url : '/assets/placeholder.png';
  }

  getTotalPrice(): number {
    return this.cartItems.reduce((total, item) => total + item.price, 0);
  }

  proceedToCheckout(): void {
    this.router.navigateByUrl('/checkout');
  }

  goBack(): void {
    const previousUrl = this.navigationService.getPreviousUrl();
    this.router.navigateByUrl(previousUrl);
  }

  removeFromCart(index: number): void {
    this.cartItems.splice(index, 1);
    this.shoppingCartService.setCartItems(this.cartItems);
  }

  increaseQuantity(index: number): void {
    const price = this.cartItems[index].price / this.cartItems[index].quantity;
    this.cartItems[index].quantity++;
    this.cartItems[index].price = price * this.cartItems[index].quantity;
    this.shoppingCartService.setCartItems(this.cartItems);
  }

  decreaseQuantity(index: number): void {
    if (this.cartItems[index].quantity > 1) {
      const price = this.cartItems[index].price / this.cartItems[index].quantity;
      this.cartItems[index].quantity--;
      this.cartItems[index].price = price * this.cartItems[index].quantity;
      this.shoppingCartService.setCartItems(this.cartItems);
    }
  }

}
