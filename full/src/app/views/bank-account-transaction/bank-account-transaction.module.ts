import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BankAccountTransactionRoutingModule } from './bank-account-transaction-routing.module';
import { AddBankAccountComponent } from './add-bank-account/add-bank-account.component';
import { HttpClientModule } from '@angular/common/http';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatRadioModule } from '@angular/material/radio';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MAT_MOMENT_DATE_FORMATS } from '@angular/material-moment-adapter';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { GetAllAccountsComponent } from './get-all-accounts/get-all-accounts.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatSortModule } from '@angular/material/sort';
import { RouterModule } from '@angular/router';
import { ModifyAccountComponent } from './modify-account/modify-account.component';
import { MatDialogModule } from '@angular/material/dialog';
import { TransactionComponent } from './transaction/transaction.component';
import { MatMenuModule } from '@angular/material/menu';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatChipsModule } from '@angular/material/chips';
import { MatListModule } from '@angular/material/list';
import { MatTabsModule } from '@angular/material/tabs';
import { MatGridListModule } from '@angular/material/grid-list';
import { NgChartsModule } from 'ng2-charts';
import { NgApexchartsModule } from 'ng-apexcharts';
import { NgxEchartsModule } from 'ngx-echarts';
import { SharedPipesModule } from 'app/shared/pipes/shared-pipes.module';
import { DashboardRoutes } from '../dashboard/dashboard.routing';
import * as echarts from 'echarts';
import { NgxCurrencyDirective } from "ngx-currency";
import { ConvertercurrencyComponent } from './convertercurrency/convertercurrency.component';

export const MY_DATE_FORMATS = {
  parse: {
    dateInput: 'YYYY-MM-DD',
  },
  display: {
    dateInput: 'YYYY-MM-DD',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY'
  },
};

@NgModule({
  declarations: [
    AddBankAccountComponent,
    GetAllAccountsComponent,
    ModifyAccountComponent,
    TransactionComponent,
    ConvertercurrencyComponent
  ],
  imports: [
    NgxCurrencyDirective,
    CommonModule,
    BankAccountTransactionRoutingModule,
    HttpClientModule,
    MatFormFieldModule,
    MatCardModule,
    MatDividerModule,
    MatRadioModule,
    MatDatepickerModule,
    MatCheckboxModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatPaginatorModule,
    MatTableModule,
    MatIconModule,
    MatSortModule,
    RouterModule,
    MatDialogModule,
    CommonModule,
    MatIconModule,
    MatCardModule,
    MatMenuModule,
    MatProgressBarModule,
    MatExpansionModule,
    MatButtonModule,
    MatChipsModule,
    MatListModule,
    MatTabsModule,
    MatTableModule,
    MatGridListModule,
    NgChartsModule,
    NgxEchartsModule.forRoot({
      echarts
    }),
    NgApexchartsModule,
    SharedPipesModule,
    RouterModule.forChild(DashboardRoutes)

  ],
  providers:[
    { provide: MAT_MOMENT_DATE_FORMATS, useValue: MY_DATE_FORMATS }
  ]
})
export class BankAccountTransactionModule { }
