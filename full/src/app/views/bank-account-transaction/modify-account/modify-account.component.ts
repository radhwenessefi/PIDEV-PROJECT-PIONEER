import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BankAccount } from '../models/BankAccount';
import { BankAccountService } from '../services/bank-account.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-modify-account',
  templateUrl: './modify-account.component.html',
  styleUrl: './modify-account.component.scss'
})
export class ModifyAccountComponent implements OnInit{
  
  accountId:number;
  bankAccount:BankAccount;
  ModifyAccountForm: FormGroup;
  accountTypes = ['DEMAND_DEPOSIT_ACCOUNT','SAVINGS_ACCOUNT']

  constructor(             
  @Inject(MAT_DIALOG_DATA) public data:any,
    public dialogRef: MatDialogRef<ModifyAccountComponent>,
    private bankAccountService:BankAccountService,
    private fb:FormBuilder,
    private router:Router
  ){
    this.accountId = this.data.accountId
    this.getAccountById(this.accountId);

    }

  ngOnInit(): void {
  }


  getAccountById(accountId: number) {    
    this.bankAccountService.getAccountById(accountId).subscribe(
      (data)=>{
        this.bankAccount = data;
        this.createBankAccountForm();
      },(error)=>{
        console.log(error)
      }
    )
  }

  createBankAccountForm(){
    this.ModifyAccountForm = this.fb.group({
      accountNumber: [this.bankAccount?.accountNumber,Validators.required],
      balance: [this.bankAccount?.balance,Validators.required],
      rib: [this.bankAccount?.rib,Validators.required],
      currency: [this.bankAccount?.currency,Validators.required],
      createDate:[this.bankAccount?.createDate],
      accountType: [this.bankAccount?.accountType,Validators.required]
    })
  }
  
  submit(){

    // const modifyDate = moment(this.ModifyAccountForm.value.modifyDate).format('YYYY-MM-DD');
    // this.ModifyAccountForm.controls['modifyDate'].setValue(modifyDate);

    console.log(this.ModifyAccountForm.value)
      this.bankAccountService.EditAccountById(this.accountId,this.ModifyAccountForm.value).subscribe(
      data=>{
        Swal.fire({
          title: "Success!",
          text: "Bank Account Updated Successfully!",
          icon: "success"
        });
        this.dialogRef.close();
      }
    )
  }
}
