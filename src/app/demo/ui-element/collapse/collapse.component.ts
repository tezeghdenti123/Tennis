// angular import
import { Component, OnInit } from '@angular/core';
import { CrudService } from 'src/app/theme/shared/services/crudService.service';

// project import
import { SharedModule } from 'src/app/theme/shared/shared.module';

@Component({
  selector: 'app-collapse',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './collapse.component.html',
  styleUrls: ['./collapse.component.scss']
})
export default class CollapseComponent implements OnInit {
  admins:any;
  constructor(private crudService:CrudService){}

  ngOnInit(): void {
      this.crudService.getAdmins().subscribe(
        res=>{
          console.log(res);
          this.admins=res;
        }
      )
  }
  
}
