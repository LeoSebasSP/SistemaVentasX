import { GroupProduct } from "./groupProduct";

export class CategoryProduct{
    id!: bigint;
    name!: string;
    description!: string;
    groupProduct!: GroupProduct;
    isEnabled!: boolean;
    creationDate!: string;
    updateDate!: string;
    userCreatorId!: bigint;
    userUpdaterId!: bigint;
}