import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BankAccount } from '../models/BankAccount';
import { Transaction } from '../models/Transaction';

@Injectable({
  providedIn: 'root'
})
export class BankAccountService {

  private BASE_URL = 'http://localhost:8089'

  constructor(private http:HttpClient) { }

  addBankAccount(bankAccount:BankAccount){
    return this.http.post(this.BASE_URL+'/api/bank-accounts',bankAccount);
  }

  getAllBankAccounts(){
    return this.http.get<BankAccount[]>(this.BASE_URL+'/api/bank-accounts');
  }

  getAccountById(accountId:number){
    return this.http.get<BankAccount>(this.BASE_URL+'/api/bank-accounts/'+accountId);
  }
  EditAccountById(accountId:number,bankAccount:BankAccount){
    return this.http.put<BankAccount>(this.BASE_URL+'/api/bank-accounts/'+accountId,bankAccount);
  }
  deleteAccountById(accountId:number){
    return this.http.delete<BankAccount>(this.BASE_URL+'/api/bank-accounts/'+accountId);
  }

  sendTransaction(transaction:Transaction){
    return this.http.post<any>(this.BASE_URL+'/api/transaction',transaction);
  }

}
