import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { SavedComponent } from './pages/saved/saved.component';
import { NotificationComponent } from './pages/notification/notification.component';
import { SearchComponent } from './pages/search/search.component';

export const routes: Routes = [
    //criando rota de login
    {
        path: "",
        pathMatch: "full",
        redirectTo: "login"
    },
    {
        path: "login",
        component: LoginComponent
    },
    {
        path: "signup",
        component: SignupComponent
    },
    {
        path: "dashboard", //home
        component: DashboardComponent
    },
    {
      path: "search",
      component: SearchComponent
    },
    {
      path: "notifications",
      component: NotificationComponent
    },
    {
        path: "saved",
        component: SavedComponent
    },
    {
      path: "profile",
      component: ProfileComponent
    },
    {
        path: "**",
        redirectTo: "login"
    }
];
