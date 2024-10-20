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
  reservationForm!:UntypedFormGroup;
  roles=['admin','client','coach'];
  groupes:any
  groupe:any
  stades:any;
  coaches:any;
  submitted:any;
  user:any;
  coachNotFree:any
  stadeNotFree:any;
  groupeNotFree:any;
  constructor(private formBuilder:UntypedFormBuilder,private crudService:CrudService){}
  ngOnInit(): void {
    this.coachNotFree=false
    this.stadeNotFree=false
    this.groupeNotFree=false
    let resume=localStorage.getItem('user')
    if(resume!=null){
      this.user= JSON.parse(resume);
    }
    this.submitted=false
    this.crudService.getGroups().subscribe(
      res=>{
        this.groupes=res
      }
    )    
    this.reservationForm=this.formBuilder.group(
      {
        date: ['', [Validators.required]],
        heure_deb:['', [Validators.required]],
        heure_fin:['', [Validators.required]],
        admin:this.formBuilder.group({
          id:['', [Validators.required]]
        }),
        stade:this.formBuilder.group({
          id:['', [Validators.required]]
        }),
        groupe:this.formBuilder.group({
          id:['', [Validators.required]]
        }),
        coach:this.formBuilder.group({
          id:['', [Validators.required]]
        })
      }
    )

    this.crudService.getCoachs().subscribe(
      res=>{
        this.coaches=res
        console.log(this.coaches);
      }
    )
    this.crudService.getStades().subscribe(
      res=>{
        console.log(res);
        this.stades=res
        
      }
    )

  }


  saveAffectation(){
    this.reservationForm.patchValue({
      admin: {
        id:this.user.id
      }
    });
    this.submitted=true
    if(this.reservationForm.valid){
      console.log(this.reservationForm.value);
      this.crudService.saveReservation(this.reservationForm.value).subscribe(
        res=>{
          console.log(res);
          if(res=="Coach Not Free!"){
            this.coachNotFree=true
            this.groupeNotFree=false
            this.stadeNotFree=false
          }
          else if(res=="Groupe Not Free!"){
            this.groupeNotFree=true
            this.coachNotFree=false
            this.stadeNotFree=false
          }
          else if(res=="Stade Not Free!"){
            this.stadeNotFree=true
            this.groupeNotFree=false
            this.coachNotFree=false
          }
          else{
            location.reload()
          }
          
          
        }
      )
      
    }
    
  }

  groupeSelected(){
    this.groupeNotFree=false
  }
  coachSelected(){
    this.coachNotFree=false
  }

  selectStade(){
    this.stadeNotFree=false
  }


}
