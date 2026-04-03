import java.io.*;
import java.util.Scanner;

/**
 * La classe Grille représente le plateau de jeu du Sudoku.
 * Elle contient 81 cases (9 lignes x 9 colonnes) et fournit
 * des outils pour charger, afficher et valider la grille.
 */
public class Grille {

    // Le plateau est un tableau de 9 lignes et 9 colonnes
    // Chaque cellule contient un objet Case (valeur + estFixe)
    // "private" signifie que seule cette classe peut y accéder directement
    private Case[][] plateau;

    /**
     * Constructeur : appelé quand on fait "new Grille()"
     * Il crée le tableau et le remplit de cases vides
     */
    public Grille() {
        // On crée la structure du tableau 9x9
        // Pour l'instant les cases sont "null" (inexistantes)
        plateau = new Case[9][9];

        // On appelle viderGrille() pour créer une Case(0) dans chaque cellule
        viderGrille();
    }

    /**
     * Remplit toutes les cases du plateau avec la valeur 0 (= case vide).
     * Cette méthode est "private" car elle est uniquement utilisée en interne.
     */
    private void viderGrille() {
        // On parcourt chaque ligne (r = row = ligne)
        for (int r = 0; r < 9; r++) {
            // On parcourt chaque colonne (c = col = colonne)
            for (int c = 0; c < 9; c++) {
                // On place une case vide (valeur 0) à la position (r, c)
                // new Case(0) → valeur=0, estFixe=false
                plateau[r][c] = new Case(0);
            }
        }
    }

    /**
     * Charge une grille depuis un fichier texte.
     * Le fichier doit contenir 9 lignes avec 9 chiffres séparés par des espaces.
     * Exemple de ligne dans le fichier : 5 3 0 0 7 0 0 0 0
     *
     * @param filename Le chemin vers le fichier à lire
     * @throws IOException Si le fichier est introuvable ou illisible
     */
    public void chargerFichier(String filename) throws IOException {
        // "try-with-resources" : ouvre le fichier et le ferme automatiquement à la fin
        // BufferedReader lit le fichier ligne par ligne de façon efficace
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line; // Contiendra chaque ligne lue du fichier
            int row = 0; // Compteur de ligne dans le plateau (commence à 0)

            // On lit une ligne du fichier et on vérifie qu'elle n'est pas vide
            // On s'arrête aussi si on a déjà lu 9 lignes
            while ((line = br.readLine()) != null && row < 9) {

                // trim()      → supprime les espaces inutiles en début et fin de ligne
                // split("\\s+") → découpe la ligne en morceaux séparés par des espaces
                // Exemple : "5 3 0 0 7" devient ["5", "3", "0", "0", "7"]
                String[] values = line.trim().split("\\s+");

                // On parcourt les 9 valeurs de la ligne
                for (int col = 0; col < 9; col++) {
                    // Integer.parseInt() convertit le texte "5" en nombre entier 5
                    int val = Integer.parseInt(values[col]);

                    // On crée une Case avec cette valeur et on la place dans le plateau
                    // Si val != 0, la case sera automatiquement marquée comme fixe (estFixe=true)
                    plateau[row][col] = new Case(val);
                }

                row++; // On passe à la ligne suivante du plateau
            }
        }
    }

    /**
     * Permet à l'utilisateur de saisir la grille manuellement dans le terminal.
     * Il devra entrer 9 lignes, chacune contenant 9 chiffres séparés par des espaces.
     * Le chiffre 0 représente une case vide.
     *
     * @param scanner L'objet Scanner pour lire la saisie clavier
     */
    public void chargerSaisie(Scanner scanner) {
        // On affiche les instructions à l'utilisateur
        System.out.println("Entrez les 9 lignes (9 chiffres séparés par des espaces, 0 pour vide) :");

        // On demande les 9 lignes une par une
        for (int r = 0; r < 9; r++) {
            // Affiche "Ligne 1 :", "Ligne 2 :", etc. (r+1 car r commence à 0)
            System.out.print("Ligne " + (r + 1) + " : ");

            // On lit ce que l'utilisateur a tapé et on le découpe comme pour le fichier
            String[] values = scanner.nextLine().trim().split("\\s+");

            // ⚠️ BUG ICI : "col++" devrait être "c++"
            // Tel quel, ce code ne compilera pas car "col" n'est pas déclaré ici
            for (int c = 0; c < 9; c++) { // ✅ VERSION CORRIGÉE
                // On convertit la saisie en entier
                int val = Integer.parseInt(values[c]);

                // On place la Case dans le plateau à la position (r, c)
                plateau[r][c] = new Case(val);
            }
        }
    }

    /**
     * Vérifie si on peut placer le chiffre "num" à la position (row, col).
     * Cette méthode sera utilisée par le solveur (algorithme de backtracking).
     *
     * @param row La ligne de la case (0 à 8)
     * @param col La colonne de la case (0 à 8)
     * @param num Le chiffre qu'on veut placer (1 à 9)
     * @return true si le placement est valide, false sinon
     */
    public boolean estValide(int row, int col, int num) {
        // --- Vérification de la ligne et de la colonne en même temps ---
        for (int i = 0; i < 9; i++) {
            // On vérifie si "num" est déjà présent sur la ligne "row"
            // plateau[row][i] → on fixe la ligne et on parcourt toutes les colonnes
            if (plateau[row][i].valeur == num) return false;

            // On vérifie si "num" est déjà présent dans la colonne "col"
            // plateau[i][col] → on fixe la colonne et on parcourt toutes les lignes
            if (plateau[i][col].valeur == num) return false;
        }

        // --- Vérification du bloc 3x3 ---
        // On calcule la position du coin supérieur gauche du bloc 3x3
        // Exemples :
        //   row=0,1,2 → sR = 0  |  row=3,4,5 → sR = 3  |  row=6,7,8 → sR = 6
        //   col=0,1,2 → sC = 0  |  col=3,4,5 → sC = 3  |  col=6,7,8 → sC = 6
        int sR = (row / 3) * 3; // Début de ligne du bloc
        int sC = (col / 3) * 3; // Début de colonne du bloc

        // On parcourt les 3 lignes du bloc
        for (int i = 0; i < 3; i++)
            // On parcourt les 3 colonnes du bloc
            for (int j = 0; j < 3; j++)
                // Si "num" est déjà dans le bloc → placement invalide
                if (plateau[sR + i][sC + j].valeur == num) return false;

        // Si on arrive ici, aucune règle n'a été violée → on peut placer le chiffre
        return true;
    }

    /**
     * Retourne la Case se trouvant à la position (r, c) du plateau.
     * Utilisé par d'autres classes pour lire les valeurs sans accéder
     * directement à "plateau" (qui est private).
     *
     * @param r La ligne (0 à 8)
     * @param c La colonne (0 à 8)
     * @return L'objet Case à cette position
     */
    public Case getCase(int r, int c) {
        return plateau[r][c];
    }
}