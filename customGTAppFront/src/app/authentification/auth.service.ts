import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = false;

  login(username: string, password: string, keepSignedIn: boolean): boolean {
    if (username === 'admin' && password === '1234') {
      this.loggedIn = true;
      if (keepSignedIn) {
        if (typeof window !== 'undefined' && window.sessionStorage) {
          sessionStorage.setItem('isLoggedIn', 'true');
        }
      }
      return true;
    }
    return false;
  }

  logout(): void {
    this.loggedIn = false;
    if (typeof window !== 'undefined' && window.sessionStorage) {
      sessionStorage.removeItem('isLoggedIn');
    }
  }

  isLoggedIn(): boolean {
    if (this.loggedIn) {
      return true;
    }
    if (typeof window !== 'undefined' && window.sessionStorage) {
      const sessionLoggedIn = sessionStorage.getItem('isLoggedIn');
      return sessionLoggedIn === 'true';
    }
    return false;
  }
}
