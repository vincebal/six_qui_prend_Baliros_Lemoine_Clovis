package com.example.fx;

import com.example.fx.Cardcontroller.ControlleurCarte;
import com.example.fx.Cardcontroller.ControlleurCarte;
import com.example.fx.joueurs.joueurs;
import com.example.fx.mechanic.Method;
import com.example.fx.mechanic.Start;
import com.example.fx.object.Card;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextField joueur;
    @FXML
    private Button suivant;
    @FXML
    private Label alertjoueur;
    @FXML
    private CheckBox ia;
    @FXML
    private HBox main;
    @FXML
    private FlowPane cardpane;
    private Stage primaryStage;
    private Scene scene;
    private Parent root;

    /**
     * Change la scène vers "regle.fxml".
     *
     * @param event Evènement lors du clic.
     * @throws IOException En cas d'erreur lors du chargement de la scène.
     */
    public void switchScene(ActionEvent event) throws IOException {
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        switchSceneall("regle.fxml");
    }

    /**
     * Revient à la scène précédente.
     *
     * @param event Evènement lors du clic.
     * @throws IOException En cas d'erreur lors du chargement de la scène.
     */
    public void sceneback(ActionEvent event) throws IOException {
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        switchSceneall("start.fxml");
    }

    /**
     * Change la scène vers "joueur.fxml".
     *
     * @param event Evènement lors du clic.
     * @throws IOException En cas d'erreur lors du chargement de la scène.
     */
    public void switchSceneStart(ActionEvent event) throws IOException {
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        switchSceneall("joueur.fxml");
    }

    /**
     * Valide le nombre de joueurs et change la scène vers "jeu.fxml".
     *
     * @param event Evènement lors du clic.
     * @throws IOException En cas d'erreur lors du chargement de la scène.
     */
    public void valider(ActionEvent event) throws IOException {
        int text = 0;
        do {
            try {
                text = Integer.parseInt(joueur.getText());
                joueur.clear();
            } catch (NumberFormatException e) {
                joueur.clear();
                alertjoueur.setText("Entrer un nombre !!");
            } finally {
                joueur.clear();
            }
            joueur.clear();
        } while (text < 1 || text > 10);
        Method.nbr_joueur = text;
        System.out.println(Method.nbr_joueur);
        suivant.setDisable(false);
        Card.cart(event, primaryStage);
    }

    /**
     * Change la scène vers "jeu.fxml".
     *
     * @param event Evènement lors du clic.
     * @throws IOException En cas d'erreur lors du chargement de la scène.
     */
    public void switchScenejeu(ActionEvent event) throws IOException {
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        switchSceneall("jeu.fxml");
        addCard();
    }

    /**
     * Change la scène vers la scène spécifiée.
     *
     * @param fxml Le nom du fichier FXML de la nouvelle scène.
     * @throws IOException En cas d'erreur lors du chargement de la scène.
     */
    public void switchSceneall(String fxml) throws IOException {
        FXMLLoader sce = new FXMLLoader(getClass().getResource(fxml));
        sce.setLocation(HelloApplication.class.getResource(fxml));
        root = sce.load();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Vérifie si la case à cocher pour l'IA est sélectionnée.
     * Si oui, l'ajouter.
     *
     * @param event L'événement de changement d'état de la case à cocher.
     */
    public void checkIA(ActionEvent event) {
        if (ia.isSelected()) {
            Start.ia = 1;
            System.out.println(Start.ia);
        } else {
            Start.ia = 0;
            System.out.println(Start.ia);
        }
    }

    /**
     * Ajoute les cartes aux joueurs et les affiche dans la zone dédiée.
     *
     * @throws IOException En cas d'erreur lors de l'affichage des cartes.
     */
    private void addCard() throws IOException {
        Start.start();
        for (int i = 0; i < Method.nbr_joueur; i++) {
            ControlleurCarte.start(i);
        }
        System.out.println(joueurs.mainFx.size());
    }

    /**
     * Affiche les cartes du premier joueur dans la zone dédiée.
     *
     * @param event Evènement lors du clic.
     * @throws IOException En cas d'erreur lors de l'affichage des cartes.
     */
    public void car(ActionEvent event) throws IOException {
        cardpane.getChildren().clear();
        cardpane.getChildren().addAll(joueurs.mainFx.get(0));
    }

    /**
     * Affiche les cartes du deuxième joueur dans la zone dédiée.
     *
     * @param event Evènement lors du clic.
     * @throws IOException En cas d'erreur lors de l'affichage des cartes.
     */
    public void car1(ActionEvent event) throws IOException {
        cardpane.getChildren().clear();
        cardpane.getChildren().addAll(joueurs.mainFx.get(1));
    }

    /**
     * Affiche les cartes du troisième joueur dans la zone dédiée.
     *
     * @param event Evènement lors du clic.
     * @throws IOException En cas d'erreur lors de l'affichage des cartes.
     */
    public void car2(ActionEvent event) throws IOException {
        cardpane.getChildren().clear();
        cardpane.getChildren().addAll(joueurs.mainFx.get(2));
    }

    /**
     * Affiche les cartes du quatrième joueur dans la zone dédiée.
     *
     * @param event Evènement lors du clic.
     * @throws IOException En cas d'erreur lors de l'affichage des cartes.
     */
    public void car3(ActionEvent event) throws IOException {
        cardpane.getChildren().clear();
        cardpane.getChildren().addAll(joueurs.mainFx.get(3));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
