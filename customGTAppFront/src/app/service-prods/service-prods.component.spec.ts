import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceProdsComponent } from './service-prods.component';

describe('ServiceProdsComponent', () => {
  let component: ServiceProdsComponent;
  let fixture: ComponentFixture<ServiceProdsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ServiceProdsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ServiceProdsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
