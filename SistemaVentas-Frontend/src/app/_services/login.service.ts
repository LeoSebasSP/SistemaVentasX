import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Login } from '../_model/login';
import { UserService } from './user.service';
import { environment } from '../../environments/environment';
import { of, switchMap } from 'rxjs';
import { User } from '../_model/user';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url: string = `${environment.HOST}/authorization`;

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  login(credentials: Login) {
    return this.http.post<any>(`${this.url}/authenticate`, credentials);
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

  checkLog() {
    const username = this.getUsernameFromToken();
    const token = sessionStorage.getItem(environment.TOKEN_NAME);
    this.http.post<boolean>(`${this.url}/get-tokens-user/${username}&${token}`, null).subscribe(data => {
      if (data != null) {
        if (data) {
          this.router.navigate(['/pages/home']);
        } else {
          sessionStorage.clear();
          this.router.navigate(['login']);
        }
      } else {
        sessionStorage.clear();
        this.router.navigate(['login']);
      }
    });
  }

  getAuthTokenHeaders() {
    const username = this.getUsernameFromToken();
    const token = sessionStorage.getItem(environment.TOKEN_NAME);
    return this.http.post(`${this.url}/get-tokens-user/${username}&${token}`, null).pipe(
      switchMap(isValid => {
        if (isValid) {
          return of(
            new HttpHeaders()
              .set('Authorization', 'Bearer ' + sessionStorage.getItem(environment.TOKEN_NAME))
          );
        } else {
          sessionStorage.clear();
          this.router.navigate(['login']);
          return of(new HttpHeaders());
        }
      })
    )
  }

  logout() {
    this.getAuthTokenHeaders().subscribe(headers => {
      this.http.post<any>(`${this.url}/logout`, null, {headers})
      .subscribe(() => {
        sessionStorage.clear();
        this.router.navigate(['login']);
      });
    });
  }
}
