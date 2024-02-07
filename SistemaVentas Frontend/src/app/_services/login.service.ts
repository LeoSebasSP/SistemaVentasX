import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Login } from '../_model/login';
import { Observable, Subject } from 'rxjs';
import { Token } from '@angular/compiler';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url: string = `${environment.HOST}/authorization`;
  private tokensBackend !: string[];

  constructor(
    private http: HttpClient,
    private router: Router,
    private userService: UserService
  ) { }

  login(credentials: Login){
    return this.http.post<any>(`${this.url}/authenticate`, credentials);
  }

  checkLog() {
    const username = this.userService.getUsernameFromToken();
    this.http.post<string[]>(`${this.url}/get-tokens-user/${username}`, null).subscribe(data =>{
      if (data.length == 0) {
        sessionStorage.clear();
        this.router.navigate(['login']);
        return null;
      } else if (data[0] == sessionStorage.getItem(environment.TOKEN_NAME)) {
        return data[0]
      } else{
        sessionStorage.clear();
        this.router.navigate(['login']);
        return null;
      }
    });
  }

  logout() {
    this.http.post<any>(`${this.url}/logout`, null, {
      headers: new HttpHeaders()
      .set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8')
      .set('Authorization', 'Bearer ' + sessionStorage.getItem(environment.TOKEN_NAME))
    }).subscribe(()=>{
      sessionStorage.clear();
      this.router.navigate(['login']);
    });
  }
}
