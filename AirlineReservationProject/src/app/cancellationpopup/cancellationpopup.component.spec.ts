import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancellationpopupComponent } from './cancellationpopup.component';

describe('CancellationpopupComponent', () => {
  let component: CancellationpopupComponent;
  let fixture: ComponentFixture<CancellationpopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CancellationpopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CancellationpopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
