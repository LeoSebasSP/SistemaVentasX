export class Product{
  id: bigint;
  name: string;
  price: number;
  category: string;
  quantity: number;
  inventoryStatus: string;
  description: string;
  image: string;

  constructor(){
    this.id = 0n;
    this.name = '';
    this.price = 0;
    this.category = '';
    this.quantity = 0;
    this.inventoryStatus = '';
    this.description = '';
    this.image = '';
  }
}
