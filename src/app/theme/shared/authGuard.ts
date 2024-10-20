import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    token:any;
  constructor( private router: Router) {}

  canActivate(): boolean {
    console.log('from can Activate');
    
    const userString = localStorage.getItem('user');
    if (userString!=null) {
        const user = JSON.parse(userString); // Parse the string back into an object
        this.token=user.accessToken
        console.log('token');
        
    }
    else{
        this.router.navigate(['/auth/signin']);
      return false;
    }
    if (this.token) {
      console.log('True');
      return true;
    } else {
      this.router.navigate(['/auth/signin']);
      return false;
    }
  }
}
