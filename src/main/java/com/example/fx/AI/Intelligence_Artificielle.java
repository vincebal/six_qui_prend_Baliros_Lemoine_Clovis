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
    public static void makeListIdentical(Card[][] list1, Card[][] list2) {
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list1[0].length; j++) {
                list1[i][j] = list2[i][j];
            }
        }
    }
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
    public static void restCarte(){//on créé les carte qu'il reste
        List<Card> main = mainordi;
        Allcarte.removeAll(main);

    }
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
    private static void choixmin(int point){
        int min = Savemouv.get(2);
        if(point<min){
            Savemouv.clear();
            Savemouv.add(Save);
            Savemouv.add(Savecolumn);
            Savemouv.add(point);

        }
    }


    public static int lastcolumnV(int i){
        int lastcolumn=5;
        while( rangeesV[lastcolumn][i].getNum_card()==0){
            lastcolumn-=1;
        }
        return lastcolumn;
    }
    public static boolean verificationV(Card Cardplay, int choix){
        for (int k = 0; k < 4; k++) {
            int indexLastcolumn= lastcolumnV(k);
            if (Cardplay.getNum_card() > rangeesV[indexLastcolumn][k].getNum_card()) {
                return true;
            }
        }
        return false;
    }
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
    public static void rammasserV(int i, int j, Card cardPlay){
        int indexLastcol= lastcolumnV(j);
        for (int k=0;k<=indexLastcol;k++) {
            pliJoueurV.get(i).add(rangeesV[k][j]);
            rangeesV[k][j]=Card0;
        }
        rangeesV[0][j]=cardPlay;

    }
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