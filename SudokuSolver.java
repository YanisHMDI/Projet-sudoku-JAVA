public class SudokuSolver {
    public static boolean solve(GrilleSudoku grille) {
        return solveRecursif(grille, 0, 0);
    }

    private static boolean solveRecursif(GrilleSudoku grille, int ligne, int colonne) {
        // Si la grille est complète, la résolution est terminée
        if (ligne == 9) {
            return true;
        }

        // Trouver la prochaine cellule vide
        if (!grille.getCellule(ligne, colonne).estVide()) {
            int[] next = trouverProchaineCellule(ligne, colonne);
            return solveRecursif(grille, next[0], next[1]);
        }

        // Essayer d'assigner chaque valeur possible à la cellule vide
        for (int valeur = 1; valeur <= 9; valeur++) {
            if (grille.estValide(ligne, colonne, valeur)) {
                grille.setCellule(ligne, colonne, valeur);
                int[] next = trouverProchaineCellule(ligne, colonne);
                if (solveRecursif(grille, next[0], next[1])) {
                    return true;
                }
                grille.setCellule(ligne, colonne, 0); // Annuler l'assignation si la valeur n'est pas valide
            }
        }

        // Aucune valeur possible pour cette cellule
        return false;
    }

    private static int[] trouverProchaineCellule(int ligne, int colonne) {
        int[] next = new int[2];
        if (colonne == 8) {
            next[0] = ligne + 1;
            next[1] = 0;
        } else {
            next[0] = ligne;
            next[1] = colonne + 1;
        }
        return next;
    }
}
