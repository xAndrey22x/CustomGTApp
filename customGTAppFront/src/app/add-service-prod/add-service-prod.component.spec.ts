import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddServiceProdComponent } from './add-service-prod.component';

describe('AddServiceProdComponent', () => {
  let component: AddServiceProdComponent;
  let fixture: ComponentFixture<AddServiceProdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddServiceProdComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddServiceProdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
