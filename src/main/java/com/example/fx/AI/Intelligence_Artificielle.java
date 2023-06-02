package com.example.fx.AI;

import lombok.Getter;
import lombok.Setter;
import com.example.fx.joueurs.joueurs;
import com.example.fx.object.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.fx.mechanic.Method.*;
import static com.example.fx.object.Card.Allcarte;

/**
 * Contient plusieurs méthodes permettant de simuler les actions de l'IA lors d'une partie.
 * @author  Vincent BALIROS Kenny CLOVIS Alec LEMOINE
 */
@Getter @Setter
public class Intelligence_Artificielle {

    static Random random = new Random();
    private static int Save;
    private static int Savecolumn;
    public static Card[][] rangeesV ;
    public static List<Card> mainordi = new ArrayList<>();
    public static List<Card> orditrash = new ArrayList<>();
    public static List<Card> ordimainV = new ArrayList<>();
    public static List<Card> orditrashV = new ArrayList<>();
    public static List<Card> SaveCard = new ArrayList<>();
    public static List<Integer> Savemouv = new ArrayList<>();
    public static List<Card> trashV = new ArrayList<>();
    public static List<List<Card>> pliJoueurV = new ArrayList<>();
    private static List<Integer> ScoreV = new ArrayList<>();
    private static int l;

    /**
     * Copie les éléments du tableau list2 dans le tableau list1.
     * Utilisé pour copier les éléments de rangees dans rangeesV.
     *
     * @param list1 tableau de cartes de destination
     * @param list2 tableau de cartes source
     */
    public static void makeListIdentical(Card[][] list1, Card[][] list2) {
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list1[0].length; j++) {
                list1[i][j] = list2[i][j];
            }
        }
    }

    /**
     * Effectue une simulation de jeu en utilisant l'algorithme du "minimax".
     * @param profondeur la profondeur de l'arbre à explorer
     */
    public static void tree(int profondeur){

        Savemouv.add(0);
        Savemouv.add(0);
        Savemouv.add(50);


        for(int i =0;i<profondeur;i++){
            makeListIdentical(rangeesV,rangees);
            ordimainV.addAll(mainordi);
            orditrashV.addAll(orditrash);
            choixmin(GameBaseAI());

            orditrashV.clear();
            trashV.clear();
            Allcarte.addAll(SaveCard);
            SaveCard.clear();

        }
    }

    /**
     * Crée les cartes restantes dans le jeu en enlevant les cartes présentes dans la main de l'IA de la liste Allcarte.
     */
    public static void restCarte(){
        List<Card> main = mainordi;
        Allcarte.removeAll(main);

    }

    /**
     * Simule le jeu de base de l'IA à partir de son état actuel.
     * @return le nombre total de taureaux obtenus par l'IA
     */
    public static int GameBaseAI(){
        l=0;
        while(ordimainV.size()>0){
            gameV(l);
            l+=1;
        }
        ScoreV.clear();
        int pointV = 0;
        for (int j = 0; j< pliJoueurV.get(joueurs.joueurs.size()-1).size(); j++){
            pointV += pliJoueurV.get(joueurs.joueurs.size()-1).get(j).getNbrTaureau();//on ajoute les taureaux au compteur
        }
        return pointV;//le nombre de taureau
    }


    /**
     * Compare le pointage actuel avec le pointage minimal enregistré et met à jour les valeurs de Savemouv si le pointage actuel est inférieur au pointage minimal.
     * @param point le pointage actuel
     */
    private static void choixmin(int point){
        int min = Savemouv.get(2);
        if(point<min){
            Savemouv.clear();
            Savemouv.add(Save);
            Savemouv.add(Savecolumn);
            Savemouv.add(point);

        }
    }

    /**
     * Retourne l'indice de la dernière colonne non vide dans une rangée donnée.
     * @param i l'indice de la rangée
     * @return l'indice de la dernière colonne non vide
     */
    public static int lastcolumnV(int i){
        int lastcolumn=5;
        while( rangeesV[lastcolumn][i].getNum_card()==0){
            lastcolumn-=1;
        }
        return lastcolumn;
    }

    /**
     * Vérifie si une carte jouée par l'IA peut être placée dans une rangée spécifique.
     * @param Cardplay la carte jouée par l'IA
     * @param choix l'indice de la carte jouée dans la main de l'IA
     * @return true si la carte peut être placée, sinon false
     */
    public static boolean verificationV(Card Cardplay, int choix){
        for (int k = 0; k < 4; k++) {
            int indexLastcolumn= lastcolumnV(k);
            if (Cardplay.getNum_card() > rangeesV[indexLastcolumn][k].getNum_card()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Effectue le tour de jeu de l'IA.
     * @param i l'indice de l'IA dans la liste des joueurs
     * @param cardPlay la carte jouée par l'IA
     */
    static void tourV(int i, Card cardPlay){
        int indexRangee = 0;
        int lastcolumn;
        int indexLastcol= lastcolumnV(0);

        for (int k = 1; k < 4; k++) {
            lastcolumn = lastcolumnV(k);
            if ((cardPlay.getNum_card() > rangeesV[indexLastcol][indexRangee].getNum_card())) {
                if ((rangeesV[lastcolumn][k].getNum_card() > rangeesV[indexLastcol][indexRangee].getNum_card()) && (rangeesV[lastcolumn][k].getNum_card() < cardPlay.getNum_card())) {
                    indexRangee = k;
                    indexLastcol = lastcolumn;
                }
            }

            else{
                indexRangee = k;
                indexLastcol=lastcolumn;
            }
        }
        if (indexLastcol==4){
            rammasserV(i,indexRangee,cardPlay);
        }
        else {
            rangeesV[indexLastcol + 1][indexRangee] = cardPlay;
        }
    }

    /**
     * Ramasse les cartes d'une rangée spécifique lorsqu'elle ne peut pas jouer de carte.
     * @param i l'indice de l'IA dans la liste des joueurs
     * @param j l'indice de la rangée où les cartes doivent être ramassées
     * @param cardPlay la carte jouée par l'IA
     */
    public static void rammasserV(int i, int j, Card cardPlay){
        int indexLastcol= lastcolumnV(j);
        for (int k=0;k<=indexLastcol;k++) {
            pliJoueurV.get(i).add(rangeesV[k][j]);
            rangeesV[k][j]=Card0;
        }
        rangeesV[0][j]=cardPlay;

    }

    /**
     * Effectue le tour de jeu de l'IA pour chaque joueur.
     * @param l l'indice du tour de jeu actuel
     */
    public static void gameV(int l){

        int randomInput= (int) (Allcarte.size()*0.75);
        int randomInputCol= random.nextInt(4);
        int randomInputAI;
        if (ordimainV.size()!=1) {
            randomInputAI= random.nextInt(ordimainV.size()-1);
        }
        else {
            randomInputAI=0;
        }
        if(l==0){
            Save = randomInputAI;
            Savecolumn = randomInputCol;
        }
        if (verificationV(ordimainV.get(randomInputAI), randomInputAI) == true){
            tourV(joueurs.joueurs.size()-1,joueurs.joueurs.get(joueurs.joueurs.size()-1).get(randomInputAI));
            ordimainV.remove(randomInputAI);
        }
        else{
            rammasserV(joueurs.joueurs.size()-1,randomInputCol,ordimainV.get(randomInputAI));
            ordimainV.remove(randomInputAI);

        }


        for (int i = 0; i < joueurs.joueurs.size()-1; i++) {
            System.out.println(Allcarte.size());
            if (verificationV(Allcarte.get(randomInput), randomInput) == true) {
                tourV(i, Allcarte.get(randomInput));
                SaveCard.add(Allcarte.get(randomInput));
                Allcarte.remove(randomInput);

            } else {
                rammasserV(i, randomInputCol, Allcarte.get(randomInput));
                SaveCard.add(Allcarte.get(randomInput));
                Allcarte.remove(randomInput);
            }
        }
    }
}