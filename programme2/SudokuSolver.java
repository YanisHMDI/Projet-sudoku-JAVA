public abstract class SudokuSolver {
    protected GrilleSudoku grille;

    public SudokuSolver(GrilleSudoku grille) {
        this.grille = grille;
    }

    public abstract boolean resoudre();
}
