import { Component, ElementRef, HostListener, ViewChild } from '@angular/core';
import { Menu } from '../../_model/menu';
import { Submenu } from '../../_model/submenu';
import { LoginService } from '../../_services/login.service';
import { UserService } from '../../_services/user.service';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';
import { PrimengModule } from '../../_primeng/primeng.module';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports:[
    CommonModule,
    RouterLink,
    RouterOutlet,
    PrimengModule
  ],
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent {

  hiddenProfileLeft: boolean = true;
  hiddenProfileRight: boolean = true;
  hiddenMenus: boolean = true;
  hiddenSubmenus: boolean = true;
  menus!: Menu[];
  submenus!: Submenu[];
  expandedMenuId: number | null = null;
  menuIdSeleccionado: number | null = null;

  constructor(
    private loginService: LoginService,
    private userService: UserService,
    private el: ElementRef
    ){
  }

  @ViewChild('menubar') menuBar!: ElementRef;
  @ViewChild('toogleMenuButton') toggleMenuButton!: ElementRef;
  @ViewChild('menuli') menuli!: ElementRef;


  ngOnInit(): void{
    this.userService.listMenuByUsername().subscribe((data)=>{
      this.menus = data;
    })

    this.userService.listSubmenuByUsername().subscribe((data)=>{
      this.submenus = data;
    })
  }

  @HostListener('document:click', ['$event'])
  handleClick(event: MouseEvent) {
    if (
      !this.menuBar.nativeElement.contains(event.target) &&
      !this.toggleMenuButton.nativeElement.contains(event.target)
    ) {
      this.hiddenMenus = true;
    }

    if (
      !this.menuli.nativeElement. contains(event.target)
    ) {
      if (this.expandedMenuId===this.menuIdSeleccionado && this.expandedMenuId !== null) {
        this.hiddenSubmenus = !this.hiddenSubmenus;
        this.expandedMenuId = null;
      }
    }
  }

  logout(){
    this.loginService.logout();
  }

  toggleHiddenProfileLeft() {
    this.hiddenProfileLeft = !this.hiddenProfileLeft;
  }

  toggleHiddenMenus() {
    this.hiddenMenus = !this.hiddenMenus;
  }

  toggleHiddenSubmenus(menuId: number) {
    this.hiddenSubmenus = !this.hiddenSubmenus;
    this.menuIdSeleccionado = menuId;

    if (this.expandedMenuId!==menuId && this.expandedMenuId !== null) {
      this.hiddenSubmenus = !this.hiddenSubmenus;
    }
    this.expandedMenuId = this.expandedMenuId === menuId ? null : menuId;
  }

  toggleHiddenProfileRight() {
    this.hiddenProfileRight = !this.hiddenProfileRight;
  }
}
