import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { Product } from '../_model/product';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService extends GenericService<Product>{

  constructor(
    protected override http: HttpClient,
    private router: Router
  ) {
    super(http, `${environment.HOST}/products`);
  }
}
