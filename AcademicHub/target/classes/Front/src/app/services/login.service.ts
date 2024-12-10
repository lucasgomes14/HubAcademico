import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginResponse } from '../types/login-response.type';
import { tap } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  apiUrl : string = "http://localhost:8080/auth/login"

  constructor(private httpClient: HttpClient) { }

  login(email: string, password: string ){
    return this.httpClient.post<LoginResponse>(this.apiUrl, { email, password}).pipe(
      tap((value) => {
        sessionStorage.setItem("email", value.email)
        sessionStorage.setItem("auth-token", value.token)
      })
    )
  }
  

}
