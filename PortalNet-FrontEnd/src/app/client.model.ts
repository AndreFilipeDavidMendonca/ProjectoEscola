export class Client {
    
    public clientId?: number;
    public name: string;
    public clientNumber: number;
    public adress: string;
    public city: string;
    public postalCode: string;
    public nif: number;
    public birthDate: string;
    public email: string;
    public mobilePhone: number;
    public gender: string;
    public entryDate: string;
    public endContract: string;
    public numberOfServices: number;
    public monthlyPay: string;
    public serviceName: string;
    public status: boolean;
    public fraudulent: boolean;
    public password: string;
   

    
    constructor(clientId: number, name: string, clientNumber: number, adress: string, city: string, postalCode: string, nif: number, birthDate: string, email: string, mobilePhone: number, 
                gender: string, entryDate: string, endContract: string, numberOfServices: number, monthlyPay: string, 
                serviceName: string, status: boolean, fraudulent: boolean, password: string){

            

        this.clientId = clientId;    
        this.name = name;
        this.clientNumber = clientNumber;
        this.adress = adress;
        this.city = city;
        this.postalCode = postalCode;
        this.nif = nif;
        this.birthDate = birthDate;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.gender = gender;
        this.entryDate = entryDate;
        this.endContract = endContract;
        this.numberOfServices = numberOfServices;
        this.monthlyPay = monthlyPay;
        this.serviceName = serviceName;
        this.status = status;
        this.fraudulent = fraudulent;
        this.password = password;
    }
}

                   