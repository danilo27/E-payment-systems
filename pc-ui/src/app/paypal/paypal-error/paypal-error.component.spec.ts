import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaypalErrorComponent } from './paypal-error.component';

describe('PaypalErrorComponent', () => {
  let component: PaypalErrorComponent;
  let fixture: ComponentFixture<PaypalErrorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaypalErrorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaypalErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
