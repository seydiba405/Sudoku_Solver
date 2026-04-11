public class Solveur {

    // Cette méthode sera appelée par SudokuApp
    public boolean resoudre(Grille g) {
        int[] vide = trouverCaseVide(g);
        
        // Si plus de case vide, le Sudoku est résolu
        if (vide == null) return true;
        
        int row = vide[0];
        int col = vide[1];

        // On teste les chiffres de 1 à 9
        for (int num = 1; num <= 9; num++) {
            // Utilisation de TA méthode estValide de la classe Grille
            if (g.estValide(row, col, num)) {
                g.getCase(row, col).valeur = num; // On pose le chiffre

                if (resoudre(g)) return true; // Récursion

                g.getCase(row, col).valeur = 0; // Backtrack : on efface
            }
        }
        return false;
    }

    private int[] trouverCaseVide(Grille g) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (g.getCase(r, c).valeur == 0) return new int[]{r, c};
            }
        }
        return null;
    }
}