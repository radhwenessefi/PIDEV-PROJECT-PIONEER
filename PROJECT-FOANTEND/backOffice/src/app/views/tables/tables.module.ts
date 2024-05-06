import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatInputModule as MatInputModule } from '@angular/material/input';
import { MatPaginatorModule as MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule as MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule as MatMenuModule } from '@angular/material/menu';
import { MatButtonModule as MatButtonModule } from '@angular/material/button';
import { MatChipsModule as MatChipsModule } from '@angular/material/chips';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule as MatTooltipModule } from '@angular/material/tooltip';
import { MatDialogModule as MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule as MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSlideToggleModule as MatSlideToggleModule } from '@angular/material/slide-toggle';
import { ReactiveFormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';
import { SharedModule } from '../../shared/shared.module';
import { NgxTablePopupComponent } from 'app/views/cruds/crud-ngx-table/ngx-table-popup/ngx-table-popup.component';

import { TablesService } from './tables.service';




import { TablesRoutes } from './tables.routing';
import { MaterialTableComponent } from './material-table/material-table.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatIconModule,
    MatCardModule,
    MatMenuModule,
    MatButtonModule,
    MatChipsModule,
    MatListModule,
    MatPaginatorModule,
    MatTooltipModule,
    MatTableModule,
    MatDialogModule,
    MatSnackBarModule,
    MatSlideToggleModule,
    TranslateModule,
    SharedModule,
    RouterModule.forChild(TablesRoutes)
  ],
  declarations: [MaterialTableComponent],
  providers: [TablesService]
})
export class TablesModule { }
