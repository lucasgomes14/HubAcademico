import {Component, OnInit} from '@angular/core';
import {DashboardPostDTO, DashboardService} from '../../services/dashboard.service';
import {NgForOf} from '@angular/common';
import {FormsModule, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ReactiveFormsModule } from '@angular/forms';
import {CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],

  imports: [
    NgForOf,
    FormsModule,
    ReactiveFormsModule,
    CommonModule
  ]
})
export class DashboardComponent implements OnInit {
  menuItems = [
    { name: 'Home', icon: 'assets/casa (1).png' },
    { name: 'Search', icon: 'assets/procurar (1).png' },
    { name: 'Notifications', icon: 'assets/sino (1).png' },
    { name: 'Saved Posts', icon: 'assets/marca-paginas (1).png' },
    { name: 'Profile', icon: 'assets/do-utilizador (1).png' }
  ];

  posts: DashboardPostDTO[] = [];
  postText: string = '';
  isCommenting: boolean = false;
  commentForm: FormGroup;
  activePostId: number | null = null;

  constructor(private dashboardService: DashboardService, private router: Router, private fb: FormBuilder) {
    this.commentForm = this.fb.group({
      comment: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadFriendPosts();
  }

  loadFriendPosts(): void {
    this.dashboardService.getFriendPosts().subscribe({
      next: (data) => {
        this.posts = data;
      },
      error: (error) => {
        console.error('Erro ao carregar posts:', error);
      }
    });
  }

  postar() {
    if (!this.postText.trim()) {
      alert('O post não pode estar vazio!');
      return;
    }

    const postData = { description: this.postText };

    this.dashboardService.createPost(postData).subscribe({
      next: () => {
        alert('Post enviado com sucesso!');
        this.postText = '';
        this.loadFriendPosts();
      },
      error: (err) => {
        console.error('Erro ao postar:', err);
        alert('Erro ao postar. Tente novamente.');
      }
    });
  }

  navigateTo(route: String) {
    console.log("tentando navegar para:", route);
    this.router.navigate([route]);
  }

  like(id: number) {
    const likeData = { idPost: id };

    this.dashboardService.like(likeData).subscribe({
      next: (response) => {
        const post = this.posts.find(p => p.id === id);
        if (post) {
          post.likes = response.countLikes;
          post.isLiked = response.hasLiked;
        }
      },
      error: (error) => {
        console.error('Erro ao curtir:', error);
      }
    });
  }

  openCommentModal(postId: number) {
    this.isCommenting = true;
    this.activePostId = postId;
  }

  closeCommentModal() {
    this.isCommenting = false;
  }

  submitComment() {
    if (!this.activePostId) return;
    const commentData = { postId: this.activePostId, comment: this.commentForm.value.comment };

    this.dashboardService.addComment(commentData).subscribe({
      next: () => {
        alert('Comentário adicionado com sucesso!');
        this.closeCommentModal();
        this.loadFriendPosts();
      },
      error: (err: any) => {
        console.error('Erro ao comentar:', err);
        alert('Erro ao comentar. Tente novamente.');
      }
    });
  }
}
