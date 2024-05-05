import java.io.*;

public class SudokuFileIO {
    private static final String FILENAME = "sudoku.ser";

    public static void sauvegarderGrille(GrilleSudoku grille) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            outputStream.writeObject(grille);
            System.out.println("Grille sauvegardée avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GrilleSudoku chargerGrilleVide() {
        return new GrilleSudoku(); // Retourne une nouvelle grille vide
    }
}

