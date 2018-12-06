import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BitcoinSuccessComponent } from './bitcoin-success.component';

describe('BitcoinSuccessComponent', () => {
  let component: BitcoinSuccessComponent;
  let fixture: ComponentFixture<BitcoinSuccessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BitcoinSuccessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BitcoinSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
