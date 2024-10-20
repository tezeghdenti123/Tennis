package com.bezkoder.springjwt.DTO;

import com.bezkoder.springjwt.models.Coach;
import com.bezkoder.springjwt.models.Groupe;
import com.bezkoder.springjwt.models.Reservation;
import com.bezkoder.springjwt.models.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PublicClientDTO {
    private Long id;
    private String name;
    private String phone;
    private String username;

    private String email;


    private Set<Role> roles = new HashSet<>();
    private Groupe groupe;
    private List<Reservation> reservationList ;


    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }



}
