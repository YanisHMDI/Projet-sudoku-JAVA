import java.util.concurrent.TimeUnit;

public abstract class SudokuSolver {
    protected GrilleSudoku grille;

    public SudokuSolver(GrilleSudoku grille) {
        this.grille = grille;
    }

    public abstract void resoudre();

    protected void afficherGrille() {
        // Afficher la grille dans la console
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grille.getCellule(i, j).getValeur() + " ");
            }
            System.out.println();
        }
    }

    protected long mesureTemps(long startTime) {
        long endTime = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
    }
}
