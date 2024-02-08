import { Component } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Product } from '../../_model/product';
import { ProductService } from '../../_services/product.service';
import { PrimengModule } from '../../_primeng/primeng.module';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  standalone: true,
  imports:[
    PrimengModule,
    CommonModule,
    FormsModule
  ],
  providers:[
    MessageService,
    ConfirmationService
  ],
  styleUrls: ['./product.component.css']
})
export class ProductComponent {

  productDialog: boolean = false;

  products!: Product[];

  product!: Product;

  selectedProducts!: Product[] | null;

  submitted: boolean = false;

  statuses!: any[];

  constructor(
    private productService: ProductService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
    ){

  }

  ngOnInit() {
      this.productService.listar().subscribe((data) => (this.products = data));

      this.statuses = [
          { label: 'INSTOCK', value: 'instock' },
          { label: 'LOWSTOCK', value: 'lowstock' },
          { label: 'OUTOFSTOCK', value: 'outofstock' }
      ];
  }

  openNew() {
      // this.product = {};
      this.submitted = false;
      this.productDialog = true;
  }

  deleteSelectedProducts() {
      this.confirmationService.confirm({
          message: 'Are you sure you want to delete the selected products?',
          header: 'Confirm',
          icon: 'pi pi-exclamation-triangle',
          accept: () => {
              this.products = this.products.filter((val) => !this.selectedProducts?.includes(val));
              this.selectedProducts = null;
              this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Products Deleted', life: 3000 });
          }
      });
  }

  editProduct(product: Product) {
      this.product = { ...product };
      this.productDialog = true;
  }

  deleteProduct(product: Product) {
      // this.confirmationService.confirm({
      //     message: 'Are you sure you want to delete ' + product.name + '?',
      //     header: 'Confirm',
      //     icon: 'pi pi-exclamation-triangle',
      //     accept: () => {
      //         this.products = this.products.filter((val) => val.id !== product.id);
      //         this.product = {};
      //         this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Deleted', life: 3000 });
      //     }
      // });
  }

  hideDialog() {
      this.productDialog = false;
      this.submitted = false;
  }

  saveProduct() {
      // this.submitted = true;

      // if (this.product.name?.trim()) {
      //     if (this.product.id) {
      //         this.products[this.findIndexById(this.product.id)] = this.product;
      //         this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Updated', life: 3000 });
      //     } else {
      //         this.product.id = this.createId();
      //         this.product.image = 'product-placeholder.svg';
      //         this.products.push(this.product);
      //         this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Created', life: 3000 });
      //     }

      //     this.products = [...this.products];
      //     this.productDialog = false;
      //     this.product = {};
      // }
  }

  findIndexById(id: bigint): number {
      // let index = -1;
      // for (let i = 0; i < this.products.length; i++) {
      //     if (this.products[i].id === id) {
      //         index = i;
      //         break;
      //     }
      // }

      // return index;
      return 0;
  }

  createId(): string {
      // let id = '';
      // var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
      // for (var i = 0; i < 5; i++) {
      //     id += chars.charAt(Math.floor(Math.random() * chars.length));
      // }
      // return id;
      return 'null';
  }

  getSeverity(status: string) {
      // switch (status) {
      //     case 'INSTOCK':
      //         return 'success';
      //     case 'LOWSTOCK':
      //         return 'warning';
      //     case 'OUTOFSTOCK':
      //         return 'danger';
      // }
      return 'status';
  }
}
