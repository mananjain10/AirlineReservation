import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowSearchedFlightsComponent } from './show-searched-flights.component';

describe('ShowSearchedFlightsComponent', () => {
  let component: ShowSearchedFlightsComponent;
  let fixture: ComponentFixture<ShowSearchedFlightsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowSearchedFlightsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowSearchedFlightsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
