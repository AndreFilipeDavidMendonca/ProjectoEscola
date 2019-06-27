export class Client {
    public fullName: string;
    public clientNumber: number;
    public adress: string;
    public city: string;
    public postCode: string;
    public nif: string;
    public birthDate: string;
    public email: string;
    public phoneNumber: number;
    public gender: string;
    public contractDate: string;
    public endContract: string;
    public numberOfServices: number;
    public monthlyPayment: string;
    public serviceName: string;
    public status: boolean;
    public fraudulent: boolean;

    
    constructor(fullName: string, clientNumber: number, adress: string, city: string, postCode: string, nif: string, birthDate: string, email: string, phoneNumber: number, 
                gender: string, contractDate: string, endContract: string, numberOfServices: number, monthlyPayment: string, 
                serviceName: string, status: boolean, fraudulent: boolean){

        this.fullName = fullName;
        this.clientNumber = clientNumber;
        this.adress = adress;
        this.city = city;
        this.postCode = postCode;
        this.nif = nif;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.contractDate = contractDate;
        this.endContract = endContract;
        this.numberOfServices = numberOfServices;
        this.monthlyPayment = monthlyPayment;
        this.serviceName = serviceName;
        this.status = status;
        this.fraudulent = fraudulent;
        
    }
}

                   