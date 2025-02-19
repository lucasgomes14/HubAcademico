import {Component, OnInit} from '@angular/core';
import { DashboardService, DashboardPostDTO  } from '../../services/dashboard.service';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],

  imports: [
    NgForOf
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

  constructor(private dashboardService: DashboardService) { }

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
}
