import { Component } from '@angular/core';
import { BankAccountService } from '../services/bank-account.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-bank-account',
  templateUrl: './add-bank-account.component.html',
  styleUrl: './add-bank-account.component.scss'
})
export class AddBankAccountComponent {

  AddBankAccountFormGroup:FormGroup;
  accountTypes = ['DEMAND_DEPOSIT_ACCOUNT','SAVINGS_ACCOUNT']
  constructor(
    private bankAccountService:BankAccountService,
    private fb:FormBuilder,private router:Router
    ){
      this.createBankAccountForm()
    }
    
    createBankAccountForm(){
      this.AddBankAccountFormGroup = this.fb.group({
        accountNumber: ['',Validators.required],
        balance: ['',Validators.required],
        modifyDate: ['',Validators.required],
        rib: ['',Validators.required],
        currency: ['',Validators.required],
        accountType: ['',Validators.required]
      })
    }

    submit(){
      // console.log(this.AddBankAccountFormGroup.value)
      const modifyDate = moment(this.AddBankAccountFormGroup.value.modifyDate).format('YYYY-MM-DD');

    this.AddBankAccountFormGroup.patchValue({
      accountNumber: this.AddBankAccountFormGroup.value.accountNumber,
      balance:this.AddBankAccountFormGroup.value.balance,
      modifyDate: modifyDate,
      rib: this.AddBankAccountFormGroup.value.rib,
      currency: this.AddBankAccountFormGroup.value.currency,
      accountType:this.AddBankAccountFormGroup.value.accountType
    });

    console.log()
    console.log(modifyDate)

      this.bankAccountService.addBankAccount(this.AddBankAccountFormGroup.value).subscribe(
        data=>{

          Swal.fire({
            title: "Success!",
            text: "Bank Account Created Successfully!",
            icon: "success"
          });

          this.router.navigate(['/bankaccount/getAllAccounts'])
          
        },
        (error)=>{
          Swal.fire({
            title: "Unexpected Error!",
            text: "Unexpected Error!",
            icon: "error"
          });
        }
      )
    }

}
