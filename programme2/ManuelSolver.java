<<<<<<< HEAD
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
=======
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
>>>>>>> c186302eae0204dd915362a4f40e4bab613cff36

public class ManuelSolver extends SudokuSolver {
    private SudokuInterface sudokuInterface;

    public ManuelSolver(GrilleSudoku grille, SudokuInterface sudokuInterface) {
        super(grille);
        this.sudokuInterface = sudokuInterface;
    }

    @Override
    public boolean resoudre() {
        // Cette méthode n'est pas utilisée pour le solveur manuel
        return false;
    }

    public void resoudreManuellement() {
        JButton[][] boutons = sudokuInterface.getBoutons();
        for (int ligne = 0; ligne < 9; ligne++) {
            for (int colonne = 0; colonne < 9; colonne++) {
                Cellule cellule = grille.getCellule(ligne, colonne);
                if (cellule.estVide()) {
                    // Afficher un message pour indiquer à l'utilisateur de remplir la case (ligne, colonne)
                    JOptionPane.showMessageDialog(sudokuInterface, "Veuillez remplir la case (" + (ligne + 1) + ", " + (colonne + 1) + ").");

                    // Attendre que l'utilisateur remplisse la case
                    boolean caseRemplie = false;
                    while (!caseRemplie) {
                        // Mettre en surbrillance la case pour indiquer à l'utilisateur de la remplir
                        boutons[ligne][colonne].setBackground(Color.YELLOW);

<<<<<<< HEAD
                        // Attendre que l'utilisateur clique sur la case et entre une valeur
                        try {
                            Thread.sleep(500); // Attente de 500 millisecondes pour permettre à l'interface de se mettre à jour
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // Vérifier si la case a été remplie
                        if (!cellule.estVide()) {
                            caseRemplie = true;
                            // Réinitialiser la couleur de fond de la case
                            boutons[ligne][colonne].setBackground(Color.WHITE);
=======
        // Demander à l'utilisateur d'entrer une valeur
        String inputValue = JOptionPane.showInputDialog("Entrez un nombre entre 1 et 9 :");

        // Vérifier si l'entrée n'est pas nulle
        if (inputValue != null && !inputValue.isEmpty()) {
            try {
                // Convertir la valeur en entier
                int value = Integer.parseInt(inputValue);
                // Vérifier si la valeur est dans la plage valide
                if (value >= 1 && value <= 9) {
                    // Vérifier si la valeur est valide pour cette cellule
                    if (getGrille().estValide(row, col, value)) {
                        // Mettre à jour la valeur de la cellule dans la grille
                        getGrille().setCellule(row, col, value);
                        // Mettre à jour l'affichage du bouton
                        selectedButton.setText(inputValue);

                        // Vérifier si la grille est complète
                        if (getGrille().estComplet()) {
                            JOptionPane.showMessageDialog(null, "Félicitations ! Vous avez résolu le Sudoku !");
>>>>>>> c186302eae0204dd915362a4f40e4bab613cff36
                        }
                    }
                }
            }
        }
    }

}
