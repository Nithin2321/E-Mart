import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { accesscontrolGuard } from './accesscontrol.guard';

describe('accesscontrolGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => accesscontrolGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
