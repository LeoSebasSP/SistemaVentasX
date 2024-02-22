import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { MeasureUnit } from '../_model/measureUnit';
import { HttpClient } from '@angular/common/http';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MeasureUnitService extends GenericService<MeasureUnit>{

  constructor(
    protected override http: HttpClient,
    protected override loginService: LoginService,
    private router: Router
  ) {
    super(http, loginService, `${environment.HOST}/measure-units`)
  }
}
