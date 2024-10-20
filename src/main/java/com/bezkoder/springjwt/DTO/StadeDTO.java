package com.bezkoder.springjwt.DTO;

import com.bezkoder.springjwt.models.Reservation;
import com.bezkoder.springjwt.models.Statistics;

import java.util.List;

public class StadeDTO {
    private Long id;
    private int num_stade;
    private Float prix;


    public int getNum_stade() {
        return num_stade;
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
