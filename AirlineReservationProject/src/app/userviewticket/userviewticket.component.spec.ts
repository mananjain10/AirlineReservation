import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserviewticketComponent } from './userviewticket.component';

describe('UserviewticketComponent', () => {
  let component: UserviewticketComponent;
  let fixture: ComponentFixture<UserviewticketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserviewticketComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserviewticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
