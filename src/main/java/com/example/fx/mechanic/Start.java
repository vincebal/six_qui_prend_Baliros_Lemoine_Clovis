package com.example.fx.mechanic;

import com.example.fx.AI.Intelligence_Artificielle;
import com.example.fx.HelloApplication;
import com.example.fx.method;
import com.example.fx.object.Card;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.fx.joueurs.joueurs.*;
import static com.example.fx.mechanic.Method.*;
import static com.example.fx.mechanic.tour.game;
import static com.example.fx.object.Card.cartes;

public class Start {
    private static Stage primaryStage;
    public static int ia;

    public Start(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static void regle() {
        method.printTitle("Regle du jeu :");
        String regles =
                "Nombre de joueurs : 2 à 10 joueurs\n" +
                        "\n" +
                        "But du jeu : Éviter de ramasser des têtes de bœufs (représentées par des cartes) qui ont une valeur en points. Le joueur avec le moins de points à la fin de la partie gagne.\n" +
                        "\n" +
                        "Déroulement du jeu :\n" +
                        "\n" +
                        "1. Distribuez 10 cartes à chaque joueur.\n" +
                        "2. Placez quatre cartes au centre de la table pour commencer le jeu.\n" +
                        "3. Les joueurs choisissent une carte de leur main et la placent face cachée sur la table.\n" +
                        "4. Lorsque tous les joueurs ont choisi leur carte, ils la révèlent simultanément.\n" +
                        "5. Les cartes sont alors placées sur la table en ordre croissant (de la plus petite à la plus grande) en respectant les règles suivantes :\n\n" +
                        "   - Les cartes doivent être placées sur la même ligne que la carte qui a la plus petite valeur et doit être inférieure à la carte la plus grande.\n\n" +
                        "   - Si une carte est supérieure à toutes les cartes sur la table, elle doit commencer une nouvelle ligne.\n\n" +
                        "   - Si une carte est égale à une ou plusieurs cartes sur la table, elle doit être placée à droite de ces cartes.\n\n" +
                        "6. Si un joueur doit placer sa carte sur une ligne qui a déjà six cartes, il doit prendre la ligne complète et toutes les cartes de la ligne sont ajoutées à sa pile de têtes de bœufs.\n" +
                        "7. Les joueurs répètent ce processus pour chaque tour jusqu'à ce que toutes les cartes de la main de chaque joueur soient jouées.\n" +
                        "8. La partie se termine lorsque la dernière carte est jouée. Les joueurs comptent alors les points de leurs têtes de bœufs. Chaque tête de bœuf vaut de 1 à 7 points, selon le numéro de la carte. Le joueur avec le moins de points gagne la partie.\n\n";

        method.enterContinue();
        method.clearConsole();
    }

    public static void start() throws IOException {
        Random random = new Random();
        // Mélanger les cartes
        Collections.shuffle(cartes);
        for (int i = 0; i < nbr_joueur; i++) {
            main = new ArrayList<>();
            mainFx = new ArrayList<>();
            bin = new ArrayList<>();
            joueurs.add(main);
            joueursPli.add(bin);
            Intelligence_Artificielle.pliJoueurV.add(Intelligence_Artificielle.trashV);
        }
        if (ia == 1) {
            joueurs.add(Intelligence_Artificielle.mainordi);
            joueursPli.add(Intelligence_Artificielle.orditrashV);
            Intelligence_Artificielle.pliJoueurV.add(Intelligence_Artificielle.mainordi);
        }

        for (int j = 0; j < 10; j++) {
            method.printLine(20);
            for (int i = 0; i <= joueurs.size() - 1; i++) {
                joueurs.get(i).add(cartes.get(0));
                cartes.remove(cartes.get(0));
                show(i);

            }
            method.printLine(20);
            method.clearConsole();
        }
        Intelligence_Artificielle.restCarte();

        System.out.println();
    }

    public static void GameLogic() {
        Random random = new Random();
        // Jouer au  jeu
        init();
        while (joueurs.get(joueurs.size() - 1).size() != 0) {
            game();
        }
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < joueurs.size(); i++) {
            int point = 0;
            for (int j = 0; j < joueursPli.get(i).size(); j++) {
                point += joueursPli.get(i).get(j).getNbrTaureau();
            }
            scores.add(point);
            System.out.println("Nombre de taureaux pour le joueur " + i + " : " + point);
        }
        method.enterContinue();
        method.clearConsole();
        method.printLine(40);
        afficherElementPlusPetit(scores);
        afficherElementPlusGrand(scores);
        method.printLine(40);
    }

    private static void showAlert(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(primaryStage);
            alert.setTitle("Regle du jeu");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}