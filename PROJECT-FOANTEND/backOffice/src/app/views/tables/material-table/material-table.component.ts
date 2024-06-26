import { Component, OnInit, ViewChild } from '@angular/core';
import { TablesService } from '../tables.service';
import { MatPaginator as MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource as MatTableDataSource } from '@angular/material/table';
import { egretAnimations } from 'app/shared/animations/egret-animations';
import { PortfolioService } from 'app/views/Portfolio-Service/portfolio.Service';
import { Inject } from '@angular/core';
import { MatDialogRef as MatDialogRef, MatDialog as MatDialog } from '@angular/material/dialog';
import { MatSnackBar as MatSnackBar } from '@angular/material/snack-bar';
import { AppConfirmService } from 'app/shared/services/app-confirm/app-confirm.service';
import { AppLoaderService } from 'app/shared/services/app-loader/app-loader.service';
import { Subscription } from 'rxjs';




@Component({
  selector: 'app-material-table',
  templateUrl: './material-table.component.html',
  styleUrls: ['./material-table.component.scss'],
  animations: egretAnimations
})
export class MaterialTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  public getItemSub: Subscription;
  displayedColumns: string[] = [];
  dataSource: any;
  data: any;

  constructor(private tableService: TablesService,
    private dialog: MatDialog,
    private snack: MatSnackBar,
   
    private confirmService: AppConfirmService,
    private loader: AppLoaderService,
    @Inject(PortfolioService) private portfolioService: PortfolioService
    
   
  
  ) { }

  ngOnInit() {
    this.displayedColumns = this.getDisplayedColumns();
    this.getItems()

  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  getDisplayedColumns() {
    //return ['name', 'age', 'balance', 'company', 'status', 'actions'];
    return ['Symbol', 'OrderType','Amount', 'TakeProfit', 'StopLoss', 'Date', 'actions'];
  }
  getItems() {    
    this.getItemSub = this.tableService.getItems()
      .subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      })
  }

}
