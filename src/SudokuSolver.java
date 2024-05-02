public class SudokuSolver {
    public static boolean solve(GrilleSudoku grille) {
        int[] coordonnees = trouverCaseVide(grille);
        if (coordonnees == null) {
            // La grille est résolue
            return true;
        }

        int ligne = coordonnees[0];
        int colonne = coordonnees[1];

        // Essayer d'assigner une valeur à la case vide
        for (int valeur = 1; valeur <= 9; valeur++) {
            if (grille.estValide(ligne, colonne, valeur)) {
                grille.setCellule(ligne, colonne, valeur);

                // Résoudre récursivement le reste de la grille
                if (solve(grille)) {
                    return true;
                }

                // Si la valeur actuelle ne mène pas à une solution, réinitialiser la case
                grille.setCellule(ligne, colonne, 0);
            }
        }

        // Si aucune valeur ne fonctionne dans cette case, revenir en arrière
        return false;
    }

    private static int[] trouverCaseVide(GrilleSudoku grille) {
        for (int ligne = 0; ligne < 9; ligne++) {
            for (int colonne = 0; colonne < 9; colonne++) {
                if (grille.getCellule(ligne, colonne).estVide()) {
                    return new int[]{ligne, colonne};
                }
            }
        }
        return null; // Aucune case vide trouvée
    }
}
