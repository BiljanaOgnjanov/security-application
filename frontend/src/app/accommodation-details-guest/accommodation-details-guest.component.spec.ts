import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccommodationDetailsGuestComponent } from './accommodation-details-guest.component';

describe('AccommodationDetailsGuestComponent', () => {
  let component: AccommodationDetailsGuestComponent;
  let fixture: ComponentFixture<AccommodationDetailsGuestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccommodationDetailsGuestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccommodationDetailsGuestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
