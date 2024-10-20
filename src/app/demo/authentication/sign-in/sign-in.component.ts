// angular import
import { Component, OnInit } from '@angular/core';
import { FormBuilder, UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CrudService } from 'src/app/theme/shared/services/crudService.service';

// project import
import { SharedModule } from 'src/app/theme/shared/shared.module';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [SharedModule, RouterModule],
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export default class SignInComponent implements OnInit {
  username:any
  password:any;
  signInForm!:UntypedFormGroup;
  constructor(private formBuilder: UntypedFormBuilder,private crudService:CrudService,private router:Router){}

  ngOnInit(): void {
    this.signInForm=this.formBuilder.group(
      {
        username: ['', [Validators.required]],
        password: ['', [Validators.required]]
      }
    )
    
  }

  signIn(){
    this.signInForm.patchValue({
      username:this.username,
      password:this.password
    })

    this.crudService.signIn(this.signInForm.value).subscribe(
      res=>{
        console.log(res);
        this.pushUser(res)
        this.router.navigateByUrl('analytics')
        
      }
    )
  }


  pushUser(user:any){
    localStorage.setItem('user', JSON.stringify(user));
  }

}
