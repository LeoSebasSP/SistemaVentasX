import { BrandProduct } from "./brandProduct";
import { CategoryProduct } from "./categoryProduct";
import { GroupProduct } from "./groupProduct";
import { MeasureUnit } from "./measureUnit";
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
  measureUnit!: MeasureUnit;
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
