export class Service {
    public id: number;
    public name: string;
    public internet: string;
    public tv: string;
    public mobilePhone: string;
    public phone: string;
    public price: string;
    public loyalty: string;
    public creationDate: string;
    public status: boolean;
    
    constructor (id: number, name: string, internet: string, tv: string, mobilePhone: string, phone: string, price: string, loyalty: string, creationDate: string, status: boolean)  {

        this.id = id;
        this.name = name;
        this.internet = internet;
        this.tv = tv;
        this.mobilePhone = mobilePhone;
        this.phone = phone;
        this.price = price;
        this.loyalty = loyalty;
        this.creationDate = creationDate;
        this.status = status;

    }

}
