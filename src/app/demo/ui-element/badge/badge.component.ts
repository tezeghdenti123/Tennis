// angular import
import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup, UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { CrudService } from 'src/app/theme/shared/services/crudService.service';

// project import
import { SharedModule } from 'src/app/theme/shared/shared.module';

@Component({
  selector: 'app-badge',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './badge.component.html',
  styleUrls: ['./badge.component.scss']
})
export default class BadgeComponent implements OnInit{
  userForm!:UntypedFormGroup;
  roles=['admin','client','coach'];
  roleName:any;
  constructor(private formBuilder:UntypedFormBuilder,private crudService:CrudService){}
  ngOnInit(): void {
    this.userForm=this.formBuilder.group(
      {
        name: ['', [Validators.required]],
        password: ['', [Validators.required]],
        username: ['', [Validators.required]],
        email:['',[Validators.required]],
        phone: ['', [Validators.required]],
        role: this.formBuilder.array([], [Validators.required])
      }
    )
  }

  saveUser(){
    console.log(this.userForm.value);
    
    
    this.crudService.saveAdmin(this.userForm.value).subscribe(
      res=>{
        console.log(res);
        location.reload()
      }
    )
  }

  // Helper function to get the FormArray for roles
  get roleFormArray(): FormArray {
    return this.userForm.get('role') as FormArray;
  }

  // Function to patch roles
  patchRoles(roles: string[]) {
    // Clear existing values
    this.roleFormArray.clear();

    // Patch new values
    roles.forEach(role => {
      this.roleFormArray.push(this.formBuilder.control(role));
    });
  }

  onSelectionChange(data:any){
    const selected = (data.target as HTMLSelectElement).value;
    this.patchRoles([selected])
  }

}
