import java.io.*;

public class SudokuFileIO {
    private static final String FILENAME = "sudoku.txt";

    public static void sauvegarderGrilleVide() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (int ligne = 0; ligne < 9; ligne++) {
                for (int colonne = 0; colonne < 9; colonne++) {
                    writer.print("0"); // Grille vide
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GrilleSudoku chargerGrilleVide() {
        GrilleSudoku grille = new GrilleSudoku();
        // Nous n'avons pas besoin de charger une grille vide car elle est déjà initialisée lors de la création
        return grille;
    }
}
