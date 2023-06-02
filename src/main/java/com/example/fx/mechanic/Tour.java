package com.example.fx.mechanic;

import com.example.fx.AI.Intelligence_Artificielle;
import com.example.fx.method;
import com.example.fx.object.Card;
import lombok.Getter;
import lombok.Setter;

import static com.example.fx.joueurs.joueurs.joueurs;
import static com.example.fx.joueurs.joueurs.joueursPli;
import static com.example.fx.mechanic.Method.*;
import static com.example.fx.object.Card.Allcarte;

@Getter
@Setter
public class Tour {
    /**
     * Récupère la dernière colonne d'une rangée pour un joueur donné.
     *
     * @param i L'indice du joueur.
     * @return Dernière colonne de la rangée pour le joueur.
     */
    public static int lastcol(int i) {
        int lastcolumn = 5;
        while (rangees[lastcolumn][i].getNum_card() == 0) {
            lastcolumn -= 1;
        }
        return lastcolumn;
    }

    /**
     * Vérifie si une carte choisie par un joueur est supérieure aux cartes de la dernière colonne de chaque rangée.
     *
     * @param i L'indice du joueur.
     * @param choix L'indice de la carte choisie par le joueur.
     * @return Vrai si la carte choisie est supérieure à la dernière colonne des autres rangées, sinon faux.
     */
    public static boolean verification(int i, int choix) {
        for (int k = 0; k < 4; k++) {
            int indexLastcol = lastcol(k);
            if (joueurs.get(i).get(choix).getNum_card() > rangees[indexLastcol][k].getNum_card()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Effectue le tour d'un joueur en plaçant une carte dans la rangée appropriée.
     *
     * @param i L'indice du joueur.
     * @param choix L'indice de la carte choisie par le joueur.
     */
    static void tour(int i, int choix) {
        int indexRangee = 0;
        int lastcol;
        int indexLastcol = lastcol(0);

        for (int k = 1; k < 4; k++) {
            lastcol = lastcol(k);
            if ((joueurs.get(i).get(choix).getNum_card() > rangees[indexLastcol][indexRangee].getNum_card())) {
                if ((rangees[lastcol][k].getNum_card() > rangees[indexLastcol][indexRangee].getNum_card()) &&
                        (rangees[lastcol][k].getNum_card() < joueurs.get(i).get(choix).getNum_card())) {
                    indexRangee = k;
                    indexLastcol = lastcol;
                }
            } else {
                indexRangee = k;
                indexLastcol = lastcol;
            }
        }

        if (indexLastcol == 4) {
            rammasser_cartes(i, indexRangee, joueurs.get(i).get(choix));
        } else {
            rangees[indexLastcol + 1][indexRangee] = joueurs.get(i).get(choix);
        }
        joueurs.get(i).remove(joueurs.get(i).get(choix));
    }

    /**
     * Ramasse les cartes d'une colonne de rangées pour un joueur donné et place la carte jouée à la première rangée.
     *
     * @param i L'indice du joueur.
     * @param j L'indice de la colonne.
     * @param cardPlay Carte jouée par le joueur.
     */
    public static void rammasser_cartes(int i, int j, Card cardPlay) {
        int indexLastcol = lastcol(j);
        for (int k = 0; k <= indexLastcol; k++) {
            joueursPli.get(i).add(rangees[k][j]);
            rangees[k][j] = Card0;
        }
        rangees[0][j] = cardPlay;
    }

    /**
     * Gère le déroulement du jeu en effectuant les tours des joueurs.
     */
    public static void game() {
        for (int i = 0; i < joueurs.size(); i++) {
            plateau();
            if (joueurs.get(i) != Intelligence_Artificielle.mainordi) {
                int choix;
                int choix2;
                show(i);
                System.out.print("Joueur " + (i + 1) + ", choisissez une carte : ");
                choix = method.scInt("->", joueurs.get(i).size()) - 1;

                if (verification(i, choix)) {
                    Allcarte.remove(joueurs.get(i).get(choix));
                    tour(i, choix);
                } else {
                    System.out.print("Joueur " + (i + 1) + ", choisissez une colonne : ");
                    choix2 = method.scInt("->", 4) - 1;
                    rammasser_cartes(i, choix2, joueurs.get(i).get(choix));
                    Allcarte.remove(joueurs.get(i).get(choix));
                    joueurs.get(i).remove(joueurs.get(i).get(choix));
                }
            } else {
                Intelligence_Artificielle.tree(50);
                int random = Intelligence_Artificielle.Savemouv.get(0);
                int randomcol = Intelligence_Artificielle.Savemouv.get(1);
                Intelligence_Artificielle.Savemouv.clear();

                if (verification(i, random)) {
                    tour(i, random);
                } else {
                    rammasser_cartes(i, randomcol, joueurs.get(i).get(random));
                    joueurs.get(i).remove(joueurs.get(i).get(random));
                }
            }
        }
    }
}
