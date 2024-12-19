import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, tap} from 'rxjs';

export interface Post {
  id: number;
  description: string;
  content: string;
  publicationUpdateDateAndTime: string;
  totalLikes: number;
  comments: any[]; // Pode ser ajustado para o tipo de comentário que você usar
  dateAndTimeOfPublication: string;
}

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
  posts: Post[];
}

@Injectable({
  providedIn: 'root'
})

export class UserProfileService {

  private apiUrl = 'http://localhost:8081/profile';
  private apiUrlLikes = 'http://localhost:8081/likes';

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

  likePost(postId: number, username: string): Observable<boolean> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${sessionStorage.getItem('auth-token')}`
    });

    const params = new HttpParams()
      .set('postId', postId.toString())
      .set('username', username);

    return this.http.post<boolean>(this.apiUrlLikes, null, { headers, params });
  }
}
