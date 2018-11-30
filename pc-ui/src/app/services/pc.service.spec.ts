import { TestBed } from '@angular/core/testing';

import { PcService } from './pc.service';

describe('PcService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PcService = TestBed.get(PcService);
    expect(service).toBeTruthy();
  });
});
