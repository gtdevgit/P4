package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Collaborateur;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.model.Salle;

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

    List<Collaborateur> getCollaborateurs();

    /**
     * Liste des réunions
     * @return {@link List}
     */
    List<Reunion> getReunions();

    Salle findSalleById(long id);
    Collaborateur findCollaborateurById(long id);

}
