import { MeasureUnitComponent } from "../pages/measure-unit/measure-unit.component";
import { BrandProduct } from "./brandProduct";
import { CategoryProduct } from "./categoryProduct";
import { GroupProduct } from "./groupProduct";
import { TypeProduct } from "./typeProduct";

export class Product{
  id!: bigint;  
  name!: string;
  code!: string;
  codeAux!: string;
  description!: string;
  groupProduct!: GroupProduct;
  categoryProduct!: CategoryProduct;
  typeProduct!: TypeProduct;
  brandProduct!: BrandProduct;
  measureUnit!: MeasureUnitComponent;
  parentProduct!: bigint;
  minimumStock!: number;
  sellingPriceSoles!: number;
  previousSellingPriceSoles!: number;
  sellingPriceDollars!: number;
  previousSellingPriceDollars!: number;
  sunatType!: string;
  isEnabled!: boolean;
  creationDate!: string;
  updateDate!: string;
  userCreatorId!: bigint;
  userUpdaterId!: bigint;
}
