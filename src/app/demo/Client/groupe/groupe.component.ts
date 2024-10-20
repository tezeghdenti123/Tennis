import { Component, OnInit } from '@angular/core';
import { CrudService } from 'src/app/theme/shared/services/crudService.service';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';

// project import
import { SharedModule } from 'src/app/theme/shared/shared.module';
@Component({
  selector: 'app-groupe',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './groupe.component.html',
  styleUrl: './groupe.component.scss'
})
export class GroupeComponent implements OnInit {
  groupes:any
  modalRef?: BsModalRef;
  submitted:any
  groupForm!:UntypedFormGroup;
  constructor(private modalService: BsModalService, private formBuilder: UntypedFormBuilder,private crudService:CrudService){

  }
  ngOnInit(): void {
    this.groupForm= this.formBuilder.group({
      id: [''],
      name: ['', [Validators.required]],
    });
    this.crudService.getGroups().subscribe(
      res=>{
        console.log(res);
        this.groupes=res
        
      }
    )
      
  }

  openModal(content: any) {
    this.submitted = false;
    this.modalRef = this.modalService.show(content, { class: 'modal-md' });
  }
  saveGroupe(){
    console.log(this.groupForm.value);
    this.crudService.saveGroupe(this.groupForm.value).subscribe(
      res=>{
        console.log(res);
        location.reload()
        
      }
    )
    
  }
}
