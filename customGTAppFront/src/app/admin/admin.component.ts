import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../authentification/auth.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  loginForm: FormGroup;
  submitted = false;
  invalidLogin = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService
  ) {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      keepSignedIn: [false]
    });
  }

  get formControls() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    const formValue = this.loginForm.value;
    if (this.authService.login(formValue['username'], formValue['password'], formValue['keepSignedIn'])) {
      this.invalidLogin = false;
      this.router.navigate(['/admin-panel']);
    } else {
      this.invalidLogin = true;
    }
  }
}
