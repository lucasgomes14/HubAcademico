import { Router } from '@angular/router';
//import { router } from './../../app.routes';
import { Component, EventEmitter, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
  imports: [FormsModule, CommonModule],
  standalone:true
})
export class SearchComponent {
  searchTerm: string = '';
  usuarios: any[] = [];
  @Output("navigate") onNavigate = new EventEmitter();
  //private router: Router ;

  constructor(private http: HttpClient) {}

  buscarUsuarios() {
    if (this.searchTerm.trim() === '') {
      this.usuarios = [];
      return;
    }

    this.http.get<any[]>(`http://localhost:8080/usuarios?nome=${this.searchTerm}`)
      .subscribe(response => {
        this.usuarios = response;
      });
  }

  navigate() {
    //this.routes.navigate(['login']);
  }
}
