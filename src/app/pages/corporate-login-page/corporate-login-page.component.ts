import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-corporate-login-page',
  standalone: false,
  templateUrl: './corporate-login-page.component.html',
  styleUrl: './corporate-login-page.component.css'
})
export class CorporateLoginPageComponent {

  constructor(private router: Router) {}

  goToLogin() {
    this.router.navigateByUrl('/login');
  }

  goToRegister() {
    this.router.navigateByUrl('/register');
  }

}
