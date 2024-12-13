import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

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

  private apiUrl = 'http://localhost:8080/profile';

  constructor(private http: HttpClient) { }

  getUserProfile(username: string): Observable<UserProfile> {
    return this.http.get<UserProfile>(`${this.apiUrl}/${username}`);
  }
}
