import { CanActivateFn } from '@angular/router';

export const autorizadoGuard: CanActivateFn = (route, state) => {
  return false;
};
