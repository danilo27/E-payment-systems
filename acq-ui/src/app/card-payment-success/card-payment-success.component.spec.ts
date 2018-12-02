import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardPaymentSuccessComponent } from './card-payment-success.component';

describe('CardPaymentSuccessComponent', () => {
  let component: CardPaymentSuccessComponent;
  let fixture: ComponentFixture<CardPaymentSuccessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardPaymentSuccessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardPaymentSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
