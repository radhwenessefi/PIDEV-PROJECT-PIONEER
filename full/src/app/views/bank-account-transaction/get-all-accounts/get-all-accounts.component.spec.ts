import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllAccountsComponent } from './get-all-accounts.component';

describe('GetAllAccountsComponent', () => {
  let component: GetAllAccountsComponent;
  let fixture: ComponentFixture<GetAllAccountsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetAllAccountsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetAllAccountsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
