import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BankAccountService } from '../services/bank-account.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrl: './transaction.component.scss'
})
export class TransactionComponent implements OnInit {
    accountId:any
    TransactionForm:FormGroup;

    constructor(  
    @Inject(MAT_DIALOG_DATA) public data:any,
    public dialogRef: MatDialogRef<TransactionComponent>,
    private bankAccountService:BankAccountService,
    private fb:FormBuilder
    ){
      this.accountId = this.data.accountId
      this.createTransactionForm();
    }

    ngOnInit(): void {
      
    }

    submit() {
      this.bankAccountService.sendTransaction(this.TransactionForm.value).subscribe(
        (data: any) => {    

          const responseData = JSON.parse(data.body);

          if (responseData.message=== "Insufficent Funds") {
            Swal.fire({
              title: "Insufficent Funds!",
              text: "Insufficent Funds!",
              icon: "warning"
            });
          } if (responseData.message === "Transaction completed") {
            Swal.fire({
              title: "Transaction Successful!",
              text: "Transaction Successful You have received an email!",
              icon: "success"
            });
            this.dialogRef.close();
          } 
        }
      );
    }
    

    createTransactionForm(){
      this.TransactionForm = this.fb.group({
        date: [new Date().toISOString(),Validators.required],
        amount: ['',Validators.required],
        senderId: ['',Validators.required],
        receiverId:[this.accountId,Validators.required],
        transactionType: ['Deposit',Validators.required]
      })
    }
}
