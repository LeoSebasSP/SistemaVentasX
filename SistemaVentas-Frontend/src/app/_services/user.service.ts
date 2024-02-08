import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { User } from '../_model/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Menu } from '../_model/menu';
import { Router } from '@angular/router';
import { Submenu } from '../_model/submenu';
import { environment } from '../../environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LoginService } from './login.service';
import { Observable, switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService extends GenericService<User>{

  constructor(
    protected override http: HttpClient,
    private router: Router,
    private loginService: LoginService
  ) {
    super(http, `${environment.HOST}/users`);
  }

  listMenuByUsername() {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        let username = this.loginService.getUsernameFromToken();
        return this.http.get<Menu[]>(`${this.url}/menus-by-username/${username}`, {headers});
      })
    )
  }

  listSubmenuByUsername() {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        let username = this.loginService.getUsernameFromToken();
        return this.http.get<Submenu[]>(`${this.url}/submenus-by-username/${username}`, {headers});
      })
    )
  }
}
