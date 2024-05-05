import java.io.Serializable;

public class GrilleSudoku implements Serializable {
    private static final long serialVersionUID = 1L; // Numéro de série pour la sérialisation
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
    

    public void reinitialiser() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grille[i][j].setValeur(0); // Remise à zéro de la valeur de chaque cellule
            }
        }
    }



    public void setCellule(int ligne, int colonne, int valeur) {
        grille[ligne][colonne].setValeur(valeur);
    }

    public boolean estValide(int ligne, int colonne, int valeur) {
        // Vérifie si la valeur est déjà présente dans la même ligne
        for (int i = 0; i < 9; i++) {
            if (i != colonne && grille[ligne][i].getValeur() == valeur) {
                return false;
            }
        }

        // Vérifie si la valeur est déjà présente dans la même colonne
        for (int j = 0; j < 9; j++) {
            if (j != ligne && grille[j][colonne].getValeur() == valeur) {
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

        return true; // Retourne true si la valeur est valide
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

    public boolean resoudre() {
    return resoudre(0, 0);
}

    private boolean resoudre(int ligne, int colonne) {
        if (ligne == 9) {
            return true; // La grille est résolue
        }

        if (grille[ligne][colonne].estVide()) {
            for (int valeur = 1; valeur <= 9; valeur++) {
                if (estValide(ligne, colonne, valeur)) {
                    grille[ligne][colonne].setValeur(valeur);
                    int nextLigne = ligne + (colonne + 1) / 9;
                    int nextColonne = (colonne + 1) % 9;
                    if (resoudre(nextLigne, nextColonne)) {
                        return true;
                    }
                    grille[ligne][colonne].setValeur(0); // Réinitialiser la valeur si la résolution a échoué
                }
            }
            return false; // Aucune valeur valide pour cette case
        } else {
            int nextLigne = ligne + (colonne + 1) / 9;
            int nextColonne = (colonne + 1) % 9;
            return resoudre(nextLigne, nextColonne); // Passer à la prochaine case
        }
    }
}
