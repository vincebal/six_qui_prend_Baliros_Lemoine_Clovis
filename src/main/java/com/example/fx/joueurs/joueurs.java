package com.example.fx.joueurs;

import com.example.fx.object.Card;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class joueurs {

    /**
     * La liste des cartes de la main du joueur.
     */
    public static List<Card> main;

    /**
     * La liste des Parents des éléments de l'interface utilisateur associés à la main du joueur.
     */
    public static List<Parent> mainFx;

    /**
     * La liste des cartes dans la poubelle.
     */
    public static List<Card> bin;

    /**
     * La liste des joueurs.
     */
    public static List<List<Card>> joueurs = new ArrayList<>();

    /**
     * La liste de liste des cartes des joueurs.
     */
    public static List<List<Card>> joueursPli = new ArrayList<>();
}


