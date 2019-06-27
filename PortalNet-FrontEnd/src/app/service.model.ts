export class Service {
    public id: number;
    public name: string;
    public internet: string;
    public tv: string;
    public mobilePhone: string;
    public price: number;
    public creationDate: string;
    public status: boolean;
    
    constructor (id: number, name: string, internet: string, tv: string, mobilePhone: string, price: number, creationDate: string, status: boolean)  {

        this.id = id;
        this.name = name;
        this.internet = internet;
        this.tv = tv;
        this.mobilePhone = mobilePhone;
        this.price = price;
        this.creationDate = creationDate;
        this.status = status;

    }

}
