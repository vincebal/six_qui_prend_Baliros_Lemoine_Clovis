package com.example.fx;

import java.util.Scanner;

/**
 * Classe contenant des méthodes utiles à la mise en forme.
 */
public class method {
    static Scanner sc = new Scanner(System.in);

    /**
     * Efface la console en ajoutant une série de lignes vides.
     */
    public static void clearConsole() {
        for (int i = 0; i < 100; i++)
            System.out.println();
    }

    /**
     * Ajoute une ligne de séparation.
     *
     * @param n Le nombre de points à imprimer.
     */
    public static void printLine(int n) {
        for (int i = 0; i < n; i++)
            System.out.print(".");
        System.out.println(" ");
    }

    /**
     * Attend que l'utilisateur appuie sur la touche Entrée.
     */
    public static void enterContinue() {
        System.out.println("Appuyez sur Entrée pour continuer");
        sc.nextLine();
    }

    /**
     * Lit un entier à partir de la console en affichant un prompt.
     * L'entier doit être compris entre 1 et userChoice inclus.
     *
     * @param prompt Le message affiché à l'utilisateur.
     * @param userChoice La valeur maximale autorisée pour l'entier.
     * @return L'entier saisi par l'utilisateur.
     */
    public static int scInt(String prompt, int userChoice) {
        int input = 0;
        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(sc.next());
            } catch (Exception e) {
                input = -1;
                System.out.println("Veuillez entrer un nombre");
            }
        } while ((input < 1 || input > userChoice));
        return input;
    }

    /**
     * Ajoute un titre centré entouré de lignes de caractères.
     *
     * @param title Le titre à ajouter.
     */
    public static void printTitle(String title) {
        printLine(50);
        System.out.println(title);
        printLine(50);
    }
}
