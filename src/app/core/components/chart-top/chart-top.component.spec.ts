import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartTopComponent } from './chart-top.component';

describe('ChartTopComponent', () => {
  let component: ChartTopComponent;
  let fixture: ComponentFixture<ChartTopComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChartTopComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChartTopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
