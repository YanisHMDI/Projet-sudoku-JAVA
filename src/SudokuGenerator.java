import java.util.Random;

public class SudokuGenerator {

    public static GrilleSudoku genererGrille() {
        GrilleSudoku grille = new GrilleSudoku();
        resoudreGrille(grille);
        viderCases(grille, 40); // Nombre de cases à vider pour laisser à l'utilisateur
        return grille;
    }

    private static boolean resoudreGrille(GrilleSudoku grille) {
        for (int ligne = 0; ligne < 9; ligne++) {
            for (int colonne = 0; colonne < 9; colonne++) {
                if (grille.getCellule(ligne, colonne).estVide()) {
                    for (int valeur = 1; valeur <= 9; valeur++) {
                        if (grille.estValide(ligne, colonne, valeur)) {
                            grille.setCellule(ligne, colonne, valeur);
                            if (resoudreGrille(grille)) {
                                return true;
                            } else {
                                grille.setCellule(ligne, colonne, 0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static void viderCases(GrilleSudoku grille, int casesAVider) {
        Random random = new Random();
        while (casesAVider > 0) {
            int ligne = random.nextInt(9);
            int colonne = random.nextInt(9);
            if (!grille.getCellule(ligne, colonne).estVide()) {
                grille.setCellule(ligne, colonne, 0);
                casesAVider--;
            }
        }
    }
}
