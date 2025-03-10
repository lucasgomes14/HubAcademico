import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { map, Observable } from "rxjs";

export interface DashboardPostDTO {
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
export class DashboardService {

  private urlFeed = 'http://localhost:8081/post/feed';
  private urlPost = 'http://localhost:8081/dashboard/post';
  private urlLike = 'http://localhost:8081/post/like';
  private urlComment = 'http://localhost:8081/post/comment'; // Adicione o endpoint correto da API

  constructor(private http: HttpClient) {}

  getFriendPosts(): Observable<DashboardPostDTO[]> {
    const token = sessionStorage.getItem('auth-token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    const params = new HttpParams().set('page', 'dashboard').set('username', 'null');

    return this.http.get<any[]>(this.urlFeed, { headers, params }).pipe(
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

  like(likeData: { idPost: number }): Observable<any> {
    const token = sessionStorage.getItem('auth-token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    const params = new HttpParams().set('idPost', likeData.idPost);

    return this.http.post<any>(this.urlLike, { headers, params });
  }

  addComment(commentData: { postId: number; comment: string }): Observable<any> {
    const token = sessionStorage.getItem('auth-token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    return this.http.post<any>(this.urlComment, commentData, { headers });
  }
}
