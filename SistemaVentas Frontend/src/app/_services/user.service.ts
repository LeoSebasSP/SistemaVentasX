import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { User } from '../_model/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Menu } from '../_model/menu';
import { Router } from '@angular/router';
import { Submenu } from '../_model/submenu';

@Injectable({
  providedIn: 'root'
})
export class UserService extends GenericService<User>{

  constructor(
    protected override http: HttpClient,
    private router: Router
  ) {
    super(http, `${environment.HOST}/users`);
  }

  listMenuByUsername() {
    let username = this.getUsernameFromToken();
    return this.http.get<Menu[]>(`${this.url}/menus-by-username/${username}`, {
      headers: new HttpHeaders()
      .set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8')
      .set('Authorization', 'Bearer ' + sessionStorage.getItem(environment.TOKEN_NAME))
    });
  }

  listSubmenuByUsername() {
    let username = this.getUsernameFromToken();
    return this.http.get<Submenu[]>(`${this.url}/submenus-by-username/${username}`, {
      headers: new HttpHeaders()
      .set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8')
      .set('Authorization', 'Bearer ' + sessionStorage.getItem(environment.TOKEN_NAME))
    });
  }

  getUsernameFromToken(){
    const helper = new JwtHelperService();
    let token = sessionStorage.getItem(environment.TOKEN_NAME);
    if (token !== null) {
      const decodedToken = helper.decodeToken(token);
      return decodedToken.sub;
    } else {
      sessionStorage.clear();
      this.router.navigate(['login']);
    }
  }
}
