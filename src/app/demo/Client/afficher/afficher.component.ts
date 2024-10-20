import { Component, OnInit } from '@angular/core';
import { CrudService } from 'src/app/theme/shared/services/crudService.service';
import { SharedModule } from 'src/app/theme/shared/shared.module';
@Component({
  selector: 'app-afficher',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './afficher.component.html',
  styleUrl: './afficher.component.scss'
})
export class AfficherComponent implements OnInit {
  clients:any
  constructor(private crudService:CrudService){

  }

  ngOnInit(): void {
      this.crudService.getClients().subscribe(
        res=>{
          this.clients=res
          console.log(res);
          
        }
      )
  }

}
