export class registerClient {
    public fullName: string;
    public adress: string;
    public postCode: string;
    public nif: string;
    public birthDate: string;
    public email: string;
    public phoneNumber: string;
    public gender: string;
    public service: string;

    
    constructor(fullName: string, adress: string, postCode: string, nif: string, birthDate: string, email: string, phoneNumber: string, 
                gender: string, service: string){

        this.fullName = fullName;
        this.adress = adress;
        this.postCode = postCode;
        this.nif = nif;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.service = service;        
    }
}