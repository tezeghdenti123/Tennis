package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Stade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int num_stade;
    private Float prix;
    @OneToMany(mappedBy = "stade", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "sta_res")
    private List<Reservation> reservationList ;
    @OneToMany(mappedBy = "stade", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "stat_stade")
    private List<Statistics> statisticsList;
    public int getNum_stade() {
        return num_stade;
    }

    public List<Statistics> getStatisticsList() {
        return statisticsList;
    }

    public void setStatisticsList(List<Statistics> statisticsList) {
        this.statisticsList = statisticsList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public void setNum_stade(int num_stade) {
        this.num_stade = num_stade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
