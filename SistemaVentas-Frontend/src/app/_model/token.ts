import { User } from "./user";

export class Token{
  id!: bigint;
  token!: string;
  tokenType!: string;
  revoked!: boolean;
  expired!: boolean;
  user!: User;
  isEnabled!: boolean;
  creationDate!: string;
  updateDate!: string;
}
