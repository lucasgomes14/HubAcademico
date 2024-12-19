import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, tap} from 'rxjs';

export interface UserProfile {
  name: string;
  lastName: string;
  username: string;
  bio: string | null;
  dateAndTimeOfUserCreation: string;
  userUpdateDateAndTime: string | null;
  email: string;
  role: string;
  course: string;
  profilePicture: string | null;
  posts: any[];
}

@Injectable({
  providedIn: 'root'
})

export class UserProfileService {

  private apiUrl = 'http://localhost:8081/profile';

  constructor(private http: HttpClient) { }

  getUserProfile(username: string): Observable<UserProfile> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${sessionStorage.getItem('auth-token')}`
    });

    return this.http.get<UserProfile>(`${this.apiUrl}/${username}`, { headers });
  }

  updateUserProfile(username: string, updateUserProfileDTO: any) {

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${sessionStorage.getItem('auth-token')}`
    });

    return this.http.put<any>(`${this.apiUrl}/${username}`, updateUserProfileDTO, { headers });
  }
}
