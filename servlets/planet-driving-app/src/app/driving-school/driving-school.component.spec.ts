import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrivingSchoolComponent } from './driving-school.component';

describe('DrivingSchoolComponent', () => {
  let component: DrivingSchoolComponent;
  let fixture: ComponentFixture<DrivingSchoolComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DrivingSchoolComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DrivingSchoolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
