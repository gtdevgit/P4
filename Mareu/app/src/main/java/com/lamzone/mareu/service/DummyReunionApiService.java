package com.lamzone.mareu.service;

import android.util.Log;

import com.lamzone.mareu.model.Collaborateur;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.model.Salle;

import java.util.List;

public class DummyReunionApiService implements ReunionApiService{

    private static final String TAG = "DummyReunionApiService";

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
    public void deleteReunion(Reunion reunion) {
        Log.d(TAG, "deleteReunion() called with: reunion = [" + reunion + "]");
        reunions.remove(reunion);
        Log.d(TAG, "deleteReunion() reunions.size() = [" + reunions.size() + "]");
    }
}
