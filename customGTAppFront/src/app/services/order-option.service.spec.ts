import { TestBed } from '@angular/core/testing';

import { OrderOptionService } from './order-option.service';

describe('OrderOptionService', () => {
  let service: OrderOptionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrderOptionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
