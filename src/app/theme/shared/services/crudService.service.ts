import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'currentUser';

@Injectable({
  providedIn: 'root'
})

export class CrudService{
    private springapiUrl = 'http://localhost:8080/api/v1';
    constructor(private http:HttpClient){}

    signIn(signIn:any){
        return this.http.post(`${this.springapiUrl}/auth/signin`,signIn)
    }

    saveUser(user:any){
      return this.http.post(`${this.springapiUrl}/auth/signup`,user,{ responseType: 'text' })
    }
    getReservations(){
      return this.http.get(`${this.springapiUrl}/reservations`)
    }
    getReservationsWeek(){
      return this.http.get<any[]>(`${this.springapiUrl}/reservations/week`)
    }
    getReservationsWeekAndStadeId(stadeId:any){
      return this.http.get<any[]>(`${this.springapiUrl}/stade/reservations/week?stadeId=`+stadeId)
    }
    getClients(){
      return this.http.get(`${this.springapiUrl}/clients`)
    }

    getUsers(){
      return this.http.get(`${this.springapiUrl}/users`)
    }
    getGroups(){
      return this.http.get(`${this.springapiUrl}/groupes`)
    }
    getCoachs(){
      return this.http.get(`${this.springapiUrl}/coachs`)
    }

    saveClient(client:any){
      return this.http.post(`${this.springapiUrl}/client`,client,{ responseType: 'text' })
    }
    saveCoach(coach:any){
      return this.http.post(`${this.springapiUrl}/coach`,coach,{ responseType: 'text' })
    }
    saveAdmin(admin:any){
      return this.http.post(`${this.springapiUrl}/admin`,admin,{ responseType: 'text' })
    }
    saveGroupe(groupe:any){
      return this.http.post(`${this.springapiUrl}/groupe`,groupe,{ responseType: 'text' })
    }
    getAdmins(){
      return this.http.get(`${this.springapiUrl}/admins`)
    }
    getStades(){
      return this.http.get(`${this.springapiUrl}/stades`)
    }
    saveReservation(reservation:any){
      return this.http.post(`${this.springapiUrl}/groupe/reservation`,reservation,{ responseType: 'text' })
    }
}