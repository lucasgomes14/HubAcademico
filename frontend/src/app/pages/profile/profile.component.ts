import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserProfileService} from '../../services/user-profile.service';
import {DatePipe, NgForOf, NgIf} from '@angular/common';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  imports: [
    NgForOf,
    DatePipe,
    FormsModule,
    ReactiveFormsModule,
    NgIf
  ],
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  userProfile: any = null; // Variável para armazenar os dados do perfil
  username: string = '';    // Variável para armazenar o username da URL
  isOwner: boolean = false;
  editProfileForm : FormGroup;

  constructor(
    private route: ActivatedRoute, // Para acessar os parâmetros da URL
    private userProfileService: UserProfileService, // Serviço para buscar o perfil
    private fb: FormBuilder,
    private router: Router
  ) {
    this.editProfileForm = this.fb.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      bio: [''],
      profilePicture: ['']
    });
  }

  ngOnInit(): void {
    // Obtém o username da URL
    this.username = this.route.snapshot.paramMap.get('username') || 'me';
    console.log("Username recebido da URL:", this.username);

    if (!this.username) {
      this.router.navigate(['/dashboard']);  // Redireciona para o dashboard
      console.log("Username nao encontrado")
      return;
    }

    // Verifica se o username está presente
    if (this.username) {
      this.userProfileService.getUserProfile(this.username).subscribe({
        next: (data) => {
          this.userProfile = data; // Preenche os dados do perfil

          this.isOwner = sessionStorage.getItem("username") === this.username;

          this.editProfileForm.patchValue({
            name: this.userProfile.name,
            username: this.userProfile.username,
            bio: this.userProfile.bio,
            profilePicture: this.userProfile.profilePicture
          });
        },
        error: (err) => {
          console.error('Erro ao carregar o perfil', err);
          this.router.navigate(['/dashboard'])
        }
      });
    }

  }

  getProfilePicture(): string {
    return this.userProfile?.profilePicture
      ? `data:image/jpeg;base64,${this.userProfile.profilePicture}`
      : 'assets/Default-Profile-Picture.png'; // Imagem padrão caso não tenha
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

  onImageChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.editProfileForm.patchValue({ profilePicture: reader.result?.toString().split(',')[1] })
      };

      reader.readAsDataURL(file);
    } else {
      this.editProfileForm.patchValue({
        profilePicture: null
      });
    }
  }

  onSubmit() {
    if (this.editProfileForm.valid) {
      const updateUserProfileDTO = {
        name: this.editProfileForm.value.name,
        username: this.editProfileForm.value.username,
        bio: this.editProfileForm.value.bio,
        profilePicture: this.editProfileForm.value.profilePicture
      };

      this.userProfileService.updateUserProfile(this.username, updateUserProfileDTO).subscribe({
        next: (response) => {
          console.log('Perfil atualizado com sucesso!');
          this.userProfile = response;
          this.closeModal();

          this.router.navigate(['/dashboard'])
          sessionStorage.setItem("username", updateUserProfileDTO.username)
        },
        error: (err) => {
          console.error('Erro ao atualizar perfil', err);
        }
      });
    }
  }
}
