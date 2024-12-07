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
    //não botei a url do backend pq ainda n tem kkkk
    return this.httpClient.post<LoginResponse>(this.apiUrl, { email, password}).pipe(
      tap((value) => {
        sessionStorage.setItem("auth-token", value.token)
        sessionStorage.setItem("email", value.email)
      })
    )
  }
  //quando retornar aí, vai retornar um token
  //register(username: string,  email: string , password: string ){
    //não botei a url do backend pq ainda n tem kkkk
    //return this.httpClient.post<LoginResponse>(this.apiUrl, { username, email, password}).pipe(
      //tap((value) => {
        //sessionStorage.setItem("auth-token", value.token)
        //sessionStorage.setItem("username", value.username)
        //sessionStorage.setItem("username", value.email)
      //})
    //)
  //}

}
