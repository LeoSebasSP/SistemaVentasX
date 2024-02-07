import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from 'src/app/_model/login';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LoginService } from 'src/app/_services/login.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user!: string;
  password!: string;
  credentials!: Login;

  constructor(
    private loginService: LoginService,
    private router: Router,
  ) {
    this.credentials = new Login();
  }

  ngOnInit(): void {
    if (this.loginService.checkLog() == null) {
      this.router.navigate(['/pages/home']);
    }
  }

  iniciarSesion() {
    this.credentials.username = this.user;
    this.credentials.password = this.password
    this.loginService.login(this.credentials).subscribe(data => {
      sessionStorage.setItem(environment.TOKEN_NAME, data.access_token);
      sessionStorage.setItem(environment.REFRESHTOKEN_NAME, data.refresh_token);

      this.router.navigate(['/pages/home']);
    });
  }
}
