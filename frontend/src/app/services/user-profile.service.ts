import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {map, Observable} from 'rxjs';

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
  private urlLike = 'http://localhost:8081/post/like'
  private apiUrl = 'http://localhost:8081/profile';
  private urlFollowUser = 'http://localhost:8081/profile/followUser';
  private urlUnfollowUser = 'http://localhost:8081/profile/unfollowUser';

  constructor(private http: HttpClient) { }

  getUserProfile(username: string): Observable<UserProfile> {
    const headers = this.getToken()

    return this.http.get<UserProfile>(`${this.apiUrl}/${username}`, { headers });
  }

  updateUserProfile(username: string, updateUserProfileDTO: any) {
    const headers = this.getToken()

    return this.http.put<any>(`${this.apiUrl}/${username}`, updateUserProfileDTO, { headers });
  }

  getPosts(username: string): Observable<ProfilePostDTO[]> {
    const headers = this.getToken()

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
    const headers = this.getToken()

    return this.http.post<any>(this.urlLike, likeData, { headers });
  }

  followUser(username: { username: string }): Observable<any> {
    const headers = this.getToken();

    return this.http.post<any>(this.urlFollowUser, username , { headers });
  }

  unfollowUser( username: { username: string } ): Observable<any> {
    const headers = this.getToken();

    return this.http.post<any>(this.urlUnfollowUser , username, { headers });
  }

  getToken(): HttpHeaders {
    const token = sessionStorage.getItem('auth-token');

    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }
}
