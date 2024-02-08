import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { LayoutComponent } from './pages/layout/layout.component';
import { HomeComponent } from './pages/home/home.component';
import { ProductComponent } from './pages/product/product.component';
import { MeasureUnitComponent } from './pages/measure-unit/measure-unit.component';
import { UsersComponent } from './pages/users/users.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'pages', component: LayoutComponent, children:[
    {path: 'home', component: HomeComponent},
    {path: 'products', component: ProductComponent},
    {path: 'measure-units', component: MeasureUnitComponent},
    {path: 'users', component: UsersComponent}
  ]}
];
