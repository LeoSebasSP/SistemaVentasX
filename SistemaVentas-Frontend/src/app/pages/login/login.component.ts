import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../_services/login.service';
import { Login } from '../../_model/login';
import { environment } from '../../../environments/environment';
import { FormsModule } from '@angular/forms';
import { PrimengModule } from '../../_primeng/primeng.module';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-login',
  standalone: true,
  imports:[
    FormsModule,
    PrimengModule
  ],
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
    this.loginService.checkLog();
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
