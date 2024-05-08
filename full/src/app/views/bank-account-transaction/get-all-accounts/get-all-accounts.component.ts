import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { TablesService } from 'app/views/tables/tables.service';
import { BankAccountService } from '../services/bank-account.service';
import { BankAccount } from '../models/BankAccount';
import { MatDialog } from '@angular/material/dialog';
import { ModifyAccountComponent } from '../modify-account/modify-account.component';
import { Observable, map } from 'rxjs';
import { AppConfirmService } from 'app/shared/services/app-confirm/app-confirm.service';
import Swal from 'sweetalert2';
import { TransactionComponent } from '../transaction/transaction.component';
import { egretAnimations } from 'app/shared/animations/egret-animations';
import { ThemeService } from 'app/shared/services/theme.service';
import tinyColor from 'tinycolor2';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-get-all-accounts',
  templateUrl: './get-all-accounts.component.html',
  styleUrls: ['./get-all-accounts.component.scss'],
  animations: egretAnimations
})
export class GetAllAccountsComponent {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  
  displayedColumns: string[] = ['accountNumber','accountType','createDate','balance','currency','modifyDate','rib','actions'];
  BankAccountList:BankAccount[]=[];

  dataSource: any;
  bankAccounts:BankAccount[]
  trafficVsSaleOptions: any;
  trafficVsSale: any;
  trafficData: any;
  saleData: any;

  sessionOptions: any;
  sessions: any;
  sessionsData: any;

  trafficGrowthChart: any;
  bounceRateGrowthChart: any;

  dailyTrafficChartBar: any;
  trafficSourcesChart: any;
  countryTrafficStats: any[];

  constructor(
    private bankAccountService:BankAccountService,
    public dialog:MatDialog,
    private confirmService:AppConfirmService,
    private themeService:ThemeService,
    private http: HttpClient
    ) { }

   ngOnInit() {
    
  
    this.themeService.onThemeChange.subscribe(activeTheme => {
      this.initTrafficSourcesChart(activeTheme)

    });
    this.initTrafficSourcesChart(this.themeService.activatedTheme)



    this.bankAccountService.getAllBankAccounts().subscribe(
      (data: BankAccount[]) => {
        console.log(data)
        this.bankAccounts = data;
        this.dataSource = new MatTableDataSource<BankAccount>(this.bankAccounts);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      (error) => {
        console.log(error);
      }
    );

  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  sortData(sort: Sort) {
    const data = this.dataSource.slice();
    if (!sort.active || sort.direction === '') {
      this.dataSource = data;
      return;
    }

    this.dataSource = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'accountNumber':
          return compare(a.accountNumber, b.accountNumber, isAsc);
        case 'rib':
          return compare(a.rib, b.rib, isAsc);
        case 'accountType':
          return compare(a.accountType, b.accountType, isAsc);
        case 'createDate':
          return compare(a.createDate, b.createDate, isAsc);
        case 'modifyDate':
          return compare(a.modifyDate, b.modifyDate, isAsc);
        case 'currency':
          return compare(a.currency, b.currency, isAsc);
        case 'balance':
          return compare(a.balance, b.balance, isAsc);

        default:
          return 0;
      }
    });
  }

  EditAccount(accountId: number) {
    let dialogRef = this.dialog.open(ModifyAccountComponent, {
      maxWidth: '30vw',
      maxHeight: '100%',
      data: {
        accountId: accountId
      }
    });
  
    dialogRef.afterClosed().subscribe(() => {
      this.bankAccountService.getAllBankAccounts().subscribe(
        (data: BankAccount[]) => {
          this.bankAccounts = data;
          this.dataSource = new MatTableDataSource<BankAccount>(this.bankAccounts);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        },
        (error) => {
          console.log(error);
        }
      );  
    });
  }

  deleteAccountById(accountId:number){
      this.openDialog("Confirmation", "Are you sure you want to delete this Account?").subscribe(result => {
        if(result === 'yes' ){
          this.bankAccountService.deleteAccountById(accountId).subscribe(
            (data)=>{
              Swal.fire({
                title: "Success!",
                text: "Bank Account Deleted Successfully!",
                icon: "success"
              });
              this.bankAccountService.getAllBankAccounts().subscribe(
                (data: BankAccount[]) => {
                  this.bankAccounts = data;
                  this.dataSource = new MatTableDataSource<BankAccount>(this.bankAccounts);
                  this.dataSource.paginator = this.paginator;
                  this.dataSource.sort = this.sort;
                },
                (error) => {
                  console.log(error);
                }
              );  
        
            }
          )
        }
      })
  }


  SendTransaction(accountId: number) {
    let dialogRef = this.dialog.open(TransactionComponent, {
      maxWidth: '30vw',
      maxHeight: '100%',
      data: {
        accountId: accountId
      }
    });
  
    dialogRef.afterClosed().subscribe(() => {
      this.bankAccountService.getAllBankAccounts().subscribe(
        (data: BankAccount[]) => {
          this.bankAccounts = data;
          this.dataSource = new MatTableDataSource<BankAccount>(this.bankAccounts);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        },
        (error) => {
          console.log(error);
        }
      );  
    });
  }



  openDialog(title: string, message: string): Observable<string> {
    return this.confirmService.confirm({title: title, message: message}).pipe(
      map(result => result ? 'yes' : 'no')
    );
  }

  initTrafficSourcesChart(theme) {
    this.bankAccountService.getAllBankAccounts().subscribe(
      (data: BankAccount[]) => {
        this.BankAccountList = data;
              const statusColors = {
        'DEMAND_DEPOSIT_ACCOUNT': '#003f5c', 
        'SAVINGS_ACCOUNT': '#7a0000' 
           };

        this.trafficSourcesChart = {
          grid: {
            left: "3%",
            right: "4%",
            bottom: "3%",
            containLabel: true
          },
          color: Object.values(statusColors),
          tooltip: {
            show: false,
            trigger: "item",
            formatter: "{a} <br/>{b}: {c} ({d}%)"
          },    
          legend: {
            top: '10%',
            right: '3%',
            orient: 'vertical',
            align: 'right',
            height: '140px'
          },        
          xAxis: [
            {
              axisLine: {
                show: false
              },
              splitLine: {
                show: false
              }
            }
          ],
          yAxis: [
            {
              axisLine: {
                show: false
              },
              splitLine: {
                show: false
              }
            }
          ],
          series: [
            {
              name: "Sessions",
              type: "pie",
              radius: ["55%", "85%"],
              center: ["50%", "50%"],
              avoidLabelOverlap: false,
              hoverOffset: 5,
              stillShowZeroSum: false,
              label: {
                normal: {
                  show: false,
                  position: "center",
                  textStyle: {
                    fontSize: "13",
                    fontWeight: "normal"
                  },
                  formatter: "{a}"
                },
                emphasis: {
                  show: true,
                  textStyle: {
                    fontSize: "15",
                    fontWeight: "normal",
                    color: "rgba(15, 21, 77, 1)"
                  },
                  formatter: "{b} \n{c} ({d}%)"
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: this.getProjectStatusData().map(statusData => ({
                ...statusData,
                itemStyle: {
                  color: statusColors[statusData.name] 
                }
              }))

              
            }
          ]
           };
      })

  }



  
  getProjectStatusData(): { value: number; name: string }[] {
    // Mapping object for status keys and display names
    const statusDisplayNames = {
      'DEMAND_DEPOSIT_ACCOUNT': 'Deposit',
      'SAVINGS_ACCOUNT': 'Savings'
        };
    // Initialize status count object with display names as keys
    const statusCount = {
      'Deposit': 0,
      'Savings': 0,
    };
  
    // Count project statuses using display names as keys
    this.BankAccountList.forEach(bankaccount => {
      const displayName = statusDisplayNames[bankaccount.accountType];
      if (displayName) {
        statusCount[displayName]++;
      }
    });
  
    // Prepare data for the chart
    const chartData = [];
  
    // Iterate over the statusCount object to create the desired data structure
    Object.keys(statusCount).forEach(status => {
      chartData.push({ value: statusCount[status], name: status });
    });
  
    return chartData;
  }

  fetchBankAccounts(){

  }
  
}
function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}




