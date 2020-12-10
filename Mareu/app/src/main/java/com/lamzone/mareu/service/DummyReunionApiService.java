package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Collaborateur;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.model.Salle;

import java.util.List;

public class DummyReunionApiService implements ReunionApiService{

    private List<Salle> salles = DummyGenerator.generateSalles();
    private List<Collaborateur> collaborateurs = DummyGenerator.generateCollaborateurs();
    private List<Reunion> reunions = DummyGenerator.generateReunions();

    @Override
    public List<Salle> getSalles() {
        return salles;
    }

    @Override
    public List<Collaborateur> getCollaborateurs() {
        return collaborateurs;
    }

    @Override
    public List<Reunion> getReunions() {
        return reunions;
    }

    @Override
    public Salle findSalleById(long id) {
        for (Salle salle : salles) {
            if (salle.getId() == id) {
                return salle;
            }
        }
        return null;
    }

    @Override
    public Collaborateur findCollaborateurById(long id) {
        for (Collaborateur collaborateur : collaborateurs) {
            if (collaborateur.getId() == id) {
                    return collaborateur;
            }
        }
        return null;
    }
}
