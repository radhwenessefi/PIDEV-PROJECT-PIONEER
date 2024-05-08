export interface Transaction {
    id: number;
    date: Date;
    amount: number;
    senderId: number;
    receiverId: number;
    transactionType: TransactionType;
  }
  
  export enum TransactionType {
    Deposit,
    Withdrawal
}
  