import { InvokeFunctionExpr } from '@angular/compiler';
import { Component, Input, OnInit } from '@angular/core';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],

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



  constructor() { }

  ngOnInit(): void {
  }
}
