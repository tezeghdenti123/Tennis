import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'ajouter',
        loadComponent: () => import('./ajouter/ajouter.component').then(m => m.AjouterComponent)
      },
      {
        path: 'afficher',
        loadComponent: () => import('./afficher/afficher.component').then(m => m.AfficherComponent)
      },
      
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoachRoutingModule {}
