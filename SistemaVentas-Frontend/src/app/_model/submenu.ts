import { Menu } from "./menu";

export class Submenu{
  id!: number;
  icon!: string;
  name!: string;
  url!: string;
  menu!: Menu;
  isEnabled!: boolean;
  creationDate!: string;
  updateDate!: string;
  userCreatorId!: bigint;
  userUpdaterId!: bigint;
}
