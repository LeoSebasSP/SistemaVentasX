import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { GroupProduct } from '../_model/groupProduct';
import { HttpClient } from '@angular/common/http';
import { LoginService } from './login.service';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class GroupProductService extends GenericService<GroupProduct>{

  constructor(
    protected override http: HttpClient,
    protected override loginService: LoginService,
    private router: Router
  ) {
    super(http, loginService, `${environment.HOST}/groups-product`)
  }
}
