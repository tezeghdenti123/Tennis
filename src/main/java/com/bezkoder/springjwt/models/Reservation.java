package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime heure_deb;
    private LocalTime heure_fin;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonBackReference(value = "adm_res")
    private Admin admin;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference(value = "client_res")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "stade_id")
    @JsonBackReference(value = "sta_res")
    private Stade stade;
    @ManyToOne
    @JoinColumn(name = "groupe_id")
    @JsonBackReference(value = "gpe_res")
    private Groupe groupe;
    @ManyToOne
    @JoinColumn(name = "coach_id")
    @JsonBackReference(value = "coa_res")
    private Coach coach;

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public LocalDate getDate() {
        return date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Stade getStade() {
        return stade;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
    }

    public LocalTime getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(LocalTime heure_fin) {
        this.heure_fin = heure_fin;
    }

    public LocalTime getHeure_deb() {
        return heure_deb;
    }

    public void setHeure_deb(LocalTime heure_deb) {
        this.heure_deb = heure_deb;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
