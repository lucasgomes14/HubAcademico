import { routes } from './../../app.routes';
import {Component, OnInit} from '@angular/core';
import { DashboardService, DashboardPostDTO  } from '../../services/dashboard.service';
import {NgForOf} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],

  imports: [
    NgForOf,
    FormsModule
  ]
})
export class DashboardComponent implements OnInit {
  // Definindo os itens de menu do Dashboard
  menuItems = [
    { name: 'Home', icon: 'assets/casa (1).png' },
    { name: 'Search', icon: 'assets/procurar (1).png' },
    { name: 'Notifications', icon: 'assets/sino (1).png' },
    { name: 'Saved Posts', icon: 'assets/marca-paginas (1).png' },
    { name: 'Profile', icon: 'assets/do-utilizador (1).png' }
  ];

  posts: DashboardPostDTO[] = [];
  postText: string = '';

  constructor(private dashboardService: DashboardService, private router : Router) { }

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
      },
      error: (err) => {
        console.error('Erro ao postar:', err);
        alert('Erro ao postar. Tente novamente.');
      }
    });
    }

    navigateTo( route : String) {
      console.log("tentando navegar para:", route);
      this.router.navigate([route]);
  }
}
