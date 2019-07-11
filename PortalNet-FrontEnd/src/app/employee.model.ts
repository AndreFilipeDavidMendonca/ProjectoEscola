export class Employee {
    public employeeId: number;
    public name: string;
    public email: string;
    public role: string; 
    public password: string;
    
    constructor(employeeId: number, name: string, email: string, role: string, password: string) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }
}

