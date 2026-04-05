public class SudokuApp {

    public static void main(String[] args) {

        Grille grille = null;

        try {
            // Si fichier passé en argument
            if (args.length > 0) {
                grille = LecteurGrille.lireDepuisFichier(args[0]);
            } 
            // Sinon saisie manuelle
            else {
                grille = SaisieConsole.saisirGrille();
            }

            System.out.println("\nGrille initiale :");
            Afficheur.afficherGrille(grille);

            // Résolution
            Solveur solveur = new Solveur();

            if (solveur.resoudre(grille)) {
                System.out.println("\nGrille résolue :");
                Afficheur.afficherGrille(grille);
            } else {
                System.out.println("\nAucune solution trouvée !");
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
