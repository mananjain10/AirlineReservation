import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminviewbookingsComponent } from './adminviewbookings.component';

describe('AdminviewbookingsComponent', () => {
  let component: AdminviewbookingsComponent;
  let fixture: ComponentFixture<AdminviewbookingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminviewbookingsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminviewbookingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
