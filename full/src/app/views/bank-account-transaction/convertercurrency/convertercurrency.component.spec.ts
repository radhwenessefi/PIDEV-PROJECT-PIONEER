import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConvertercurrencyComponent } from './convertercurrency.component';

describe('ConvertercurrencyComponent', () => {
  let component: ConvertercurrencyComponent;
  let fixture: ComponentFixture<ConvertercurrencyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ConvertercurrencyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConvertercurrencyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
