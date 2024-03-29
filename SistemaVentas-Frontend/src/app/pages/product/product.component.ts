import { Component, ViewChild } from '@angular/core';
import { ConfirmationService, FilterMatchMode, FilterService, MessageService, SelectItem } from 'primeng/api';
import { Product } from '../../_model/product';
import { ProductService } from '../../_services/product.service';
import { PrimengModule } from '../../_primeng/primeng.module';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Table, TablePageEvent } from 'primeng/table'
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
import { FileSelectEvent, FileUploadErrorEvent, FileUploadEvent } from 'primeng/fileupload';
import { DataImportService } from '../../_services/data-import.service';
import { DataExportService } from '../../_services/data-export.service';
import { PaginatorState } from 'primeng/paginator';

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
    importExcelDialog: boolean = false;
    products!: Product[];
    productsDisable!: Product[];
    product!: Product;
    productDisable!: Product;
    submittedProduct: boolean = false;

    productSelected!: Product;
    productDisabledSelected!: Product;

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

    firstElementIndexPageEnabled: number = 0;
    sizePageEnabled: number = 10;
    numberPageEnabled: number = 0;
    totalElementsPaginatorEnabled!: number;

    sizePageDisabled: number = 10;
    numberPageDisabled: number = 0;
    totalElementsPaginatorDisabled!: number;

    showOptDisabled: boolean = true;
    showOptEnabled: boolean = true;

    listCategoriesFilter!: CategoryProduct[];

    @ViewChild('dt') dt: Table | undefined;

    constructor(
        private productService: ProductService,
        private groupProductService: GroupProductService,
        private categoryProductService: CategoryProductService,
        private typeProductService: TypeProductService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private brandProductService: BrandProductService,
        private measureUnitService: MeasureUnitService,
        private dataImportService: DataImportService,
        private dataExportService: DataExportService,
        private filterService: FilterService
        ){
    }

    ngOnInit() {
      this.productService.findAllEnabledTrueOrderDesc().subscribe(data => {this.products = data; this.showOptDisabled = this.products.length<=3000 ? true : false;});
      this.productService.findAllEnabledFalseOrderDesc().subscribe(data => {this.productsDisable = data; this.showOptEnabled = this.productsDisable.length<=3000 ? true : false;});

      this.groupProductService.findAllEnabledTrueOrderDesc().subscribe((data) => (this.listGroupProduct = data));
      this.brandProductService.findAllEnabledTrueOrderDesc().subscribe((data) => (this.listBrandProduct = data));
      this.measureUnitService.findAllEnabledTrueOrderDesc().subscribe((data) => (this.listMeasureUnit = data));

      this.categoryProductService.findAllEnabledTrueOrderDesc().subscribe(data => (this.listCategoriesFilter = data));
    }

    // initListProductsEnabled(page:number, size:number){
    //     this.productService.findAllEnabledTrueOrderDescPagination(page, size).subscribe((data) => {
    //         this.products = data.content;
    //         this.totalElementsPaginatorEnabled = data.totalElements;
    //         this.sizePageEnabled = data.size;
    //         console.log(data);
    //     });
    // }

    // initListProductsDisabled(page:number, size:number){
    //     this.productService.findAllEnabledFalseOrderDescPagination(page, size).subscribe((data) => {
    //         this.productsDisable = data.content;
    //         this.totalElementsPaginatorDisabled = data.totalElements;
    //         this.sizePageDisabled = data.size;
    //     });
    // }

    // onPageChangeEnabled(event: any) {
    //     this.numberPageEnabled = event.page;
    //     this.sizePageEnabled = event.rows;
    //     this.initListProductsEnabled(this.numberPageEnabled, this.sizePageEnabled);
    // }

    // onPageChangeDisabled(event: any) {
    //     this.numberPageDisabled = event.page;
    //     this.sizePageDisabled = event.rows;
    //     this.initListProductsDisabled(this.numberPageDisabled, this.sizePageDisabled);
    // }

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

    openNew() {
        this.product = new Product();
        this.selectedGroupProduct= null;
        this.selectedMeasureUnit = null;
        this.selectedCategoryProduct = null;
        this.selectedTypeProduct = null;
        this.selectedBrandProduct = null;

        this.submittedProduct = false;
        this.productDialog = true;
    }

    disableProduct(product: Product | null) {
        if (product != null) {
            this.confirmationService.confirm({
                message: '¿Estás seguro de deshabilitar el producto: ' + product.name + '?',
                header: 'Confirm',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.productService.disableProducts(product.id).subscribe((data)=>{
                        this.messageService.add({ severity: 'success', summary: 'Exitoso', detail: 'Producto Deshabilitado', life: 3000 });
                        this.productService.findAllEnabledTrueOrderDesc().subscribe(data => {this.products = data; this.showOptDisabled = this.products.length<=3000 ? true : false;});
                        this.productService.findAllEnabledFalseOrderDesc().subscribe(data => {this.productsDisable = data; this.showOptEnabled = this.productsDisable.length<=3000 ? true : false;});
                    });
                }
            });
        }
    }

    enableProduct(product: Product | null) {
        if (product != null) {
            this.confirmationService.confirm({
                message: '¿Estás seguro de habilitar el producto: ' + product.name + '?',
                header: 'Confirm',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.productService.enableProducts(product.id).subscribe((data)=>{
                        this.messageService.add({ severity: 'success', summary: 'Exitoso', detail: 'Producto Habilitado', life: 3000 });
                        this.productService.findAllEnabledTrueOrderDesc().subscribe(data => {this.products = data; this.showOptDisabled = this.products.length<=3000 ? true : false;});
                        this.productService.findAllEnabledFalseOrderDesc().subscribe(data => {this.productsDisable = data; this.showOptEnabled = this.productsDisable.length<=3000 ? true : false;});
                    });
                }
            });
        }
    }

    editProduct(product: Product) {
        this.product = { ...product };
        this.selectedGroupProduct = product.groupProduct;
        this.selectedMeasureUnit = product.measureUnit;
        this.categoryProductService.listarByGroupProduct(this.selectedGroupProduct).subscribe((data)=>{
            this.listCategoryProduct = data;
            this.selectedCategoryProduct = product.categoryProduct;
            this.typeProductService.listarByCategoryProduct(this.selectedCategoryProduct).subscribe((data)=>{
                this.listTypeProduct = data;
                this.selectedTypeProduct = product.typeProduct;
                this.selectedBrandProduct = product.brandProduct;
                this.productDialog = true;
            })
        })
    }


    hideDialog() {
        if (this.importExcelDialog) {
            this.importExcelDialog = false;
        } else if (this.productDialog) {
            this.productDialog = false;
            this.submittedProduct = false;
        }
    }

    saveProduct() {
        if (this.selectedBrandProduct != null) {this.product.brandProduct = this.selectedBrandProduct;}
        if (this.selectedCategoryProduct != null) {this.product.categoryProduct = this.selectedCategoryProduct;}
        if (this.selectedGroupProduct != null) {this.product.groupProduct = this.selectedGroupProduct;}
        if (this.selectedMeasureUnit != null) {this.product.measureUnit = this.selectedMeasureUnit;}
        if (this.selectedTypeProduct != null) {this.product.typeProduct = this.selectedTypeProduct;}
        this.product.isEnabled = true;

        this.submittedProduct = true;

        if (this.product.name?.trim() && this.product.code?.trim()
                && this.selectedBrandProduct && this.selectedCategoryProduct
                && this.selectedGroupProduct && this.selectedMeasureUnit
                && this.selectedTypeProduct) {
            if (this.product.id) {
                this.productService.modificar(this.product).subscribe((data)=>{
                    this.messageService.add({ severity: 'success', summary: 'Exitoso', detail: 'Producto Actualizado', life: 3000 });
                    this.productDialog = false;
                    this.productService.findAllEnabledTrueOrderDesc().subscribe(data => {this.products = data; this.showOptDisabled = this.products.length<=3000 ? true : false;});
                })
            } else {
                this.productService.registrar(this.product).subscribe((data) => {
                    this.messageService.add({ severity: 'success', summary: 'Exitoso', detail: 'Producto Creado', life: 3000 });
                    this.productDialog = false;
                    this.productService.findAllEnabledTrueOrderDesc().subscribe(data => {this.products = data; this.showOptDisabled = this.products.length<=3000 ? true : false;});
                })
            }
        }
    }

    applyFilterGlobal($event: any, stringVal: any) {
        this.dt!.filterGlobal(($event.target as HTMLInputElement).value, stringVal);
    }

    onUpload(event:FileUploadEvent) {
        this.dataImportService.importProducts(event.files[0]).subscribe((data)=>{
            this.messageService.add({severity: 'success', summary: 'Exitoso', detail: 'Productos Importados'});
            this.importExcelDialog = false;
            this.productService.findAllEnabledTrueOrderDesc().subscribe(data => {this.products = data; this.showOptDisabled = this.products.length<=3000 ? true : false;});
        })
    }

    openDialogImportExcel() {
        this.importExcelDialog = true;
    }

    exportProductsEnabled() {
        this.dataExportService.exportProductsEnabledToExcel().subscribe((data) => {
            this.messageService.add({severity: 'success', summary: 'Exitoso', detail: 'Productos Activos Exportados'});
        }, error => {
            console.log(error);
        });
    }

    exportProductsDisabled(){
        this.dataExportService.exportProductsDisabledToExcel().subscribe((data) => {
            this.messageService.add({severity: 'success', summary: 'Exitoso', detail: 'Productos Inactivos Exportados'});
        }, error => {
            console.log(error);
        });
    }

    onRowSelect(event: any) {
      this.messageService.add({ severity: 'info', summary: 'Product Selected', detail: event.data.name });
    }

    onRowUnselect(event: any) {
        this.messageService.add({ severity: 'info', summary: 'Product Unselected', detail: event.data.name });
    }

    filterByCode(event: any){
      this.dt?.filter(event.target.value, 'code','contains');
    }

    filterByName(event: any){
      this.dt?.filter(event.target.value, 'name','contains');
    }
}
