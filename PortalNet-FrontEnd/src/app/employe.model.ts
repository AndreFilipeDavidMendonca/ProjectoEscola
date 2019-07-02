export class Employe {
    public id: number;
    public name: string;
    public email: string;
    public role: string; 
    
    constructor(id: number, name: string, email: string, role: string) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}

