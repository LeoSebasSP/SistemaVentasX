import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { TooltipModule } from 'primeng/tooltip';
import { CardModule } from 'primeng/card';
import { PasswordModule } from 'primeng/password';
import { StyleClassModule } from 'primeng/styleclass';
import { TableModule } from 'primeng/table';
import { ToolbarModule } from 'primeng/toolbar';
import { ToastModule } from 'primeng/toast';
import { FileUploadModule } from 'primeng/fileupload';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { TagModule } from 'primeng/tag';
import { DropdownModule } from 'primeng/dropdown';
import { DialogModule } from 'primeng/dialog';
import { RadioButtonModule } from 'primeng/radiobutton';
import { DividerModule } from 'primeng/divider';
import { CascadeSelectModule } from 'primeng/cascadeselect';
import { TabViewModule } from 'primeng/tabview';
import { ListboxModule } from 'primeng/listbox';
import { InputNumberModule } from 'primeng/inputnumber';
import { PaginatorModule } from 'primeng/paginator';
import { MultiSelectModule } from 'primeng/multiselect';

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  exports: [
    ButtonModule,
    InputGroupModule,
    InputGroupAddonModule,
    TooltipModule,
    CardModule,
    PasswordModule,
    StyleClassModule,
    TableModule,
    ToolbarModule,
    ToastModule,
    FileUploadModule,
    ConfirmDialogModule,
    TagModule,
    DropdownModule,
    DialogModule,
    RadioButtonModule,
    DividerModule,
    CascadeSelectModule,
    TabViewModule,
    ListboxModule,
    InputNumberModule,
    PaginatorModule,
    MultiSelectModule
  ]
})
export class PrimengModule { }
