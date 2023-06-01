package com.example.fx.Cardcontroller;

import com.example.fx.object.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlleurCarte implements Initializable {

    @FXML
    private AnchorPane cardPane;

    @FXML
    private StackPane numberPane;

    @FXML
    private Label numberLabel;

    @FXML
    private Label taureauLabel;

    private Card card;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialisation du contrôleur de carte
    }

    public void setCard(Card card) {
        this.card = card;
        updateCardView();
    }

    private void updateCardView() {
        numberLabel.setText(String.valueOf(card.getNum_card()));
        taureauLabel.setText(String.valueOf(card.getNbrTaureau()));
        setColorBasedOnTaureau();
    }

    private void setColorBasedOnTaureau() {
        int taureau = card.getNbrTaureau();
        if (taureau < 2) {
            cardPane.setStyle("-fx-background-color: green;");
        } else if (taureau < 4) {
            cardPane.setStyle("-fx-background-color: yellow;");
        } else if (taureau < 6) {
            cardPane.setStyle("-fx-background-color: orange;");
        } else {
            cardPane.setStyle("-fx-background-color: red;");
        }
    }
}