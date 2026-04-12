public class Afficheur {

    public static void afficherGrille(Grille grille) {
        System.out.println("┌───────┬───────┬───────┐");

        for (int i = 0; i < 9; i++) {

            System.out.print("│ ");

            for (int j = 0; j < 9; j++) {
                int val = grille.getCase(i, j).getValeur();

                if (val == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(val + " ");
                }

                // Séparateur vertical des blocs
                if ((j + 1) % 3 == 0 && j < 8) {
                    System.out.print("│ ");
                }
            }

            System.out.println("│");

            // Séparateur horizontal des blocs
            if ((i + 1) % 3 == 0 && i < 8) {
                System.out.println("├───────┼───────┼───────┤");
            }
        }

        System.out.println("└───────┴───────┴───────┘");
    }
}
