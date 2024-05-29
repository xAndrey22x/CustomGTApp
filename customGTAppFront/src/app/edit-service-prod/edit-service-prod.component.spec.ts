import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditServiceProdComponent } from './edit-service-prod.component';

describe('EditServiceProdComponent', () => {
  let component: EditServiceProdComponent;
  let fixture: ComponentFixture<EditServiceProdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditServiceProdComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditServiceProdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
