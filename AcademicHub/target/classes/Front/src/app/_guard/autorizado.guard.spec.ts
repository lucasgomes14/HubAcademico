import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { autorizadoGuard } from './autorizado.guard';

describe('autorizadoGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => autorizadoGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
