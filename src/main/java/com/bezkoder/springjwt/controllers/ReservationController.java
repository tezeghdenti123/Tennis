package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.DTO.Filter;
import com.bezkoder.springjwt.DTO.ReservationDTO;
import com.bezkoder.springjwt.Services.ReservationService;
import com.bezkoder.springjwt.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @PostMapping("/groupe/reservation")
    ResponseEntity<?> saveReservation(@RequestBody Reservation reservation){
        return reservationService.saveReservation(reservation);
    }
    @GetMapping("/reservations")
    List<ReservationDTO>getReservations(){
        return reservationService.getCurrentReservations();
    }
    @PostMapping("/reservations/filter")
    List<ReservationDTO> getReservationsByFilter(@RequestBody Filter filter){
        return reservationService.getReservationsByFilter(filter);
    }

    @GetMapping("/reservations/week")
    List<ReservationDTO> getReservationsByWeek(){
        return reservationService.getReservationsByWeek();
    }
    @GetMapping("/stade/reservations/week")
    List<ReservationDTO> getReservationsByWeekAndStadeId(@RequestParam Long stadeId){
        return reservationService.getReservationsByWeekAndStadeId(stadeId);
    }
}
