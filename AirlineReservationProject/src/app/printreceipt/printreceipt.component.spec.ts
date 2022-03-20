import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrintreceiptComponent } from './printreceipt.component';

describe('PrintreceiptComponent', () => {
  let component: PrintreceiptComponent;
  let fixture: ComponentFixture<PrintreceiptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PrintreceiptComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PrintreceiptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
