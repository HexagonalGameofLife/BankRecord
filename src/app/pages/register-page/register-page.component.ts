import { Component } from '@angular/core';

@Component({
  selector: 'app-register-page',
  standalone: false,
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.css'
})
export class RegisterPageComponent {

  step: number = 1;

  nextStep() {
    if (this.step < 3) {
      this.step++;
    }
  }

}
