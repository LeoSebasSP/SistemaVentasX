import { Menu } from "./menu";

export class Submenu{
  id: number;
  icon: string;
  name: string;
  url: string;
  menu: Menu;

  constructor(){
    this.id = 0;
    this.icon = '';
    this.name = '';
    this.url = '';
    this.menu = new Menu();
  }
}
