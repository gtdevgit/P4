package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Collaborateur;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.model.Salle;

import java.util.Date;
import java.util.List;

/**
 * Reunion API service
 */
public interface ReunionApiService {
    List<Salle> getSalles();
    List<String> getCollaborateurs();
    List<Reunion> getReunions();

    void filtrerReunionsParSalle(long idSalle);
    void filtrerReunionsParDate(Date date);
    void supprimerFiltreReunion();

    int nbReunionParSalle(long idSalle);
    Salle findSalleById(long id);
    void deleteReunion(Reunion reunion);
    long ajouterReunion(Reunion reunion);
}
