package com.lamzone.mareu.model;

import java.util.Date;
import java.util.List;

public class Reunion {
    private Long id;
    private String sujet;
    private Date dateDebut;
    private Date dateFin;
    private Long idSalle;
    private List<String> participants;

    public Reunion(Long id, String sujet, Date dateDebut, Date dateFin, Long idSalle, List<String> participants) {
        this.id = id;
        this.sujet = sujet;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idSalle = idSalle;
        this.participants = participants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Long getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(Long idSalle) {
        this.idSalle = idSalle;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String listeParticipantToString(){
        StringBuilder str = new StringBuilder();
        for (String participant : this.participants)
        {
            str.append(participant);
            str.append(", ");
        }
        return str.toString();
    }
}
