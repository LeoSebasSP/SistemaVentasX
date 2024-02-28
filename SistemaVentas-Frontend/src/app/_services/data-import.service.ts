import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { switchMap } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DataImportService {
  url: string = `${environment.HOST}/data-import`;

  constructor(
    private http: HttpClient,
    private loginService: LoginService
  ) {}

  importProducts(file: File) {
    const formData = new FormData();
    formData.append('file', file);
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.post(`${this.url}/products`, formData, {headers});
      })
    )
  }
}
