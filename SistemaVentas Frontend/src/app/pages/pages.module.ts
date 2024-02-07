import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrimengModule } from '../_primeng/primeng.module';
import { HomeComponent } from './home/home.component';
import { PagesRoutingModule } from './pages-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LayoutComponent } from './layout/layout.component';
import { ProductComponent } from './product/product.component';
import { MeasureUnitComponent } from './measure-unit/measure-unit.component';
import { UsersComponent } from './users/users.component';
import { ConfirmationService, MessageService } from 'primeng/api';



@NgModule({
  declarations: [
    HomeComponent,
    LayoutComponent,
    ProductComponent,
    MeasureUnitComponent,
    UsersComponent
  ],
  imports: [
    CommonModule,
    PrimengModule,
    PagesRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    MessageService,
    ConfirmationService
  ]
})
export class PagesModule { }
