import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterBuyerDetailsComponent } from './enter-buyer-details.component';

describe('EnterBuyerDetailsComponent', () => {
  let component: EnterBuyerDetailsComponent;
  let fixture: ComponentFixture<EnterBuyerDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnterBuyerDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnterBuyerDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
