import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BitcoinErrorComponent } from './bitcoin-error.component';

describe('BitcoinErrorComponent', () => {
  let component: BitcoinErrorComponent;
  let fixture: ComponentFixture<BitcoinErrorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BitcoinErrorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BitcoinErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
