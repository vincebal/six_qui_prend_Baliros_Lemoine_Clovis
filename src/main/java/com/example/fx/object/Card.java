package com.example.fx.object;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Card {
    private int Num_card;
    private int nbrTaureau;

    public static List<Card> cartes = new ArrayList<>();
    public static List<Card> AllcarteV = new ArrayList<>();
    public static List<Card> Allcarte = new ArrayList<>();

    public Card(int n, int num) {
        Num_card = n;
        nbrTaureau = num;
    }

    public static int taureau(int Num_card, int i) {
        if (Num_card % 10 == 5) {
            cartes.get(i - 1).setNbrTaureau(cartes.get(i - 1).getNbrTaureau() + 2);
        }
        if (Num_card % 10 == 0) {
            cartes.get(i - 1).setNbrTaureau(cartes.get(i - 1).getNbrTaureau() + 3);
        }
        if (Num_card % 11 == 0) {
            cartes.get(i - 1).setNbrTaureau(cartes.get(i - 1).getNbrTaureau() + 5);
        }
        if (Num_card % 10 != 5 && Num_card % 10 != 0 && Num_card % 11 != 0) {
            cartes.get(i - 1).setNbrTaureau(cartes.get(i - 1).getNbrTaureau() + 1);
        }
        return cartes.get(i - 1).getNbrTaureau();
    }

    public static List<Card> cart() {
        // Initialiser les cartes avec les valeurs de 1 à 104
        for (int i = 1; i <= 104; i++) {
            Card card = new Card(0, 0);
            cartes.add(card);
            Allcarte.add(card);
            cartes.get(i - 1).setNum_card(i);
            Allcarte.get(i - 1).setNum_card(i);
            taureau(cartes.get(i - 1).getNum_card(), i);
            taureau(Allcarte.get(i - 1).getNum_card(), i);
        }

        return cartes;
    }
}