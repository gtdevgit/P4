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

            new Salle (6L, "D'Alembert", 0xffe91e63),
            new Salle (7L, "Bernoulli", 0xff2196f3),
            new Salle (8L, "Euler", 0xff4caf50),
            new Salle (9L, "Laplace", 0xffffc107),
            new Salle (10L, "Lagrange", 0xff9e9e9e),

            new Salle (11L, "Monge", 0xff9c27b0),
            new Salle (12L, "Condorcet", 0xff03a9f4),
            new Salle (13L, "Lavoisier", 0xff8bc34a),
            new Salle (14L, "Buffon", 0xff607d8b)
    );

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
            "guillaume@lamzone.fr",
            "loic@lamzone.fr");
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
            "martine@lamzone.fr",
            "claire@lamzone.fr");
    private static List<String> DUMMY_PARTICIPANT_6 = Arrays.asList(
            "emilien@lamzone.fr",
            "arthur@lamzone.fr",
            "octave@lamzone.fr");
    private static List<String> DUMMY_PARTICIPANT_7 = Arrays.asList(
            "olivier@lamzone.fr",
            "laure@lamzone.fr",
            "frederic@lamzone.fr",
            "jennifer@lamzone.fr");
    private static List<String> DUMMY_PARTICIPANT_8 = Arrays.asList(
            "frederic@lamzone.fr",
            "claire@lamzone.fr",
            "guillaume@lamzone.fr");
    private static List<String> DUMMY_PARTICIPANT_9 = Arrays.asList(
            "arthur@lamzone.fr",
            "martine@lamzone.fr",
            "claire@lamzone.fr");
    private static List<String> DUMMY_PARTICIPANT_10 = Arrays.asList(
            "frederic@lamzone.fr",
            "claire@lamzone.fr",
            "arthur@lamzone.fr",
            "martine@lamzone.fr",
            "claire@lamzone.fr");


    private static Date DATE_DEBUT_1 = new GregorianCalendar(2020, Calendar.DECEMBER, 10, 10, 30).getTime();
    private static Date DATE_FIN_1 = new GregorianCalendar(2020, Calendar.DECEMBER, 10, 12, 0).getTime();
    private static Date DATE_DEBUT_2 = new GregorianCalendar(2020, Calendar.DECEMBER, 11, 9, 0).getTime();
    private static Date DATE_FIN_2 = new GregorianCalendar(2020, Calendar.DECEMBER, 11, 12, 0).getTime();
    private static Date DATE_DEBUT_3 = new GregorianCalendar(2020, Calendar.DECEMBER, 12, 14,0).getTime();
    private static Date DATE_FIN_3 = new GregorianCalendar(2020, Calendar.DECEMBER, 12, 17,0).getTime();
    private static Date DATE_DEBUT_4 = new GregorianCalendar(2020, Calendar.DECEMBER, 13, 15, 30).getTime();
    private static Date DATE_FIN_4 = new GregorianCalendar(2020, Calendar.DECEMBER, 13, 17, 0).getTime();
    private static Date DATE_DEBUT_5 = new GregorianCalendar(2020, Calendar.DECEMBER, 14, 10, 45).getTime();
    private static Date DATE_FIN_5 = new GregorianCalendar(2020, Calendar.DECEMBER, 14, 11, 45).getTime();
    private static Date DATE_DEBUT_6 = new GregorianCalendar(2020, Calendar.DECEMBER, 15, 9, 0).getTime();
    private static Date DATE_FIN_6 = new GregorianCalendar(2020, Calendar.DECEMBER, 15, 17, 0).getTime();
    private static Date DATE_DEBUT_7 = new GregorianCalendar(2020, Calendar.DECEMBER, 16, 11, 0).getTime();
    private static Date DATE_FIN_7 = new GregorianCalendar(2020, Calendar.DECEMBER, 16, 12, 0).getTime();
    private static Date DATE_DEBUT_8 = new GregorianCalendar(2020, Calendar.DECEMBER, 17, 14, 0).getTime();
    private static Date DATE_FIN_8 = new GregorianCalendar(2020, Calendar.DECEMBER, 17, 16, 0).getTime();
    private static Date DATE_DEBUT_9 = new GregorianCalendar(2020, Calendar.DECEMBER, 18, 9, 0).getTime();
    private static Date DATE_FIN_9 = new GregorianCalendar(2020, Calendar.DECEMBER, 18, 12,0 ).getTime();
    private static Date DATE_DEBUT_10 = new GregorianCalendar(2020, Calendar.DECEMBER, 19, 9, 0).getTime();
    private static Date DATE_FIN_10 = new GregorianCalendar(2020, Calendar.DECEMBER, 19, 12, 0).getTime();
    private static Date DATE_DEBUT_11 = new GregorianCalendar(2020, Calendar.DECEMBER, 20, 10, 0).getTime();
    private static Date DATE_FIN_11 = new GregorianCalendar(2020, Calendar.DECEMBER, 20, 12, 0).getTime();
    private static Date DATE_DEBUT_12 = new GregorianCalendar(2020, Calendar.DECEMBER, 21, 14, 0).getTime();
    private static Date DATE_FIN_12 = new GregorianCalendar(2020, Calendar.DECEMBER, 21, 17, 0).getTime();

    public static List<Reunion> DUMMY_REUNION = Arrays.asList(
            new Reunion(1L, "Projet Unicorn", DATE_DEBUT_1, DATE_FIN_1, 1L, DUMMY_PARTICIPANT_1),
            new Reunion(2L, "Réunion commerciale", DATE_DEBUT_2, DATE_FIN_2, 2L, DUMMY_PARTICIPANT_2),
            new Reunion(3L, "Robotic Management System", DATE_DEBUT_3, DATE_FIN_3, 3L, DUMMY_PARTICIPANT_3),
            new Reunion(4L, "Evolution site web", DATE_DEBUT_4, DATE_FIN_4, 4L, DUMMY_PARTICIPANT_4),
            new Reunion(5L, "Point comptabilité", DATE_DEBUT_5, DATE_FIN_5, 9L, DUMMY_PARTICIPANT_5),
            new Reunion(6L, "Projet JetAssembly", DATE_DEBUT_6, DATE_FIN_6, 7L, DUMMY_PARTICIPANT_6),
            new Reunion(7L, "Codir", DATE_DEBUT_7, DATE_FIN_7, 1L, DUMMY_PARTICIPANT_7),
            new Reunion(8L, "Réunion commerciale", DATE_DEBUT_8, DATE_FIN_8, 6L, DUMMY_PARTICIPANT_2),
            new Reunion(9L, "Projet Unicorn", DATE_DEBUT_9, DATE_FIN_9, 7L, DUMMY_PARTICIPANT_1),
            new Reunion(10L, "Robotic Management System", DATE_DEBUT_10, DATE_FIN_10, 8L, DUMMY_PARTICIPANT_3),
            new Reunion(11L, "Projet JetAssembly", DATE_DEBUT_11, DATE_FIN_11, 9L, DUMMY_PARTICIPANT_6),
            new Reunion(12L, "Projet Unicorn", DATE_DEBUT_12, DATE_FIN_12, 7L, DUMMY_PARTICIPANT_1)
    );

    public static List<Salle> generateSalles() { return new ArrayList<>(DUMMY_SALLE); }
    public static List<String> generateCollaborateurs() { return new ArrayList<>(DUMMY_COLLABORATEUR); }
    public static List<Reunion> generateReunions() { return new ArrayList<>(DUMMY_REUNION); }

}
