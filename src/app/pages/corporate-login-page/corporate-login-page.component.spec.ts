import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CorporateLoginPageComponent } from './corporate-login-page.component';

describe('CorporateLoginPageComponent', () => {
  let component: CorporateLoginPageComponent;
  let fixture: ComponentFixture<CorporateLoginPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CorporateLoginPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CorporateLoginPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
