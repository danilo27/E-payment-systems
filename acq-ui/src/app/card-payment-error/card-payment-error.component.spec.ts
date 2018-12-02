import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardPaymentErrorComponent } from './card-payment-error.component';

describe('CardPaymentErrorComponent', () => {
  let component: CardPaymentErrorComponent;
  let fixture: ComponentFixture<CardPaymentErrorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardPaymentErrorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardPaymentErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
