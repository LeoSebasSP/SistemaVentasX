import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { map, switchMap, tap } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DataExportService {
  url: string = `${environment.HOST}/data-export`;

  constructor(
    private http: HttpClient,
    private loginService: LoginService
  ) {}

  exportProductsEnabledToExcel() {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.get(`${this.url}/products`, {
          responseType: 'blob' as 'blob',
          headers: headers
        });
      }),
      map(blob => {
        // Adjust the filename as needed
        const fileName = 'Productos Activos.xlsx';
        return { blob, fileName };
      }),
      tap(response => {
        const url = window.URL.createObjectURL(response.blob);
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.href = url;
        a.download = response.fileName;
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      })
    );
  }

  exportProductsDisabledToExcel() {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.get(`${this.url}/products-disabled`, {
          responseType: 'blob' as 'blob',
          headers: headers
        });
      }),
      map(blob => {
        // Adjust the filename as needed
        const fileName = 'Productos Inactivos.xlsx';
        return { blob, fileName };
      }),
      tap(response => {
        const url = window.URL.createObjectURL(response.blob);
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.href = url;
        a.download = response.fileName;
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      })
    );
  }
}
