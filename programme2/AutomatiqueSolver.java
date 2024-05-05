public class AutomatiqueSolver extends SudokuSolver {

    public AutomatiqueSolver(GrilleSudoku grille) {
        super(grille);
    }

    @Override
    public boolean resoudre() {
        return resoudreRecursive(0, 0);
    }

    private boolean resoudreRecursive(int ligne, int colonne) {
        if (ligne == 9) {
            ligne = 0;
            if (++colonne == 9) {
                return true; // Toutes les cases ont été remplies
            }
        }
        if (grille.getCellule(ligne, colonne).getValeur() != 0) {
            return resoudreRecursive(ligne + 1, colonne);
        }
        for (int valeur = 1; valeur <= 9; valeur++) {
            if (grille.estValide(ligne, colonne, valeur)) {
                grille.setCellule(ligne, colonne, valeur);
                if (resoudreRecursive(ligne + 1, colonne)) {
                    return true;
                }
            }
        }
        grille.setCellule(ligne, colonne, 0); // Annuler la tentative
        return false;
    }
}
