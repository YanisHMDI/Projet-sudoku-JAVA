public class GrilleSudoku {
    private Cellule[][] grille;

    public GrilleSudoku() {
        grille = new Cellule[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grille[i][j] = new Cellule();
            }
        }
    }

    public Cellule getCellule(int ligne, int colonne) {
        return grille[ligne][colonne];
    }

    public void setCellule(int ligne, int colonne, int valeur) {
        grille[ligne][colonne].setValeur(valeur);
    }

    public boolean estValide(int ligne, int colonne, int valeur) {
        // Vérifie si la valeur est déjà présente dans la même ligne ou colonne
        for (int i = 0; i < 9; i++) {
            if (grille[ligne][i].getValeur() == valeur || grille[i][colonne].getValeur() == valeur) {
                return false;
            }
        }

        // Vérifie si la valeur est déjà présente dans le même bloc 3x3
        int blocLigne = ligne / 3 * 3;
        int blocColonne = colonne / 3 * 3;
        for (int i = blocLigne; i < blocLigne + 3; i++) {
            for (int j = blocColonne; j < blocColonne + 3; j++) {
                if (grille[i][j].getValeur() == valeur) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean estComplet() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grille[i][j].estVide()) {
                    return false;
                }
            }
        }
        return true;
    }
}