// angular import
import { Component, OnInit } from '@angular/core';
import { CrudService } from 'src/app/theme/shared/services/crudService.service';

// project import
import { SharedModule } from 'src/app/theme/shared/shared.module';

interface Event {
  name: string;
  day: string;
  startTime: string;
  endTime: string;
}

@Component({
  selector: 'app-sample-page',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './sample-page.component.html',
  styleUrls: ['./sample-page.component.scss']
})
export default class SamplePageComponent implements OnInit{
  daysOfWeek = ['Hour','Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];
  currentClass:any;
  // Hours in the day, starting from 5:00 AM to 12:00 AM
  hoursInDay = [
    '5:00', '6:00', '7:00', '8:00', '9:00', '10:00', '11:00', '12:00', 
    '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', 
    '21:00', '22:00', '23:00', '24:00'
  ];

  // Sample events with start and end times
  events :Event[] = [];
  reservations:any;
  stades:any;
  constructor(private crudService:CrudService){}
  ngOnInit(): void {
    this.currentClass="event1"
    this.crudService.getStades().subscribe(
      res=>{
        console.log(res);
        this.stades=res
        this.crudService.getReservationsWeekAndStadeId(this.stades[0].id).subscribe(
          (response: any[]) => {
            // Iterate over the response data using forEach
            response.forEach(reservation => {
              console.log(reservation);
              let event={name: reservation.groupe.name+" "+reservation.coach.name, day: this.getDayNameFromLocalDate(reservation.date), startTime: reservation.heure_deb, endTime: reservation.heure_fin}
              console.log(event);
              this.events.push(event)
              
              
            })
          }
        )
      }
    )
    
  }

  onButtonClick(stadeId: any,num_stade:any) {
    this.events = [];
    this.crudService.getReservationsWeekAndStadeId(stadeId).subscribe(
      (response: any[]) => {
        console.log(response);
        
        // Iterate over the response data using forEach
        response.forEach(reservation => {
          console.log(reservation);
          let event={name: reservation.groupe.name+" "+reservation.coach.name, day: this.getDayNameFromLocalDate(reservation.date), startTime: reservation.heure_deb, endTime: reservation.heure_fin}
          console.log(event);
          this.events.push(event)
          
          
        })
      }
    )
      switch (num_stade) {
        case 1:
          this.currentClass= 'event1';
          
          return 'event1'
        case 2:
          this.currentClass='event2';
          return 'event2';
        case 3:
          this.currentClass ='event3';
          return 'event3';
        case 4:
          this.currentClass= 'event4';
          return 'event4';
        default:
          this.currentClass= 'event1';
          return 'event1';
    }
    

  }

  getClass(currentClass:any){
    return currentClass
  }
  getDayNameFromLocalDate(localDate: string): string {
    // Create a Date object from the localDate string
    const date = new Date(localDate);
  
    // Use Intl.DateTimeFormat to get the day name (e.g., 'Monday')
    const dayName = new Intl.DateTimeFormat('en-US', { weekday: 'long' }).format(date);
  
    return dayName;
  }
  isThereEvent(day:any,hour:any){
    for(var i=0;i<this.events.length;i++){
      const eventStartHour = parseInt(this.events[i].startTime.split(':')[0], 10);
      const eventHour = parseInt(hour.split(':')[0], 10);
      if(this.events[i].day === day && eventStartHour === eventHour){
        return true
      }
      
    }
    return false
  }

  isitside(day:any){
    console.log(day);
    return day=='Hour'
    
  }
  getEventsForDayAndHour(day: string, hour: string) {
    // Find events for the same day and hour
    const eventsAtSameTime = this.events.filter(event => {
      const eventStartHour = parseInt(event.startTime.split(':')[0], 10);
      const eventHour = parseInt(hour.split(':')[0], 10);
      return event.day === day && eventStartHour === eventHour;
    });
  
    // Combine the event names into a single string
    let eventNames = eventsAtSameTime.map(event => [event.name,event.startTime,event.endTime]);
  
    // Return the combined names, separated by commas
    return eventNames;
  }
  
}
