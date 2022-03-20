import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminviewticketComponent } from './adminviewticket.component';

describe('AdminviewticketComponent', () => {
  let component: AdminviewticketComponent;
  let fixture: ComponentFixture<AdminviewticketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminviewticketComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminviewticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
