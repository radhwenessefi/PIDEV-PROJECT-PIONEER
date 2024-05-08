export class BankAccount{
    public id:number;
    public accountNumber:number;
    public balance:number;
    public createDate:string;
    public modifyDate:string;
    public rib:string;
    public currency:string;
    public accountType:AccountType;
}

export enum AccountType{
    DEMAND_DEPOSIT_ACCOUNT,
    SAVINGS_ACCOUNT
}