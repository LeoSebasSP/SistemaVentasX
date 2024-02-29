import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { Product } from '../_model/product';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { LoginService } from './login.service';
import { switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService extends GenericService<Product>{

  constructor(
    protected override http: HttpClient,
    protected override loginService: LoginService,
    private router: Router
  ) {
    super(http, loginService, `${environment.HOST}/products`);
  }

  disableProducts(id: bigint) {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.put(`${this.url}/disable`, id, {headers});
      })
    )
  }

  enableProducts(id: bigint) {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.put(`${this.url}/enable`, id, {headers});
      })
    )
  }

  listarProductsDisable() {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.get<Product[]>(`${this.url}/disable`, {headers});
      })
    )
  }

}
