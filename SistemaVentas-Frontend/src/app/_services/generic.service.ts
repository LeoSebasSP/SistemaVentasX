import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GenericService<T> {

  constructor(
    protected http: HttpClient,
    protected loginService: LoginService,
    @Inject('url') protected url: string
  ) {}

  // listarPagination(p:number, s:number) {
  //   return this.loginService.getAuthTokenHeaders().pipe(
  //     switchMap(headers => {
  //       return this.http.get<any>(`${this.url}/pagination/?page=${p}&size=${s}`, {headers});
  //     })
  //   )
  // }

  findAllEnabledTrueOrderDesc() {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.get<T[]>(`${this.url}`, {headers});
      })
    )
  }

  findAllEnabledFalseOrderDesc() {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.get<T[]>(`${this.url}/disabled`, {headers});
      })
    )
  }

  listarPorId(id: number) {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.get<T>(`${this.url}/${id}`, {headers});
      })
    )
  }

  registrar(t: T) {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.post(this.url, t, {headers});
      })
    )
  }

  modificar(t: T) {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.put(this.url, t, {headers});
      })
    )
  }

  delete(id: number) {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.delete(`${this.url}/${id}`, {headers});
      })
    )
  }

  findAllEnabledTrueOrderDescPagination(p:number, s:number) {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.get<any>(`${this.url}/pagination?page=${p}&size=${s}`, {headers});
      })
    )
  }

  findAllEnabledFalseOrderDescPagination(p:number, s:number) {
    return this.loginService.getAuthTokenHeaders().pipe(
      switchMap(headers => {
        return this.http.get<any>(`${this.url}/pagination-disabled?page=${p}&size=${s}`, {headers});
      })
    )
  }

}
