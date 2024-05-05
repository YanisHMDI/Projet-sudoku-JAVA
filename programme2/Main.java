import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GrilleSudoku grille = SudokuFileIO.chargerGrilleVide(); // Charger une grille vide au démarrage
            SudokuInterface sudokuInterface = new SudokuInterface(grille);
            // Ajout du troisième argument pour le mode (true pour le mode automatique, false pour le mode manuel)
            SudokuController controller = new SudokuController(grille, sudokuInterface);
            sudokuInterface.setVisible(true); // Assurez-vous que l'interface est visible
        });
    }
}
