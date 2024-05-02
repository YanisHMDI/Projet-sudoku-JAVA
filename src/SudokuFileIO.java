import java.io.*;

public class SudokuFileIO {
    private static final String FILENAME = "sudoku.txt";

    public static void sauvegarderGrille(GrilleSudoku grille) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (int ligne = 0; ligne < 9; ligne++) {
                for (int colonne = 0; colonne < 9; colonne++) {
                    writer.print(grille.getCellule(ligne, colonne).getValeur());
                    writer.print(" ");
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GrilleSudoku chargerGrille() {
        GrilleSudoku grille = new GrilleSudoku();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            int ligne = 0;
            while ((line = reader.readLine()) != null) {
                String[] valeurs = line.trim().split(" ");
                for (int colonne = 0; colonne < 9; colonne++) {
                    int valeur = Integer.parseInt(valeurs[colonne]);
                    grille.setCellule(ligne, colonne, valeur);
                }
                ligne++;
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return grille;
    }
}