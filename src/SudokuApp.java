import java.util.Scanner;

public class SudokuApp {
    public static void main(String[] args) {
        Grille grille = new Grille();
        Scanner scanner = new Scanner(System.in);

        try {
            // Étape 1 : Chargement 
            if (args.length > 0) {
                System.out.println("Chargement du fichier : " + args[0]);
                grille.chargerFichier(args[0]);
            } else {
                grille.chargerSaisie(scanner);
            }

            // Étape 2 : Affichage Initial 
            System.out.println("\nGrille initiale :");
            Afficheur.afficherGrille(grille);

            // Étape 3 : Résolution 
            Solveur solveur = new Solveur();
            System.out.println("\nRésolution en cours...");

            if (solveur.resoudre(grille)) {
                System.out.println("Grille résolue avec succès !");
                Afficheur.afficherGrille(grille);
            } else {
                System.out.println("Aucune solution possible pour cette grille.");
            }

        } catch (Exception e) {
            System.out.println("Erreur critique : " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}