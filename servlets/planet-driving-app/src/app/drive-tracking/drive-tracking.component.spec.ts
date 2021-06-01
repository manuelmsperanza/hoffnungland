import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DriveTrackingComponent } from './drive-tracking.component';

describe('DriveTrackingComponent', () => {
  let component: DriveTrackingComponent;
  let fixture: ComponentFixture<DriveTrackingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DriveTrackingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DriveTrackingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
