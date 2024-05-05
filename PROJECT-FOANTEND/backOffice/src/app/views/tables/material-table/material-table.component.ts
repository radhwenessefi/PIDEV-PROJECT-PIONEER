import { Component, OnInit, ViewChild } from '@angular/core';
import { TablesService } from '../tables.service';
import { MatPaginator as MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource as MatTableDataSource } from '@angular/material/table';
import { egretAnimations } from 'app/shared/animations/egret-animations';
import { PortfolioService } from 'app/views/Portfolio-Service/portfolio.Service';
import { Inject } from '@angular/core';


@Component({
  selector: 'app-material-table',
  templateUrl: './material-table.component.html',
  styleUrls: ['./material-table.component.scss'],
  animations: egretAnimations
})
export class MaterialTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  
  displayedColumns: string[] = [];
  dataSource: any;
  data: any;

  constructor(private tableService: TablesService,
    
    @Inject(PortfolioService) private portfolioService: PortfolioService
  
  ) { }

  ngOnInit() {
    this.displayedColumns = this.tableService.getDataConf().map((c) => c.prop)
    this.dataSource = new MatTableDataSource(this.tableService.getAll());
    //consume the service portfolioService to get the data
    //format the data to json
    
    this.portfolioService.getDataPortfolio().subscribe((data: any[]) => {
      this.data = data;
      console.log("the result as an array of JSON objects: ", this.data);
    })
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

}
