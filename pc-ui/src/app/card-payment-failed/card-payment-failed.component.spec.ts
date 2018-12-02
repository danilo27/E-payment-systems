import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardPaymentFailedComponent } from './card-payment-failed.component';

describe('CardPaymentFailedComponent', () => {
  let component: CardPaymentFailedComponent;
  let fixture: ComponentFixture<CardPaymentFailedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardPaymentFailedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardPaymentFailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
