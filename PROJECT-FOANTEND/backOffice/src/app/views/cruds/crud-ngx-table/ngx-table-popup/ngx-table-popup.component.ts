import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef as MatDialogRef, MAT_DIALOG_DATA as MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UntypedFormBuilder, Validators, UntypedFormGroup } from '@angular/forms';

@Component({
  selector: 'app-ngx-table-popup',
  templateUrl: './ngx-table-popup.component.html'
})
export class NgxTablePopupComponent implements OnInit {
  public itemForm: UntypedFormGroup;
  //portfolioID: any;
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<NgxTablePopupComponent>,
    private fb: UntypedFormBuilder,
  ) { }

  ngOnInit() {
    this.buildItemForm(this.data.payload)
    const portfolioID = this.data.portfolioID
    const portfolioid = this.data.portfolioID
    const userid = 1
    console.log("the data iiiiiiiiiiid: ", this.data.portfolioID)
  
  }
  buildItemForm(item) {
    this.itemForm = this.fb.group({
      amount: [item.amount || ''],
      takeProfit: [item.takeProfit || ''],
      StopLoss: [item.StopLoss || ''],
      orderType: [item.orderType  || ''],
    });
  }
  

  submit() {
    console.log("the item form iiis: ", this.itemForm);
    //this.dialogRef.close(this.itemForm.value)
  }
}
