import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup, UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { CrudService } from 'src/app/theme/shared/services/crudService.service';

// project import
import { SharedModule } from 'src/app/theme/shared/shared.module';
@Component({
  selector: 'app-ajouter',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './ajouter.component.html',
  styleUrl: './ajouter.component.scss'
})
export class AjouterComponent implements OnInit{
  userForm!:UntypedFormGroup;
  roles=['admin','client','coach'];
  groupes:any
  groupe:any
  constructor(private formBuilder:UntypedFormBuilder,private crudService:CrudService){}
  ngOnInit(): void {
    this.crudService.getGroups().subscribe(
      res=>{
        this.groupes=res
      }
    )    
    this.userForm=this.formBuilder.group(
      {
        name: ['', [Validators.required]],
        password: ['', [Validators.required]],
        username: ['', [Validators.required]],
        email:['',[Validators.required]],
        phone: ['', [Validators.required]],
        salary: ['', [Validators.required]]
      }
    )
  }

  saveUser(){
    

    this.crudService.saveCoach(this.userForm.value).subscribe(
      res=>{
        console.log(res);
        location.reload()
      }
      
    )
    console.log(this.userForm.value);
    
  }

  // Helper function to get the FormArray for roles
  get groupeFormArray(): FormArray {
    return this.userForm.get('role') as FormArray;
  }

  // Function to patch roles
  patchRoles(roles: string[]) {
    // Clear existing values
    this.groupeFormArray.clear();

    // Patch new values
    roles.forEach(role => {
      this.groupeFormArray.push(this.formBuilder.control(role));
    });
  }

  onSelectionChange(data:any){
    const selected = (data.target as HTMLSelectElement).value;
    this.patchRoles([selected])
  }

}
