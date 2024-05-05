import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        GrilleSudoku grille = new GrilleSudoku(); // Créez une instance de GrilleSudoku
        SwingUtilities.invokeLater(() -> new SudokuInterface(grille));
    }}
