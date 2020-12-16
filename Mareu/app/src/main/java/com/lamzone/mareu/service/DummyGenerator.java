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
            new Salle (1L, "Voltaire", 0xfff44336),
            new Salle (2L, "Diderot", 0xff3f51b5),
            new Salle (3L, "Rousseau", 0xff009688),
            new Salle (4L, "Montesquieu", 0xffffeb3b),
            new Salle (5L, "Beaumarchais", 0xff795548),
            new Salle (6L, "D'Alembert", 0xffcddc39));

    private static List<String> DUMMY_COLLABORATEUR = Arrays.asList(
            "martine@lamzone.fr",
            "claire@lamzone.fr" ,
            "gabriel@lamzone.fr",
            "leo@lamzone.fr",
            "emilien@lamzone.fr",
            "arthur@lamzone.fr",
            "octave@lamzone.fr",
            "chloe@lamzone.fr",
            "louise@lamzone.fr",
            "fabien@lamzone.fr",
            "juliette@lamzone.fr",
            "olivier@lamzone.fr",
            "laure@lamzone.fr",
            "frederic@lamzone.fr",
            "jennifer@lamzone.fr",
            "guillaume@lamzone.fr",
            "loic@lamzone.fr");

    private static List<String> DUMMY_PARTICIPANT_1 = Arrays.asList(
            "martine@lamzone.fr",
            "claire@lamzone.fr");
    private static List<String> DUMMY_PARTICIPANT_2 = Arrays.asList(
            "gabriel@lamzone.fr",
            "leo@lamzone.fr",
            "emilien@lamzone.fr",
            "arthur@lamzone.fr",
            "octave@lamzone.fr");
    private static List<String> DUMMY_PARTICIPANT_3 = Arrays.asList(
            "chloe@lamzone.fr",
            "louise@lamzone.fr",
            "fabien@lamzone.fr",
            "juliette@lamzone.fr",
            "olivier@lamzone.fr",
            "laure@lamzone.fr",
            "frederic@lamzone.fr",
            "jennifer@lamzone.fr");
    private static List<String> DUMMY_PARTICIPANT_4 = Arrays.asList(
            "claire@lamzone.fr",
            "leo@lamzone.fr",
            "louise@lamzone.fr",
            "guillaume@lamzone.fr");
    private static List<String> DUMMY_PARTICIPANT_5 = Arrays.asList(
            "guillaume@lamzone.fr",
            "loic@lamzone.fr");

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
            new Reunion(1L, "Projet Unicorn", DATE_DEBUT_1, DATE_FIN_1, 1L, DUMMY_PARTICIPANT_5),
            new Reunion(2L, "Réunion commerciale", DATE_DEBUT_2, DATE_FIN_2, 2L, DUMMY_PARTICIPANT_2),
            new Reunion(3L, "Robotic Management System", DATE_DEBUT_3, DATE_FIN_3, 3L, DUMMY_PARTICIPANT_3),
            new Reunion(4L, "Evolution site web", DATE_DEBUT_4, DATE_FIN_4, 4L, DUMMY_PARTICIPANT_4),
            new Reunion(5L, "Point comptabilité", DATE_DEBUT_5, DATE_FIN_5, 5L, DUMMY_PARTICIPANT_1));

    public static List<Salle> generateSalles() { return new ArrayList<>(DUMMY_SALLE); }
    public static List<String> generateCollaborateurs() { return new ArrayList<>(DUMMY_COLLABORATEUR); }
    public static List<Reunion> generateReunions() { return new ArrayList<>(DUMMY_REUNION); }

}
