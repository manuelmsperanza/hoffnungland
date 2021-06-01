import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DriveBookingComponent } from './drive-booking.component';

describe('DriveBookingComponent', () => {
  let component: DriveBookingComponent;
  let fixture: ComponentFixture<DriveBookingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DriveBookingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DriveBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
