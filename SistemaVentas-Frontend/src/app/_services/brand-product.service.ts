import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { BrandProduct } from '../_model/brandProduct';
import { HttpClient } from '@angular/common/http';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BrandProductService extends GenericService<BrandProduct>{

  constructor(
    protected override http: HttpClient,
    protected override loginService: LoginService,
    private router: Router
  ) {
    super(http, loginService, `${environment.HOST}/brands-product`)
  }

  listarBrandsEnabled() {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.get<BrandProduct[]>(`${this.url}`, {headers});
      })
    )
  }
}
