import { Component } from '@angular/core';
import { DefaultLoginLayoutComponent } from '../../components/default-login-layout/default-login-layout.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PrimaryInputComponent } from '../../components/primary-input/primary-input.component';
import { Router } from '@angular/router';
import { SignupService } from '../../services/signup.service';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

interface SignupForm {
  name: FormControl<string | null>;
  lastName: FormControl<string | null>; // Alterado para `lastName`
  username: FormControl<string | null>;
  email: FormControl<string | null>;
  role: FormControl<string | null>;
  course: FormControl<string | null>;
  password: FormControl<string | null>;
  passwordConfirm: FormControl<string | null>;
}

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    DefaultLoginLayoutComponent,
    ReactiveFormsModule,
    PrimaryInputComponent,
    CommonModule
  ],
  providers: [SignupService],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'], // Corrigido para `styleUrls`
})
export class SignupComponent {
  signupForm!: FormGroup<SignupForm>;

  constructor(
    private router: Router,
    private signupService: SignupService,
    private toastService: ToastrService
  ) {
    this.signupForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      lastName: new FormControl('', [Validators.required, Validators.minLength(3)]), // Alterado para `lastName`
      username: new FormControl('', [Validators.required, Validators.minLength(3)]),
      email: new FormControl('', [
        Validators.required,
        Validators.email,
        this.emailDomainValidator('academico.ifpb.edu.br'),
      ]),
      role: new FormControl('', [Validators.required]),
      course: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      passwordConfirm: new FormControl('', [Validators.required, Validators.minLength(6)]),
    }, { validators: this.passwordMatchValidator });
  }

  // Validação para garantir que os campos de senha sejam iguais
  passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
    if (control instanceof FormGroup) {
      const password = control.get('password')?.value;
      const passwordConfirm = control.get('passwordConfirm')?.value;
      return password === passwordConfirm ? null : { passwordMismatch: true };
    }
    return null;
  }

  // Validação para aceitar somente e-mails com domínio específico
  emailDomainValidator(domain: string): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const email = control.value;
      if (email && !email.endsWith(`@${domain}`)) {
        return { invalidDomain: { value: email } };
      }
      return null;
    };
  }

  // Método de envio do formulário
  submit(): void {
    if (this.signupForm.valid) {
      // Remover o campo `passwordConfirm` antes de enviar
      const { passwordConfirm, ...formData } = this.signupForm.value;

      // Enviar somente os dados esperados pelo backend
      this.signupService.signup(formData).subscribe({
        next: () => {
          this.toastService.success('Registro realizado com sucesso!', 'Sucesso');
          this.router.navigate(['/login']);
        },
        error: (err) => {
          this.toastService.error('Erro ao realizar o registro. Tente novamente.', 'Erro');
          console.error('Erro no registro:', err);
        },
      });
    } else {
      this.toastService.warning('Preencha todos os campos corretamente.', 'Atenção');
    }
  }

  // Navegação para a página de login
  navigate() {
    this.router.navigate(['login']);
  }
}

