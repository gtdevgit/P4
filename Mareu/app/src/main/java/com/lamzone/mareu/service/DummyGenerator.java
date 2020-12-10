package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Collaborateur;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.model.Salle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class DummyGenerator {

    private static List<Salle> DUMMY_SALLE = Arrays.asList(
            new Salle (1L, "Voltaire"),
            new Salle (2L, "Diderot"),
            new Salle (3L, "Rousseau"),
            new Salle (4L, "Montesquieu"),
            new Salle (5L, "Beaumarchais"),
            new Salle (6L, "D'Alembert"));

    private static List<Collaborateur> DUMMY_COLLABORATEUR = Arrays.asList(
            new Collaborateur(1L, "martine@lamzone.fr" ),
            new Collaborateur(2L, "claire@lamzone.fr" ),
            new Collaborateur(3L, "gabriel@lamzone.fr" ),
            new Collaborateur(4L, "leo@lamzone.fr" ),
            new Collaborateur(5L, "emilien@lamzone.fr" ),
            new Collaborateur(6L, "arthur@lamzone.fr" ),
            new Collaborateur(7L, "octave@lamzone.fr" ),
            new Collaborateur(8L, "chloe@lamzone.fr" ),
            new Collaborateur(9L, "louise@lamzone.fr" ),
            new Collaborateur(10L, "fabien@lamzone.fr" ),
            new Collaborateur(11L, "juliette@lamzone.fr" ),
            new Collaborateur(12L, "olivier@lamzone.fr" ),
            new Collaborateur(13L, "laure@lamzone.fr" ),
            new Collaborateur(14L, "frederic@lamzone.fr" ),
            new Collaborateur(15L, "jennifer@lamzone.fr"),
            new Collaborateur(16L, "guillaume@lamzone.fr"),
            new Collaborateur(17L, "loic@lamzone.fr"));

    private static List<Long> DUMMY_PARTICIPANT_1 = Arrays.asList(
            16L, 17L);
    private static List<Long> DUMMY_PARTICIPANT_2 = Arrays.asList(
            9L, 10L, 11L, 12L, 13L, 14L, 15L);
    private static List<Long> DUMMY_PARTICIPANT_3 = Arrays.asList(
            9L, 10L, 11L, 15L);
    private static List<Long> DUMMY_PARTICIPANT_4 = Arrays.asList(
            5L, 6L, 7L);
    private static List<Long> DUMMY_PARTICIPANT_5 = Arrays.asList(
            2L, 8L);

    private static Date DATE_DEBUT_1 = new GregorianCalendar(2020, Calendar.DECEMBER, 10).getTime();
    private static Date DATE_FIN_1 = new GregorianCalendar(2020, Calendar.DECEMBER, 10).getTime();
    private static Date DATE_DEBUT_2 = new GregorianCalendar(2020, Calendar.DECEMBER, 11).getTime();
    private static Date DATE_FIN_2 = new GregorianCalendar(2020, Calendar.DECEMBER, 11).getTime();
    private static Date DATE_DEBUT_3 = new GregorianCalendar(2020, Calendar.DECEMBER, 12).getTime();
    private static Date DATE_FIN_3 = new GregorianCalendar(2020, Calendar.DECEMBER, 12).getTime();
    private static Date DATE_DEBUT_4 = new GregorianCalendar(2020, Calendar.DECEMBER, 13).getTime();
    private static Date DATE_FIN_4 = new GregorianCalendar(2020, Calendar.DECEMBER, 13).getTime();
    private static Date DATE_DEBUT_5 = new GregorianCalendar(2020, Calendar.DECEMBER, 14).getTime();
    private static Date DATE_FIN_5 = new GregorianCalendar(2020, Calendar.DECEMBER, 14).getTime();

    public static List<Reunion> DUMMY_REUNION = Arrays.asList(
            new Reunion(1L, "Projet Unicorn", DATE_DEBUT_1, DATE_FIN_1, 1L, DUMMY_PARTICIPANT_1),
            new Reunion(2L, "Réunion commerciale", DATE_DEBUT_2, DATE_FIN_2, 2L, DUMMY_PARTICIPANT_2),
            new Reunion(3L, "Robotic Management System", DATE_DEBUT_3, DATE_FIN_3, 3L, DUMMY_PARTICIPANT_3),
            new Reunion(4L, "Evolution site web", DATE_DEBUT_4, DATE_FIN_4, 4L, DUMMY_PARTICIPANT_4),
            new Reunion(5L, "Point comptabilité", DATE_DEBUT_5, DATE_FIN_5, 5L, DUMMY_PARTICIPANT_5));

    public static List<Salle> generateSalles() { return new ArrayList<>(DUMMY_SALLE); }
    public static List<Collaborateur> generateCollaborateurs() { return new ArrayList<>(DUMMY_COLLABORATEUR); }
    public static List<Reunion> generateReunions() { return new ArrayList<>(DUMMY_REUNION); }

}
