import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        // Chemin des fichiers de grilles de Sudoku
        String fichierEntree = "grille_entree.txt";
        String fichierSortie = "grille_sortie.txt";

        // Charger une grille de Sudoku depuis un fichier
        try {
            GrilleSudoku grille = SudokuFileHandler.chargerGrille(fichierEntree);
            System.out.println("Grille chargée avec succès :");
            afficherGrille(grille);

            // Résoudre la grille automatiquement
            SudokuSolver.solve(grille);
            System.out.println("Grille résolue :");
            afficherGrille(grille);

            // Sauvegarder la grille résolue dans un fichier
            SudokuFileHandler.sauvegarderGrille(grille, fichierSortie);
            System.out.println("Grille sauvegardée avec succès.");
        } catch (FileNotFoundException e) {
            System.err.println("Erreur : Fichier introuvable.");
            e.printStackTrace();
        }
    }

    public static void afficherGrille(GrilleSudoku grille) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grille.getCellule(i, j).getValeur() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
