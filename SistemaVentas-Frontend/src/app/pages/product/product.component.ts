import { Component, Renderer2, ViewChild } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Product } from '../../_model/product';
import { ProductService } from '../../_services/product.service';
import { PrimengModule } from '../../_primeng/primeng.module';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Table } from 'primeng/table'
import { GroupProductService } from '../../_services/group-product.service';
import { GroupProduct } from '../../_model/groupProduct';
import { CategoryProduct } from '../../_model/categoryProduct';
import { TypeProduct } from '../../_model/typeProduct';
import { CategoryProductService } from '../../_services/category-product.service';
import { TypeProductService } from '../../_services/type-product.service';
import { BrandProduct } from '../../_model/brandProduct';
import { BrandProductService } from '../../_services/brand-product.service';
import { MeasureUnit } from '../../_model/measureUnit';
import { MeasureUnitService } from '../../_services/measure-unit.service';

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

    listGroupProduct!: GroupProduct[];
    selectedGroupProduct!: GroupProduct | null;
    
    listCategoryProduct!: CategoryProduct[];
    selectedCategoryProduct!: CategoryProduct | null;
    
    listTypeProduct!: TypeProduct[];
    selectedTypeProduct!: TypeProduct | null;
    
    listBrandProduct!: BrandProduct[];
    selectedBrandProduct!: BrandProduct | null;

    listMeasureUnit!: MeasureUnit[];
    selectedMeasureUnit!: MeasureUnit | null;

    @ViewChild('dt') dt: Table | undefined;
    
    constructor(
        private productService: ProductService,
        private groupProductService: GroupProductService,
        private categoryProductService: CategoryProductService,
        private typeProductService: TypeProductService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private brandProductService: BrandProductService,
        private measureUnitService: MeasureUnitService
        ){

    }

    ngOnInit() {
        this.productService.listar().subscribe((data) => (this.products = data));

        this.statuses = [
            { label: 'INSTOCK', value: 'instock' },
            { label: 'LOWSTOCK', value: 'lowstock' },
            { label: 'OUTOFSTOCK', value: 'outofstock' }
        ];

        this.groupProductService.listar().subscribe((data) => (this.listGroupProduct = data));

        this.brandProductService.listarBrandsEnabled().subscribe((data) => (this.listBrandProduct = data));

        this.measureUnitService.listar().subscribe((data) => (this.listMeasureUnit = data));
    }

    listCategories(){
        this.selectedCategoryProduct = null;
        this.selectedTypeProduct = null;
        this.listTypeProduct = [];
        if (this.selectedGroupProduct == null) {
            this.listCategoryProduct = [];
        } else{
            this.categoryProductService.listarByGroupProduct(this.selectedGroupProduct).subscribe((data)=>{
                this.listCategoryProduct = data;
            })
        }
    }

    listTypes(){
        this.selectedTypeProduct = null;
        if (this.selectedCategoryProduct == null) {
            this.listTypeProduct = [];
        } else{
            this.typeProductService.listarByCategoryProduct(this.selectedCategoryProduct).subscribe((data)=>{
                this.listTypeProduct = data;
            })
        }
    }

    showSelected(){
        if (this.selectedCategoryProduct != null && this.selectedGroupProduct != null && this.selectedTypeProduct != null) {
            console.log(this.selectedGroupProduct.name);
            console.log(this.selectedCategoryProduct.name);
            console.log(this.selectedTypeProduct.name);            
        }
    }

    showBrandSelected(){
        if (this.selectedBrandProduct != null) {
            console.log(this.selectedBrandProduct);
        }
    }

    showMeasureUnitSelected(){
        if (this.selectedMeasureUnit != null) {
            console.log(this.selectedMeasureUnit);
        }
    }

    openNew() {
        this.product = new Product();
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

        if (this.selectedBrandProduct != null) {this.product.brandProduct = this.selectedBrandProduct;}
        if (this.selectedCategoryProduct != null) {this.product.categoryProduct = this.selectedCategoryProduct;}
        if (this.selectedGroupProduct != null) {this.product.groupProduct = this.selectedGroupProduct;}
        if (this.selectedMeasureUnit != null) {this.product.measureUnit = this.selectedMeasureUnit;}
        if (this.selectedTypeProduct != null) {this.product.typeProduct = this.selectedTypeProduct;}
        this.product.isEnabled = true;

        this.submitted = true;

        if (this.product.name?.trim() && this.product.code?.trim()
                && this.selectedBrandProduct && this.selectedCategoryProduct
                && this.selectedGroupProduct && this.selectedMeasureUnit
                && this.selectedTypeProduct) {
            if (this.product.id) {
                console.log("esta seccion sirve para actualizar");
                // this.products[this.findIndexById(this.product.id)] = this.product;
                // this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Updated', life: 3000 });
            } else {
                this.productService.registrar(this.product).subscribe((data) => {
                    console.log(data);
                    this.messageService.add({ severity: 'success', summary: 'Exitoso', detail: 'Producto Creado', life: 3000 });
                    
                    this.productDialog = false;
                    this.productService.listar().subscribe((data) => (this.products = data));
                })
            }
        }
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

    applyFilterGlobal($event: any, stringVal: any) {
        this.dt!.filterGlobal(($event.target as HTMLInputElement).value, stringVal);
    }
}
