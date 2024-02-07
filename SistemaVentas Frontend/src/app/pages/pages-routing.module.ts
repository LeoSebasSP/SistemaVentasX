import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProductComponent } from './product/product.component';
import { MeasureUnitComponent } from './measure-unit/measure-unit.component';
import { UsersComponent } from './users/users.component';

const routes: Routes = [
  {
    path: 'home', component: HomeComponent
  },
  {
    path: 'product', component: ProductComponent
  },
  {
    path: 'measure-unit', component: MeasureUnitComponent
  },
  {
    path: 'users', component: UsersComponent
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagesRoutingModule { }
