import { Injectable } from '@angular/core';
import { OrderItem } from '../models/orderItem';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  private storageKey = 'shoppingCart';

  constructor() {}

  private isBrowser(): boolean {
    return typeof window !== 'undefined' && typeof sessionStorage !== 'undefined';
  }

  getCartItems(): OrderItem[] {
    if (this.isBrowser()) {
      const items = sessionStorage.getItem(this.storageKey);
      return items ? JSON.parse(items) : [];
    }
    return [];
  }

  addCartItem(item: OrderItem): void {
    if (this.isBrowser()) {
      const items = this.getCartItems();
      items.push(item);
      sessionStorage.setItem(this.storageKey, JSON.stringify(items));
    }
  }

  setCartItems(items: OrderItem[]): void {
    if (this.isBrowser()) {
      sessionStorage.setItem(this.storageKey, JSON.stringify(items));
    }
  }

  getCartItemCount(): number {
    if (this.isBrowser()) {
      return this.getCartItems().map((item) => item.quantity).reduce((prev, curr) => prev + curr, 0)
    }
    return 0;
  }

  clearCart(): void {
    if (this.isBrowser()) {
      sessionStorage.removeItem(this.storageKey);
    }
  }
}
