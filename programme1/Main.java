import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GrilleSudoku grille = SudokuFileIO.chargerGrilleVide(); // Charger une grille vide au démarrage
            SudokuInterface sudokuInterface = new SudokuInterface(grille);
        });
    }
}
