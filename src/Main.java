import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // Création de l'interface graphique Sudoku
        SudokuInterface sudokuInterface = new SudokuInterface();

        // Création du contrôleur en lui passant la grille et l'interface
        SudokuController sudokuController = new SudokuController(sudokuInterface.getGrille(), sudokuInterface);

        // Affichage de l'interface graphique Sudoku
        sudokuInterface.setVisible(true);
    }
}