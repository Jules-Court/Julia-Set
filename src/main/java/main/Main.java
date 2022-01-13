package main;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    /* Version Terminal */
    public static void terminalJulia() throws IOException {
        System.out.println("Création du complexe :\n");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Veuillez saisir la partie réelle (avec une virgule): \n");
            double a = scanner.nextDouble();

            System.out.print("Veuillez saisir la partie imaginaire (avec une virgule) : \n");
            double b = scanner.nextDouble();

            Complexe c = new Complexe(a, b);// Création du complexe avec les données de l'user
            System.out.println("Le niveau de zoom : \n");
            double zoom = scanner.nextDouble();
            System.out.println("Le nombre d'itération : \n");
            int iter = scanner.nextInt();
            System.out.println("La taille de l'image (juste la longueur) \n");
            int height = scanner.nextInt();
            System.out.println("Formation de l'ensemble de Julia...\n");
            Julia julia = Julia.newJulia(c, zoom, iter, height, height);
            julia.generateJulia(); // Création de l'ensemble de Julia
            julia.writeIMG();
            System.exit(0);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Merci de saisir un nombre");
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Choix de l'option de lancement :");
        System.out.println("1 pour version terminal");
        System.out.println("2 pour version graphique");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Veuillez saisir 1 ou 2: \n");
            int a = scanner.nextInt();
            if (a == 1) { // Cas pour le terminal
                terminalJulia();

            } else if (a == 2) { // Cas pour l'IG

                InterfaceGraphique ig = new InterfaceGraphique();
                ig.generateIG();
            } else { // Si l'user n'a pas mis 1 ou 2, relance le main
                main(args);
            }
        } catch (Exception e) {
            System.out.println("Merci de choisir 1 ou 2\n");

        }

    }

}
