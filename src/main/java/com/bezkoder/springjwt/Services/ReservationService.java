package com.bezkoder.springjwt.Services;

import com.bezkoder.springjwt.DTO.Filter;
import com.bezkoder.springjwt.DTO.ReservationDTO;
import com.bezkoder.springjwt.mappers.ReservationMapper;
import com.bezkoder.springjwt.models.*;
import com.bezkoder.springjwt.repository.CoachRepository;
import com.bezkoder.springjwt.repository.GroupeRepository;
import com.bezkoder.springjwt.repository.ReservationRepository;
import com.bezkoder.springjwt.repository.StadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
public class ReservationService {
    @Autowired
    private GroupeRepository groupeRepository;
    @Autowired
    private StadeRepository stadeRepository;
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public ResponseEntity<?> saveReservation(Reservation reservation) {
        if(isCoachFree(reservation)==false){
            return ResponseEntity.ok("Coach Not Free!");
        }
        else if (isGroupeFree(reservation)==false){
            return ResponseEntity.ok("Groupe Not Free!");
        }
        else if(isStadeFree(reservation)==false){
            return ResponseEntity.ok("Stade Not Free!");
        }
        reservationRepository.save(reservation);
        return  ResponseEntity.ok("Saved!");
    }

    boolean isCoachFree(Reservation reservation){
        Long coachId=reservation.getCoach().getId();
        if(coachRepository.existsById(coachId)){
            Coach coach=coachRepository.findById(coachId).orElseThrow();
            List<Reservation> reservationList=coach.getReservationList();
            return isValidReservation(reservationList,reservation);
        }
        return false;
    }

    boolean isStadeFree(Reservation reservation){
        Long stadeId=reservation.getStade().getId();
        if(stadeRepository.existsById(stadeId)){
            Stade stade=stadeRepository.findById(stadeId).orElseThrow();
            List<Reservation> reservationList=stade.getReservationList();
            return isValidReservation(reservationList,reservation);
        }

        return false;
    }

    boolean isGroupeFree(Reservation reservation){
        Long groupeId=reservation.getGroupe().getId();
        if(groupeRepository.existsById(groupeId)){
            Groupe groupe=groupeRepository.findById(groupeId).orElseThrow();
            List<Reservation> reservationList=groupe.getReservationList();
            return isValidReservation(reservationList,reservation);
        }
        return false;
    }
    boolean isValidReservation(List<Reservation>reservationList,Reservation reservation){
        for(int i=0;i< reservationList.size();i++){
            if(isReservationsCoincid(reservation,reservationList.get(i))==true){
                return false;
            }
        }
        return true;
    }
    boolean isReservationsCoincid(Reservation reservationA,Reservation reservationB){
        if(!reservationA.getDate().isEqual(reservationB.getDate())){
            return false;
        }
        LocalTime debutA=reservationA.getHeure_deb();
        LocalTime finA=reservationA.getHeure_fin();
        LocalTime debutB=reservationB.getHeure_deb();
        LocalTime finB=reservationB.getHeure_fin();
        return (finB.isAfter(debutA)&&(finB.isBefore(finA)||finB.equals(finA)))||(finA.isAfter(debutB)&&(finA.isBefore(finB)||finB.equals(finA)));
    }


    public List<ReservationDTO> getCurrentReservations() {
        LocalDate today=LocalDate.now();
        List<Reservation> reservationList=reservationRepository.findAll();
        List<ReservationDTO>currentReservationList=new ArrayList<ReservationDTO>();
        for(int i=0;i<reservationList.size();i++){
            LocalDate reservationdate=reservationList.get(i).getDate();
            Reservation reservation=reservationList.get(i);
            if(reservationdate.isEqual(today)){
                ReservationDTO reservationDTO= toDTO(reservation);
                currentReservationList.add(reservationDTO);
            }
        }
        currentReservationList.sort(Comparator.comparing(ReservationDTO::getHeure_deb));
        return currentReservationList;
    }
    ReservationDTO toDTO(Reservation reservation){
        ReservationDTO reservationDTO= new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setDate(reservation.getDate());
        reservationDTO.setHeure_deb(reservation.getHeure_deb());
        reservationDTO.setHeure_fin(reservation.getHeure_fin());
        Admin admin=new Admin();
        admin.setId(reservation.getAdmin().getId());
        admin.setName(reservation.getAdmin().getName());
        reservationDTO.setAdmin(admin);
        Stade stade=reservation.getStade();
        stade.setReservationList(null);
        Coach coach=new Coach();
        coach.setId(reservation.getCoach().getId());
        coach.setName(reservation.getCoach().getName());
        reservationDTO.setCoach(coach);
        reservationDTO.setStade(stade);
        Groupe groupe=new Groupe();
        groupe.setId(reservation.getGroupe().getId());
        groupe.setName(reservation.getGroupe().getName());
        reservationDTO.setGroupe(groupe);
        return reservationDTO;
    }

    public List<ReservationDTO> getReservationsByFilter(Filter filter) {
        LocalDate today=LocalDate.now();
        List<Reservation> reservationList=reservationRepository.findAll();
        List<ReservationDTO>currentReservationList=new ArrayList<ReservationDTO>();
        reservationList=filterByAdminId(filter.getAdminId(),reservationList);
        reservationList=filterByCoachId(filter.getCoachId(),reservationList);
        reservationList=filterByGroupeId(filter.getGroupeId(),reservationList);
        reservationList=filterByStadeId(filter.getStadeId(), reservationList);
        reservationList=filterBydate(filter.getDate(),reservationList);
        for(int i=0;i<reservationList.size();i++){
            LocalDate reservationdate=reservationList.get(i).getDate();
            Reservation reservation=reservationList.get(i);
            ReservationDTO reservationDTO= toDTO(reservation);
            currentReservationList.add(reservationDTO);

        }
        currentReservationList.sort(Comparator.comparing(ReservationDTO::getHeure_deb));
        return currentReservationList;
    }
    List<Reservation> filterByAdminId(Long adminId,List<Reservation> reservationList){
        if(adminId==null){
            return reservationList;
        }
        List<Reservation>newReservationList=new ArrayList<Reservation>();
        for(int i=0;i<reservationList.size();i++){
            if(reservationList.get(i).getAdmin().getId()==adminId){
                newReservationList.add(reservationList.get(i));
            }
        }
        return newReservationList;
    }
    List<Reservation> filterByCoachId(Long coachId,List<Reservation> reservationList){
        if(coachId==null){
            return reservationList;
        }
        List<Reservation>newReservationList=new ArrayList<Reservation>();
        for(int i=0;i<reservationList.size();i++){
            if(reservationList.get(i).getCoach().getId()==coachId){
                newReservationList.add(reservationList.get(i));
            }
        }
        return newReservationList;
    }
    List<Reservation> filterByGroupeId(Long groupeId,List<Reservation> reservationList){
        if(groupeId==null){
            return reservationList;
        }
        List<Reservation>newReservationList=new ArrayList<Reservation>();
        for(int i=0;i<reservationList.size();i++){
            if(reservationList.get(i).getGroupe().getId()==groupeId){
                newReservationList.add(reservationList.get(i));
            }
        }
        return newReservationList;
    }
    List<Reservation> filterByStadeId(Long stadeId,List<Reservation> reservationList){
        if(stadeId==null){
            return reservationList;
        }
        List<Reservation>newReservationList=new ArrayList<Reservation>();
        for(int i=0;i<reservationList.size();i++){
            if(reservationList.get(i).getStade().getId()==stadeId){
                newReservationList.add(reservationList.get(i));
            }
        }
        return newReservationList;
    }
    List<Reservation> filterBydate(LocalDate date,List<Reservation> reservationList){
        if(date==null){
            return reservationList;
        }
        List<Reservation>newReservationList=new ArrayList<Reservation>();
        for(int i=0;i<reservationList.size();i++){
            if(reservationList.get(i).getDate().isEqual(date)){
                newReservationList.add(reservationList.get(i));
            }
        }
        return newReservationList;
    }

    public List<ReservationDTO> getReservationsByWeek() {
        List<Reservation> reservationList=reservationRepository.findAll();

        List<ReservationDTO>currentReservationList=new ArrayList<ReservationDTO>();
        for(int i=0;i<reservationList.size();i++){
            if(isCurentWeek(reservationList.get(i).getDate())){
                ReservationDTO reservationDTO=toDTO(reservationList.get(i));
                currentReservationList.add(reservationDTO);
            }
        }
        return currentReservationList;
    }


    public List<ReservationDTO> getReservationsByWeekAndStadeId(Long stadeId) {
        List<Reservation> reservationList=reservationRepository.findAll();
        List<ReservationDTO>currentReservationList=new ArrayList<ReservationDTO>();
        for(int i=0;i<reservationList.size();i++){
            System.out.println(reservationList.get(i).getDate());
            System.out.println(isCurentWeek(reservationList.get(i).getDate()));
            if(isCurentWeek(reservationList.get(i).getDate())&&(reservationList.get(i).getStade().getId()==stadeId)){
                ReservationDTO reservationDTO=toDTO(reservationList.get(i));
                currentReservationList.add(reservationDTO);
            }
        }
        return currentReservationList;
    }


    boolean isCurentWeek(LocalDate date){
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Define the week fields (you can change Locale if needed)
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        // Get the week of the year for the current date and the input date
        int currentWeek = currentDate.get(weekFields.weekOfWeekBasedYear());
        int inputWeek = date.get(weekFields.weekOfWeekBasedYear());

        // Check if both dates are in the same year and the same week
        return currentWeek == inputWeek && currentDate.getYear() == date.getYear();
    }
}
