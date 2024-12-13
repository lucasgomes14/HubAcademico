import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserProfileService} from '../../services/user-profile.service';
import {DatePipe, NgForOf} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  imports: [
    NgForOf,
    DatePipe,
    FormsModule,
    ReactiveFormsModule
  ],
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  userProfile: any = null; // Variável para armazenar os dados do perfil
  username: string = '';    // Variável para armazenar o username da URL

  constructor(
    private route: ActivatedRoute, // Para acessar os parâmetros da URL
    private userProfileService: UserProfileService // Serviço para buscar o perfil
  ) {
  }

  ngOnInit(): void {
    // Obtém o username da URL
    this.username = this.route.snapshot.paramMap.get('username') || '';

    // Verifica se o username está presente
    if (this.username) {
      this.userProfileService.getUserProfile(this.username).subscribe({
        next: (data) => {
          this.userProfile = data; // Preenche os dados do perfil
        },
        error: (err) => {
          console.error('Erro ao carregar o perfil', err);
        }
      });
    }
  }

  getProfilePicture(): string {
    return this.userProfile?.profilePicture
      ? `data:image/jpeg;base64,${this.userProfile.profilePicture}`
      : 'assets/profile-image.png'; // Imagem padrão caso não tenha
  }

  showModal() {
    const modal = document.getElementById("editModal");
    if (modal) {
      modal.style.display = "flex"; // Exibe o modal
      window.addEventListener('click', (event) => {
        if (event.target === modal) {
          this.closeModal();
        }
      });
    }
  }

  closeModal() {
    const modal = document.getElementById("editModal");
    if (modal) {
      modal.style.display = "none"; // Oculta o modal
    }
  }

  triggerFileInput(event: Event): void {
    event.preventDefault();
    const fileInput = document.getElementById('fileInput') as HTMLInputElement;
    if (fileInput) {
      fileInput.click(); // Simula um clique no input de arquivo
    }
  }

  handleFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    const file = input.files?.[0];

    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        const imageUrl = reader.result as string;
        console.log('Imagem selecionada:', imageUrl);

        // Exibindo a imagem no perfil
        const profileImage = document.getElementById('profile-image') as HTMLImageElement;
        if (profileImage) {
          profileImage.src = imageUrl; // Atualiza a imagem no perfil
        }
      };
      reader.readAsDataURL(file);
    }
  }
}
