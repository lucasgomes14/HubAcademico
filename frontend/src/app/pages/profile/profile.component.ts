import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserProfileService} from '../../services/user-profile.service';
import {NgForOf, NgIf} from '@angular/common';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {DashboardPostDTO} from '../../services/dashboard.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  imports: [
    NgForOf,
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
  editProfileForm: FormGroup;
  posts: DashboardPostDTO[] = [];

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
    this.loadPosts();
    // Obtém o username da URL
    this.username = this.route.snapshot.paramMap.get('username') || 'me';

    if (this.username == 'me') {
      this.router.navigate(['/profile']);  // Redireciona para o dashboard
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
        this.editProfileForm.patchValue({profilePicture: reader.result?.toString().split(',')[1]})
      };

      reader.readAsDataURL(file);
    } else {
      this.editProfileForm.patchValue({
        profilePicture: null
      });
    }
  }

  onSubmit() {
    this.loadPosts();
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

  toggleFollow(): void {
    this.username = this.route.snapshot.paramMap.get('username') || ('me');

    if (!this.userProfile.isFollowing){
      this.userProfileService.followUser({username: this.username}).subscribe({
        next: () => {
          this.userProfile.isFollowing = !this.userProfile.isFollowing
          this.userProfile.followersCount += 1
        },
        error: (error) => {
          console.error('Erro ao tentar seguir usuário:', error);
        }
      });
    } else {
      this.userProfileService.unfollowUser({username: this.username}).subscribe({
        next: () => {
          this.userProfile.isFollowing = !this.userProfile.isFollowing
          this.userProfile.followersCount -= 1
        },
        error: (error) => {
          console.error('Erro ao tentar parar de seguir o usuário:', error);
        }
      });
    }
  }

  loadPosts(): void {
    this.username = this.route.snapshot.paramMap.get('username') || ('me');
    this.userProfileService.getPosts(this.username).subscribe({
      next: (data) => {
        this.posts = data;
      },
      error: (error) => {
        console.error('Erro ao carregar posts:', error);
      }
    });
  }

  like(id: number) {
    const likeData = {idPost: id};

    this.userProfileService.like(likeData).subscribe({
      next: (response) => {
        const post = this.posts.find(p => p.id === id);
        if (post) {
          post.likes = response.countLikes; // Ou ajuste conforme a resposta do backend
          post.isLiked = response.hasLiked;
        }
      },
      error: (error) => {
        console.error('Erro ao curtir:', error);
      }
    });
  }
}
