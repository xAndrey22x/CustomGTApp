import { TestBed } from '@angular/core/testing';

import { ServiceProdService } from './service-prod.service';

describe('ServiceProdService', () => {
  let service: ServiceProdService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceProdService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
