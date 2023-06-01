package com.example.fx.mechanic;

import com.example.fx.AI.Intelligence_Artificielle;
import com.example.fx.method;
import com.example.fx.object.Card;


import java.util.List;
import java.util.Scanner;

import static com.example.fx.joueurs.joueurs.joueurs;
import static com.example.fx.object.Card.Allcarte;
import static com.example.fx.object.Card.cartes;
import lombok.*;
@Getter@Setter


public class Method {
    static Scanner sc = new Scanner(System.in);
    public static Card Card0;
    public static int nbr_joueur;
    public static Card[][] rangees ;
    public static void afficherElementPlusPetit(List<Integer> liste) {
        int min = liste.get(0);
        int position = 0;
        for (int i = 1; i < liste.size(); i++) {
            if (liste.get(i) < min) {
                min = liste.get(i);
                position = i;
            }
        }
        System.out.println("Le joueur " + (position+1) + " à gagné!! avec un score de : " + min );
    }
    public static void afficherElementPlusGrand(List<Integer> liste) {
        int max = liste.get(0);
        int position = 0;
        for (int i = 1; i < liste.size(); i++) {
            if (liste.get(i) > max) {
                max = liste.get(i);
                position = i;
            }
        }
        System.out.println("Le joueur " + (position+1) + " à perdu avec un score de : " + max);
    }
    public static void show(int i){
        System.out.println("\nMain Joueur " + (i + 1) + " : \n");
        for (int j =0;j<joueurs.get(i).size();j++) {
            System.out.println((j+1) + ". num Carte : " + joueurs.get(i).get(j).getNum_card() + "          nbr taureau : " + joueurs.get(i).get(j).getNbrTaureau());
        }
    }
    public static void Initplateau() {
        rangees = new Card[6][4];
        Intelligence_Artificielle.rangeesV = new Card[6][4];

        for (int i = 0; i <= rangees.length-1; i++) {
            for (int j = 0; j <= rangees[i].length-1; j++) {
                rangees[i][j] = Card0;
            }
        }
    }
    public static void plateau(){
        String plateau="";







        method.clearConsole();
        method.printLine(50);
        System.out.println("Pile de carte :");

        int code=0;
        for (int i =0;i<=rangees.length-1;i++){
            String top = " ";
            String middle = "";
            String bottom = "";
            code=0;
            for (int j = 0; j<=rangees[i].length-1; j++){

                if (rangees[i][j].getNum_card()==0){
                    code+=1;
                    top += "   |    col " + (j+1) + "    |";
                    middle += "       | num " + rangees[i][j].getNum_card() + " |";
                    bottom += "       | tau " + rangees[i][j].getNbrTaureau() + " |  ";
                }
                else {
                    top += "   |    col " + (j+1) + "    |";
                    middle += "       | num " + rangees[i][j].getNum_card() + " |";
                    bottom += "       | tau " + rangees[i][j].getNbrTaureau() + " |  ";
                }

            }
            if (code==4){
                break;
            }
            else {
                String line = top + "\n" + middle + "\n" + bottom;
                plateau += "\n\n\n" + line;
            }
        }
        System.out.println(plateau);
        method.printLine(50);
    }
    public static void init(){
        Initplateau();
        for (int i =0;i<4;i++){
            rangees[0][i]=cartes.get(0);
            Allcarte.remove(cartes.get(0));
            cartes.remove(cartes.get(0));
        }
        plateau();
    }


}