import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // Création de la fenêtre de jeu Sudoku
        JFrame fenetre = new JFrame("Sudoku");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajout de l'interface graphique Sudoku à la fenêtre
        SudokuInterface sudokuInterface = new SudokuInterface();
        fenetre.getContentPane().add(sudokuInterface);

        // Redimensionnement automatique de la fenêtre
        fenetre.pack();

        // Affichage de la fenêtre
        fenetre.setVisible(true);
    }
}