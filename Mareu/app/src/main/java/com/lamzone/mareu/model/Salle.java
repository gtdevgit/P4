package com.lamzone.mareu.model;

import android.graphics.Color;

public class Salle {
    private Long id;
    private String nom;
    private int color;

    public Salle(Long id, String nom, int color) {
        this.id = id;
        this.nom = nom;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
