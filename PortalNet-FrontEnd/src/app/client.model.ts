export class Client {
    public fullName: string;
    public clientNumber: string;
    public adress: string;
    public postCode: string;
    public nif: string;
    public birthDate: string;
    public email: string;
    public phoneNumber: string;
    public gender: string;
    public contractDate: string;
    public endContract: string;
    public numberOfServices: string;
    public monthlyPayment: string;
    public serviceName: string;
    public status: string;
    public fraudulent: string;

    
    constructor(fullName: string, clientNumber: string, adress: string, postCode: string, nif: string, birthDate: string, email: string, phoneNumber: string, 
                gender: string, contractDate: string, endContract: string, numberOfServices: string, monthlyPayment: string, 
                serviceName: string, status: string, fraudulent: string){

        this.fullName = fullName;
        this.clientNumber = clientNumber;
        this.adress = adress;
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

                   