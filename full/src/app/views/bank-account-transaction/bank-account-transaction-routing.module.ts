import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBankAccountComponent } from './add-bank-account/add-bank-account.component';
import { GetAllAccountsComponent } from './get-all-accounts/get-all-accounts.component';
import { ConvertercurrencyComponent } from './convertercurrency/convertercurrency.component';

const routes: Routes = [
  {path:'addBankAccount',component:AddBankAccountComponent},
  {path:'getAllAccounts',component:GetAllAccountsComponent},
  {path:'Convertercurrency',component:ConvertercurrencyComponent}


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BankAccountTransactionRoutingModule { }
