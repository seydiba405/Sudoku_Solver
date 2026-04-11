import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuSolver {

    public static void main(String[] args) {
        // Nom du fichier texte
        String fileName = "grille.txt";
        int[][] board = loadBoard(fileName);

        if (board == null) {
            System.out.println("Erreur : Impossible de lire le fichier.");
            return;
        }

        System.out.println("Grille chargée depuis le fichier :");
        printBoard(board);

        if (solve(board)) {
            System.out.println("\nSolution trouvee :");
            printBoard(board);
        } else {
            System.out.println("\nPas de solution possible.");
        }
    }

    // NOUVELLE MÉTHODE : Lecture du fichier TXT
    public static int[][] loadBoard(String fileName) {
        int[][] board = new int[9][9];
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    if (scanner.hasNextInt()) {
                        board[row][col] = scanner.nextInt();
                    }
                }
            }
            scanner.close();
            return board;
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouve : " + fileName);
            return null;
        }
    }

    // --- LE RESTE DU CODE RESTE IDENTIQUE ---

    public static boolean solve(int[][] bo) {
        int[] find = findEmpty(bo);
        if (find == null) return true;
        
        int row = find[0];
        int col = find[1];

        for (int i = 1; i <= 9; i++) {
            if (isValid(bo, i, row, col)) {
                bo[row][col] = i;
                if (solve(bo)) return true;
                bo[row][col] = 0;
            }
        }
        return false;
    }

    public static boolean isValid(int[][] bo, int num, int row, int col) {
        for (int i = 0; i < 9; i++) {
            if (bo[row][i] == num && col != i) return false;
            if (bo[i][col] == num && row != i) return false;
        }

        int boxX = col / 3;
        int boxY = row / 3;
        for (int i = boxY * 3; i < boxY * 3 + 3; i++) {
            for (int j = boxX * 3; j < boxX * 3 + 3; j++) {
                if (bo[i][j] == num && (i != row || j != col)) return false;
            }
        }
        return true;
    }

    public static void printBoard(int[][] bo) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) System.out.println("- - - - - - - - - - - - - ");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) System.out.print(" | ");
                System.out.print(bo[i][j] + (j == 8 ? "" : " "));
            }
            System.out.println();
        }
    }

    public static int[] findEmpty(int[][] bo) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (bo[i][j] == 0) return new int[]{i, j};
            }
        }
        return null;
    }
}// create method resoudre (Grille g) create class "Grille"
