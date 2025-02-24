import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {map, Observable, tap} from 'rxjs';
import {DashboardPostDTO} from './dashboard.service';

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
}

export interface ProfilePostDTO {
  id: number;
  username: string;
  profilePicture: string;
  minutesAgo: number;
  description: string;
  likes: number;
  comments: number;
  isLiked: boolean;
}

export interface LikeResponseDTO {
  countLikes: number;
  hasLiked: boolean;
}

@Injectable({
  providedIn: 'root'
})

export class UserProfileService {

  private urlFeed = 'http://localhost:8081/post/feed'
  private apiUrl = 'http://localhost:8081/profile';
  private urlLike = 'http://localhost:8081/post/like'

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

  getPosts(username: string): Observable<ProfilePostDTO[]> {
    const token = sessionStorage.getItem('auth-token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    const params = new HttpParams().set('page', 'profile').set('username', username)

    return this.http.get<any[]>(this.urlFeed, { headers, params }).pipe(
      map(posts =>
        posts.map(post => ({
          ...post
        }))
      )
    );
  }

  like(likeData: { idPost: number; }): Observable<any> {
    const token = sessionStorage.getItem('auth-token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.post<any>(this.urlLike, likeData, { headers });
  }
}
