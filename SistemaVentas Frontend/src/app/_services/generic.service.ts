import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GenericService<T> {

  constructor(
    protected http: HttpClient,
    @Inject('url') protected url: string
  ) {}

  listarPagination(p:number, s:number) {
    return this.http.get<any>(`${this.url}/pagination/?page=${p}&size=${s}`);
  }

  listar() {
    return this.http.get<T[]>(`${this.url}`);
  }

  listarPorId(id: number) {
    return this.http.get<T>(`${this.url}/${id}`);
  }

  registrar(t: T) {
    return this.http.post(this.url, t);
  }

  modificar(t: T) {
    return this.http.put(this.url, t);
  }

  delete(id: number) {
    return this.http.delete(`${this.url}/${id}`);
  }
}
