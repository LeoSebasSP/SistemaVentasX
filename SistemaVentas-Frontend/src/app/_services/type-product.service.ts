import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { GroupProduct } from '../_model/groupProduct';
import { HttpClient } from '@angular/common/http';
import { LoginService } from './login.service';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { TypeProduct } from '../_model/typeProduct';
import { CategoryProduct } from '../_model/categoryProduct';

@Injectable({
  providedIn: 'root'
})
export class TypeProductService extends GenericService<TypeProduct>{

  constructor(
    protected override http: HttpClient,
    protected override loginService: LoginService,
    private router: Router
  ) {
    super(http, loginService, `${environment.HOST}/types-product`)
  }

  listarByCategoryProduct(categoryProduct: CategoryProduct) {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.post<TypeProduct[]>(`${this.url}/by-group-product`, categoryProduct, {headers});
      })
    )
  }
}
