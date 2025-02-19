import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  apiUrl : string = "http://localhost:8081/auth/register"

  constructor(private httpClient: HttpClient) { }

  signup(userData: any): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl, userData);
  }
}
