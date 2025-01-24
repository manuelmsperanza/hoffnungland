import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CallbackAuth0Component } from './callback-auth0.component';

describe('CallbackAuth0Component', () => {
  let component: CallbackAuth0Component;
  let fixture: ComponentFixture<CallbackAuth0Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CallbackAuth0Component]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CallbackAuth0Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
