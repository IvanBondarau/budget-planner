export class UserModel {
    id: number;
    username: string;
    email: string;
    password: string | null = null;

    constructor(id: number, username: string, email: string, password: string | null = null) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
