import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

export const accesscontrolGuard: CanActivateFn = (route, state) => {
  const router=inject(Router);
  const token=localStorage.getItem("token");
  if(!token){
    router.navigate(["/login"])
    return false;
  }
  try {
    const decoded: any = jwtDecode(token);
    const userRole = decoded.role;
    const allowedRoles = route.data['roles'] as Array<string>;
    if (allowedRoles.includes(userRole)) {
      return true;
    } else {
      alert('Access Denied');
      router.navigate(['/']);
      return false;
    }
  } catch (error) {
    router.navigate(['/login']);
    return false;
  }
};
