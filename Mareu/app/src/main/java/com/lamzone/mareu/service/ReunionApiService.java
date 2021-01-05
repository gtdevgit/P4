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

    /**
     * Liste des salles de réunions
     * @return {@link List}
     */
    List<Salle> getSalles();

    /**
     * Liste des collaborateurs
     * @return {@link List}
     */

    List<String> getCollaborateurs();

    /**
     * Liste des réunions
     * @return {@link List}
     */
    List<Reunion> getReunions();
    void filtrerReunionsParSalle(long idSalle);
    void filtrerReunionsParDate(Date date);
    void supprimerFiltreReunion();

    Salle findSalleById(long id);

    void deleteReunion(Reunion reunion);
    String[] arrayNomSalle();

    /**
     * Ajouter une réunion
     * @return {@link long}
     * return l'id de la réunion
     */

    long ajouterReunion(Reunion reunion);

}
