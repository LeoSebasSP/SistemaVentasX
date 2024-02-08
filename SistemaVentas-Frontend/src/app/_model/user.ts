export class User{
  id: bigint;
  username: string;
  name: string;
  lastName: string;
  password: string;
  isEnabled: boolean;

  constructor(
  ) {
    this.id = 0n;
    this.username = '';
    this.name = '';
    this.lastName = '';
    this.password = '';
    this.isEnabled = false;
  }
}
