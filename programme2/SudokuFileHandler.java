import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SudokuFileHandler {
    public static GrilleSudoku chargerGrille(String cheminFichier) throws FileNotFoundException {
        GrilleSudoku grille = new GrilleSudoku();
        File fichier = new File(cheminFichier);
        Scanner scanner = new Scanner(fichier);
        
        for (int i = 0; i < 9; i++) {
            String ligne = scanner.nextLine();
            for (int j = 0; j < 9; j++) {
                int valeur = Character.getNumericValue(ligne.charAt(j));
                grille.setCellule(i, j, valeur);
            }
        }
        
        scanner.close();
        return grille;
    }

    public static void sauvegarderGrille(GrilleSudoku grille, String cheminFichier) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(cheminFichier);
        
        for (int i = 0; i < 9; i++) {
            StringBuilder ligne = new StringBuilder();
            for (int j = 0; j < 9; j++) {
                ligne.append(grille.getCellule(i, j).getValeur());
            }
            writer.println(ligne);
        }
        
        writer.close();
    }
}