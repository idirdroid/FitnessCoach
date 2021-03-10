package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {


        //Déclaration du scanner
        Scanner scan = new Scanner(System.in);
        int mainMenuEntry = 3;

        int mainMenuChoice = -1;
        do{
            mainMenuDisplay();
            mainMenuChoice = userChoice(scan, mainMenuEntry);

            switch (mainMenuChoice) {
                case 1:
                    //System.out.println("Ajouter un set");
                    ajouter_set();
                    break;
                case 2:
                    //System.out.println("Stats sur un set");
                    afficher_set(scan);
                    break;
            }
        } while (mainMenuChoice != 3);

        System.out.println("Fin du programme");


        }

    private static int userChoice(Scanner sc, int menuEntry) {
        boolean checkInput = false;
        int choice = -1;
        do {
            System.out.println("Veuillez saisir votre choix: ");
            try {
                choice = sc.nextInt();
                sc.nextLine();
                if(choice<1 || choice>menuEntry) {
                    System.err.println("Entrez un nombre compris entre 1 et " + menuEntry);
                }
                else {
                    checkInput = true;
                }
            }catch (InputMismatchException e) {
                checkInput = false;
                System.err.println("Veuillez saisir un nombre");
                sc.nextLine();
            }

        }while(!checkInput);

        return choice;
    }

    private static void mainMenuDisplay() {
        //Affichage du menu principal
        System.out.println("-----------------Fitness Coach-----------------");
        System.out.println("1.--> Ajouter un set");
        System.out.println("2.--> Afficher les performance sur un exercice");
        System.out.println("3.--> Quitter les programme");
        System.out.println("-----------------#############-----------------");

    }

    private static void ajouter_set() {
        //Fonction pour ajouter un Set au fichier

    }

    private static void afficher_set(Scanner scan) {
        //Fonction pour afficher un set
        //Menu
        //Prévoir menu fonction exercice
        ArrayList exercises = new ArrayList<String>();

        exercises = exerciseMenu();
        //userExerciseChoice contient le choix de l'exercice sous forme d'entier
        int userExerciseChoice = userChoice(scan, exercises.size());

        System.out.println(userExerciseChoice + " : " + exercises.get(userExerciseChoice -1));

        System.out.println("-----------------Fitness Coach-----------------");
        System.out.println("1.--> Stats de poids (/Répétitions)");
        System.out.println("2.--> Stats de nombre de répétitions");
        System.out.println("3.--> Stats de poids (/set)");
        System.out.println("-----------------#############-----------------");

        System.out.println("Faites votre choix");
        scan.nextInt();
        scan.nextLine();

        //Récupération des stats pour l'exercice choisi
        String currentLine;
        try {
            Scanner scannerFile = new Scanner(new File("src/com/company/donnees.csv"));
            while (scannerFile.hasNextLine()) {
                //lineNumber++;
                currentLine = scannerFile.nextLine();
                //Délimiter la ligne avec les ";"
                String[] tempArray;
                tempArray = currentLine.split(";");
                System.out.println(tempArray[0]);
                if(tempArray[0] == exercises.get(userExerciseChoice -1)){
                    //On affiche la stat
                    System.out.println(tempArray[0]);
                }
            }
            scannerFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }//Files.write(chemin, "toto", APPEND);
        //Files.readAllLines(chemin);


    }

    private static ArrayList exerciseMenu() {
        //Chercher dans un fichier d'exercices
        ArrayList exercices = new ArrayList<String>();
        int lineNumber = 0;
        String line;

        try {
            Scanner scanner = new Scanner(new File("src/com/company/exercices.txt"));
            while (scanner.hasNextLine()) {
                lineNumber++;
                line = scanner.nextLine();
                System.out.println(lineNumber+".--> "+ line);
                exercices.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return exercices;
    }


}
