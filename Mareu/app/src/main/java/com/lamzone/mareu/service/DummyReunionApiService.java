package com.lamzone.mareu.service;

import android.util.Log;

import com.lamzone.mareu.model.Collaborateur;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.model.Salle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DummyReunionApiService implements ReunionApiService{
    private List<Salle> salles = DummyGenerator.generateSalles();
    private List<String> collaborateurs = DummyGenerator.generateCollaborateurs();
    private List<Reunion> reunions = DummyGenerator.generateReunions();

    @Override
    public List<Salle> getSalles() {
        return salles;
    }

    @Override
    public List<String> getCollaborateurs() {
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
    public void filtrerReunionsParSalle(long idSalle) {
        reunions.clear();
        List<Reunion> r = DummyGenerator.generateReunions();
        for (int i=0; i<r.size(); i++){
            Reunion reunion = r.get(i);
            if (reunion.getIdSalle() == idSalle) {
                reunions.add(reunion);
            }
        }
    }

    @Override
    public void filtrerReunionsParDate(Date date){
        reunions.clear();
        List<Reunion> r = DummyGenerator.generateReunions();
        for (int i=0; i<r.size(); i++){
            Reunion reunion = r.get(i);
            if (reunion.getDateDebut().compareTo(date) == 0) {
                reunions.add(reunion);
            }
        }
    }

    @Override
    public void supprimerFiltreReunion(){
        reunions = DummyGenerator.generateReunions();
    }

    @Override
    public int nbReunionParSalle(long idSalle) {
        int count = 0;
        List<Reunion> r = DummyGenerator.generateReunions();
        for (int i=0; i<r.size(); i++){
            Reunion reunion = r.get(i);
            if (reunion.getIdSalle() == idSalle) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }

    @Override
    public long ajouterReunion(Reunion reunion) {
        long id = reunions.size() + 1;
        reunion.setId(id);
        reunions.add(reunion);
        return id;
    }
}
