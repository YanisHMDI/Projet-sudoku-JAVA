public class AutomatiqueSolver extends SudokuSolver {
    public AutomatiqueSolver(GrilleSudoku grille) {
        super(grille);
    }

    @Override
    public void resoudre() {
        long startTime = System.nanoTime();
        if (resoudreSudoku(0, 0)) { // Commencer la résolution à la première cellule
            long tempsExecution = mesureTemps(startTime);

            // Afficher la grille résolue
            afficherGrille();

            System.out.println("Temps nécessaire à la résolution : " + tempsExecution + " millisecondes.");
        } else {
            System.out.println("Aucune solution trouvée.");
        }
    }

    private boolean resoudreSudoku(int row, int col) {
        // Si nous avons parcouru toutes les lignes, cela signifie que nous avons trouvé une solution
        if (row == 9) {
            return true;
        }

        // Si nous avons parcouru toutes les colonnes de cette ligne, passons à la ligne suivante
        if (col == 9) {
            return resoudreSudoku(row + 1, 0);
        }

        // Si la cellule est déjà remplie, passons à la cellule suivante
        if (!grille.getCellule(row, col).estVide()) {
            return resoudreSudoku(row, col + 1);
        }

        // Essayer de placer chaque chiffre possible dans cette cellule
        for (int num = 1; num <= 9; num++) {
            // Vérifier si le nombre est valide pour cette cellule
            if (grille.estValide(row, col, num)) {
                // Mettre à jour la valeur de la cellule dans la grille
                grille.setCellule(row, col, num);

                // Résoudre récursivement le reste du Sudoku
                if (resoudreSudoku(row, col + 1)) {
                    return true;
                }

                // Si la solution n'est pas trouvée avec ce nombre, réinitialiser la cellule
                grille.setCellule(row, col, 0);
            }
        }

        // Aucune solution trouvée pour cette cellule
        return false;
    }
}
