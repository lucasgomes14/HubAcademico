import { Component } from '@angular/core';


@Component({
  selector: 'app-profile',
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent {
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

  // Função para fechar o modal
  closeModal() {
    const modal = document.getElementById("editModal");
    if (modal) {
      modal.style.display = "none"; // Oculta o modal
    }
  }

   // Função para disparar o clique no input de arquivo
   triggerFileInput(event: Event): void {
    event.preventDefault();
    const fileInput = document.getElementById('fileInput') as HTMLInputElement;
    if (fileInput) {
      fileInput.click(); // Simula um clique no input de arquivo
    }
  }

  // Função para tratar a mudança de arquivo (quando o usuário escolhe uma imagem)
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
