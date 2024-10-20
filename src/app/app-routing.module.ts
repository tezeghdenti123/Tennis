// Angular Import
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// project import
import { AdminComponent } from './theme/layout/admin/admin.component';
import { GuestComponent } from './theme/layout/guest/guest.component';
import { AuthGuard } from './theme/shared/authGuard';

const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    children: [
      {
        path: '',
        redirectTo: '/analytics',
        pathMatch: 'full'
      },
      {
        path: 'analytics',
        loadComponent: () => import('./demo/dashboard/dash-analytics.component'),
        canActivate:[AuthGuard]
      },
      {
        path: 'component',
        loadChildren: () => import('./demo/ui-element/ui-basic.module').then((m) => m.UiBasicModule),
        canActivate:[AuthGuard]
      },
      {
        path: 'client',
        loadChildren: () => import('./demo/Client/client.module').then((m) => m.ClientModule),
        canActivate:[AuthGuard]
      },
      {
        path: 'coach',
        loadChildren: () => import('./demo/Coach/coach.module').then((m) => m.CoachModule),
        canActivate:[AuthGuard]
      },
      {
        path: 'affectation',
        loadChildren: () => import('./demo/Affectation/affectation.module').then((m) => m.AffectationModule),
        canActivate:[AuthGuard]
      },
      {
        path: 'chart',
        loadComponent: () => import('./demo/chart & map/core-apex.component'),
        canActivate:[AuthGuard]
      },
      {
        path: 'forms',
        loadComponent: () => import('./demo/forms & tables/form-elements/form-elements.component'),
        canActivate:[AuthGuard]
      },
      {
        path: 'tables',
        loadComponent: () => import('./demo/forms & tables/tbl-bootstrap/tbl-bootstrap.component'),
        canActivate:[AuthGuard]
      },
      {
        path: 'sample-page',
        loadComponent: () => import('./demo/sample-page/sample-page.component'),
        canActivate:[AuthGuard]
      }
    ]
  },
  {
    path: '',
    component: GuestComponent,
    children: [
      {
        path: 'auth/signup',
        loadComponent: () => import('./demo/authentication/sign-up/sign-up.component')
      },
      {
        path: 'auth/signin',
        loadComponent: () => import('./demo/authentication/sign-in/sign-in.component')
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
