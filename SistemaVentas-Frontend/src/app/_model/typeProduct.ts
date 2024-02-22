import { CategoryProduct } from "./categoryProduct";

export class TypeProduct{
    id!: bigint;
    name!: string;
    description!: string;
    categoryProduct!: CategoryProduct;
    isEnabled!: boolean;
    creationDate!: string;
    updateDate!: string;
    userCreatorId!: bigint;
    userUpdaterId!: bigint;
}