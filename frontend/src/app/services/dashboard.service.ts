import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map, Observable} from 'rxjs';
import { ImageService } from './image.service';

export interface DashboardPostDTO {
  username: string;
  profilePicture: string;
  minutesAgo: number;
  description: string;
  likes: number;
  comments: number;
}

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private urlFeed = 'http://localhost:8081/dashboard/feed'
  private urlPost = 'http://localhost:8081/dashboard/post'

  constructor(private http: HttpClient) {
  }

  getFriendPosts(): Observable<DashboardPostDTO[]> {
    const token = sessionStorage.getItem('auth-token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.get<any[]>(this.urlFeed, { headers }).pipe(
      map(posts =>
        posts.map(post => ({
          ...post
        }))
      )
    );
  }

  createPost(postData: { description: string }): Observable<any> {
    const token = sessionStorage.getItem('auth-token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.post<any>(this.urlPost, postData, { headers });
  }
}
