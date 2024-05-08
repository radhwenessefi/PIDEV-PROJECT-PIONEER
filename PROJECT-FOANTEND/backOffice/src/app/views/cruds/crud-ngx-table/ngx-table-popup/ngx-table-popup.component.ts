import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef as MatDialogRef, MAT_DIALOG_DATA as MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UntypedFormBuilder, Validators, UntypedFormGroup } from '@angular/forms';
import { PortfolioService } from 'app/views/Portfolio-Service/portfolio.Service';
import { get } from 'http';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-ngx-table-popup',
  templateUrl: './ngx-table-popup.component.html'
})
export class NgxTablePopupComponent implements OnInit {
  public itemForm: UntypedFormGroup;
  //portfolioID: any;
  userid : any;
  portfolioid
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<NgxTablePopupComponent>,
    private fb: UntypedFormBuilder,
    private http: HttpClient,
    @Inject(PortfolioService) private portfolioService: PortfolioService
  ) { }

  ngOnInit() {
    this.buildItemForm(this.data.payload)
    const portfolioID = this.data.portfolioID
    this.portfolioid = this.data.portfolioID
    this.userid = 1
    console.log("the data iiiiiiiiiiid: ", this.data.portfolioID)
  
  }
  buildItemForm(item) {
    // Get the current date
    const dateOfInsecription = new Date().toISOString().split('T')[0]; // Format: YYYY-MM-DD

    this.itemForm = this.fb.group({
      amount: [item.amount || ''],
      takeProfit: [item.takeProfit || ''],
      stopLoss: [item.stopLoss || ''],
      orderType: [item.orderType || ''],
      dateOfInsecription: [dateOfInsecription] // Add current date to the form
    });
  
}
  

  submit() {
    console.log("the item form iiis: ", this.itemForm);
    //this.dialogRef.close(this.itemForm.value)
    //consume addInvestment from PortfolioService
    console.log("the result as an array of JSON objects: ",this.itemForm.value, this.userid, this.portfolioid);
    this.portfolioService.addInvestment(this.itemForm.value, this.userid, this.portfolioid).subscribe((data: any[]) => {
      this.data = data;
      console.log("the result as an array of JSON objects: ", this.data);
    })
  }
}
