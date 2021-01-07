package com.lamzone.mareu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lamzone.mareu.di.DI;
import com.lamzone.mareu.model.Collaborateur;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.model.Salle;
import com.lamzone.mareu.service.DummyGenerator;
import com.lamzone.mareu.service.DummyReunionApiService;
import com.lamzone.mareu.service.ReunionApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class MareuUnitTest {

    @Test
    public void getAllReunions() {
        ReunionApiService service = DI.getNewInstanceApiService();
        List<Reunion> reunions = service.getReunions();
        List<Reunion> expectedReunions = DummyGenerator.generateReunions();
        assertThat(reunions, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedReunions.toArray()));
    }

    @Test
    public void getAllCollaborateurs() {
        ReunionApiService service = DI.getNewInstanceApiService();
        List<String> collaborateurs = service.getCollaborateurs();
        List<String> expectedCollaborateurs = DummyGenerator.generateCollaborateurs();
        assertThat(collaborateurs, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedCollaborateurs.toArray()));
    }

    @Test
    public void getAllSalles() {
        ReunionApiService service = DI.getNewInstanceApiService();
        List<Salle> salles = service.getSalles();
        List<Salle> expectedSalles = DummyGenerator.generateSalles();
        assertThat(salles, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSalles.toArray()));
    }

    @Test
    public void createReunion() {
        List<String> emails = new ArrayList<String>();
        emails.add("george@lamzone.com");
        emails.add("nadiacompta@lamzone.com");
        emails.add("support@lamzone.com");

        Reunion reunion = new Reunion(0L, "Sujet", new Date(), new Date(), 0L, emails);
        assertNotNull(reunion);
    }

    @Test
    public void ajouterReunion() {
        ReunionApiService service = DI.getNewInstanceApiService();
        List<String> emails = new ArrayList<String>();
        emails.add("george@lamzone.com");
        emails.add("nadiacompta@lamzone.com");
        emails.add("support@lamzone.com");

        Reunion reunion = new Reunion(0L, "Sujet", new Date(), new Date(), 0L, emails);
        int firstCount = service.getReunions().size();
        service.ajouterReunion(reunion);
        int secondCount = service.getReunions().size();
        assertEquals(firstCount + 1, secondCount);
    }

    @Test
    public void deleteReunion() {
        ReunionApiService service = DI.getNewInstanceApiService();
        Reunion reunion = service.getReunions().get(0);
        service.deleteReunion(reunion);
        //service.getReunions().remove(reunion);
        assertFalse(service.getReunions().contains(reunion));
    }

    @Test
    public void nbReunionParSalle() {
        ReunionApiService service = DI.getNewInstanceApiService();
        int nb = service.nbReunionParSalle(1L);
        assertEquals(nb, 3);
    }

    @Test
    public void findSalleById()
    {
        ReunionApiService service = DI.getNewInstanceApiService();
        Salle firstSalle = service.getSalles().get(0);
        Salle findSalle = service.findSalleById(1L);
        assertEquals(firstSalle, findSalle);
    }

    // void filtrerReunionsParSalle(long idSalle);
    // void filtrerReunionsParDate(Date date);
    // void supprimerFiltreReunion();
}
