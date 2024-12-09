import { Component } from '@angular/core';


@Component({
  selector: 'app-profile',
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent {
  autoResize(event: Event): void {
    const textarea = event.target as HTMLTextAreaElement;
    textarea.style.height = 'auto';
    const newHeight = textarea.scrollHeight;
    textarea.style.height = newHeight + 'px';

    // Adicionando um log para verificar o valor da altura
    console.log('Nova altura:', newHeight)
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

  // Lista de seguidores fictícia
  followers = [
    { name: 'João Silva', image: 'assets/follower1.jpg' },
    { name: 'Maria Oliveira', image: 'assets/follower2.jpg' },
    { name: 'Carlos Souza', image: 'assets/follower3.jpg' },
    // Adicione mais seguidores conforme necessário
  ];

  showFollowers() {
  const modal = document.getElementById("followersModal");
  if (modal) {
    modal.style.display = "flex"; // Exibe o modal
  }

  // Preenche a lista de seguidores no modal
  const followersList = document.getElementById("followersList");
  if (followersList) {
    followersList.innerHTML = ''; // Limpa a lista antes de adicionar novos itens

    // Adiciona cada seguidor manualmente
    this.followers.forEach(follower => {
      // Verifica se o seguidor já foi adicionado
      const existingFollower = Array.from(followersList.children).find((child: Element) => {
        // Fazendo o cast para HTMLElement aqui
        const element = child as HTMLElement;
        return element.querySelector('p')?.textContent === follower.name;
      });

      // Só adiciona o seguidor se ele não estiver na lista
      if (!existingFollower) {
        const followerElement = document.createElement("div");
        followerElement.classList.add("follower-info");

        // Cria a imagem do seguidor
        const img = document.createElement("img");
        img.src = follower.image;
        img.alt = follower.name;
        img.classList.add("follower-image");

        // Cria o nome do seguidor
        const name = document.createElement("p");
        name.textContent = follower.name;

        // Adiciona a imagem e o nome no item
        followerElement.appendChild(img);
        followerElement.appendChild(name);

        // Adiciona o item na lista
        followersList.appendChild(followerElement);
      }
    });
  }
}

  // Função para fechar o modal de seguidores
  closeFollowersModal() {
    const modal = document.getElementById("followersModal");
    if (modal) {
      modal.style.display = "none"; // Oculta o modal
    }
  }
}
