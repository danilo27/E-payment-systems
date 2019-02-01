import { TestBed, inject } from '@angular/core/testing';

import { SupportedPaymentsService } from './supported-payments.service';

describe('SupportedPaymentsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SupportedPaymentsService]
    });
  });

  it('should be created', inject([SupportedPaymentsService], (service: SupportedPaymentsService) => {
    expect(service).toBeTruthy();
  }));
});
