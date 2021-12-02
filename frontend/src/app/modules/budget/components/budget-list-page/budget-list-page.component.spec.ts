import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BudgetListPageComponent } from './budget-list-page.component';

describe('BudgetPageComponent', () => {
  let component: BudgetListPageComponent;
  let fixture: ComponentFixture<BudgetListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BudgetListPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BudgetListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
