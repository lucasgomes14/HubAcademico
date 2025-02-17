import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-notifications',
  imports: [],
  templateUrl: './notifications.component.html',
  styleUrl: './notifications.component.scss'
})
export class NotificationsComponent implements OnInit {
  // Definindo os itens do Sidebar
  menuItems = [
    { name: 'Home', icon: 'assets/casa.png' },
    { name: 'Search', icon: 'assets/procurar.png' },
    { name: 'Notifications', icon: 'assets/sino-selecionado.png' },
    { name: 'Saved Posts', icon: 'assets/marca-paginas.png' },
    { name: 'Profile', icon: 'assets/do-utilizador.png' }
  ];

  constructor() { }

  ngOnInit(): void {
  }
}
