import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaypalSuccessSubComponent } from './paypal-success-sub.component';

describe('PaypalSuccessSubComponent', () => {
  let component: PaypalSuccessSubComponent;
  let fixture: ComponentFixture<PaypalSuccessSubComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaypalSuccessSubComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaypalSuccessSubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
