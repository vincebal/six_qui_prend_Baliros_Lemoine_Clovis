package com.example.fx.mechanic;

import com.example.fx.AI.Intelligence_Artificielle;
import com.example.fx.method;
import com.example.fx.object.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.fx.joueurs.joueurs.*;
import static com.example.fx.mechanic.Method.*;
import static com.example.fx.mechanic.tour.game;
import static com.example.fx.object.Card.cartes;

public class Start {
    public static void regles(){
        method.printTitle("Regles du jeu :");
        System.out.println(
                "Nombre de joueurs : 2 à 10 joueurs\n" +
                        "\n" +
                        "Le ut du jeu est d'éviter de ramasser des têtes de bœufs présentent sur les cartes. Le joueur ayant ramassé le moins de ses têtes gagne la partie.\n" +
                        "\n" +
                        "Comment le jeuse passe-t-il? :\n" +
                        "\n" +
                        "1 . Chaque joueur doit recevoir 10 cartes.\n" +
                        "2 . On place quatre cartes au centre de la table pour commencer le jeu(ce sera notre plateau).\n" +
                        "3 . Chaque joueur choisi une carte de sa main et la placent face cachée sur la table.\n" +
                        "4 . Lorsque tous les joueurs ont choisi leur carte, ils la révèlent en même temps.\n" +
                        "5 . On place alors les cartes sur la table par  ordre croissant en respectant les règles suivantes :\n" +
                        "\n\n     - Les cartes doivent être placées sur la même ligne que la carte qui a la plus petite valeur et doit être inférieure à la carte la plus grande.\n" +
                        "\n     - Si une carte est supérieure à toutes les cartes sur la table, elle doit commencer une nouvelle ligne.\n" +
                        "\n     - Si une carte est égale à une ou plusieurs cartes sur la table, elle doit être placée à droite de ces cartes.\n\n" +
                        "6 . LOrsqu'un joueur est contraint de placer sa carte sur une colomme qui compte déjà 6 cartes, il devra ramasser les cartes de la colomme et donc les taureaux.\n" +
                        "7 . On recommence ce processus jusqu'à ce que les joueurs n'ai plus de cartes.\n" +
                        "8 . La partie est maintenant finie, il ne reste plus qu'à savoir qui à le moins de têtes de taureaux! \n\n");
        method.enterContinue();
        method.clearConsole();
    }

    public static void start(){

        Random random = new Random();
        Card.cart();
        // Mélanger les cartes
        Collections.shuffle(cartes);//MELANGER

        System.out.println("Nombre de joueur :");
        int nbr_joueur= sc.nextInt();
        System.out.println("voulez-vous intégrer une IA à la partie? :" +
                "1. yes       2. no");
        int ia=method.scInt("->",2);


        // Distribuer les cartes aux joueurs
        for (int i = 0; i < nbr_joueur; i++) {
            main = new ArrayList<>();
            trash = new ArrayList<>();
            joueurs.add(main);
            joueursPli.add(trash);
            Intelligence_Artificielle.pliJoueurV.add(Intelligence_Artificielle.trashV);
        }
        if (ia==1){
            joueurs.add(Intelligence_Artificielle.mainordi);
            joueursPli.add(Intelligence_Artificielle.orditrash);
            Intelligence_Artificielle.pliJoueurV.add(Intelligence_Artificielle.orditrashV);
        }

        for (int j = 0; j < 10; j++) {// DISTRIBUTION des cartes
            method.printLine(20);
            for (int i = 0; i <=joueurs.size()-1; i++) {
                joueurs.get(i).add(cartes.get(0));
                cartes.remove(cartes.get(0));
                show(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            method.printLine(20);
            method.clearConsole();
        }
        Intelligence_Artificielle.restCarte();
        for (int i = 0; i <joueurs.size() ; i++) {
            show(i);
        }
        System.out.println();

    }
    public static void GameLogic(){
        // Jouer au  jeu
        init();
        while (joueurs.get(joueurs.size()-1).size()!=0) {
            game();
        }
        List<Integer> Score = new ArrayList<>();;
        for (int i = 0;i<joueurs.size();i++){
            int point = 0;
            for (int j = 0; j<joueursPli.get(i).size();j++){
                point += joueursPli.get(i).get(j).getNbrTaureau();
            }
            Score.add(point);
            System.out.println("Nombre de taureau joueur numéro " + i + " : " + point);
        }
        method.enterContinue();
        method.clearConsole();
        method.printLine(40);
        afficher_plus_petit(Score);
        afficher_plus_grand(Score);
        method.printLine(40);

    }
}
