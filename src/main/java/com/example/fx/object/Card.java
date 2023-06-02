package com.example.fx.object;

import com.example.fx.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Card {
    private int Num_card;
    private int NbrTaureau;
    private String Img;
    private static Parent root;
    @FXML
    private static Label numberLU;
    @FXML
    private static Label numberRU;
    @FXML
    private static Label numberLD;
    @FXML
    private static Label numberRD;
    @FXML
    private static Label numberC;
    @FXML
    private static Label numTauU;
    @FXML
    private static Label numTauD;
    public static List<Card> cartes = new ArrayList<>();
    public static List<Scene> cartesFx = new ArrayList<>();
    public static List<Card> AllcarteV = new ArrayList<>();
    public static List<Card> Allcarte = new ArrayList<>();

    /**
     * Constructeur de la classe Card.
     *
     * @param n Numéro de la carte.
     * @param num Nombre de taureaux sur la carte.
     * @param img Nom du fichier image de la carte.
     */
    public Card(int n, int num, String img) {
        Num_card = n;
        NbrTaureau = num;
        Img = img;
    }

    /**
     * Calcule le nombre de taureaux sur une carte en fonction de son numéro.
     *
     * @param Num_card Numéro de la carte.
     * @param i l'indice de la carte dans la liste.
     * @return Nombre de taureaux sur la carte.
     */
    public static int taureau(int Num_card, int i) {
        if (Num_card % 10 == 5) {
            cartes.get(i - 1).NbrTaureau += 2;
        }
        if (Num_card % 10 == 0) {
            cartes.get(i - 1).NbrTaureau += 3;
        }
        if (Num_card % 11 == 0) {
            cartes.get(i - 1).NbrTaureau += 5;
        }
        if (Num_card % 10 != 5 && Num_card % 10 != 0 && Num_card % 11 != 0) {
            cartes.get(i - 1).NbrTaureau += 1;
        }
        return cartes.get(i - 1).NbrTaureau;
    }

    /**
     * Initialise les cartes avec les valeurs de 1 à 104 et calcule le nombre de taureaux sur chaque carte.
     *
     * @param event Événement déclenché par le bouton.
     * @param primaryStage Stage principal de l'application.
     * @return Liste des cartes initialisées.
     * @throws IOException En cas d'erreur lors du chargement de la scène.
     */
    public static List<Card> cart(ActionEvent event, Stage primaryStage) throws IOException {
        // Initialiser les cartes avec les valeurs de 1 à 104
        for (int i = 1; i <= 104; i++) {
            Card card = new Card(i, 0, i + ".png");
            cartes.add(card);
            Allcarte.add(card);
            taureau(cartes.get(i - 1).Num_card, i);
            taureau(Allcarte.get(i - 1).Num_card, i);

            cartes.get(i - 1).setNbrTaureau(cartes.get(i - 1).getNbrTaureau() / 2);

            System.out.println("numcard" + cartes.get(i - 1).getNum_card() + "   nbrtaureau" + cartes.get(i - 1).getNbrTaureau());
        }

        return cartes;
    }
}
