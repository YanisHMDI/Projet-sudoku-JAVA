import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;

public class ManuelSolver extends SudokuSolver {
    private SudokuInterface sudokuInterface;

    public ManuelSolver(GrilleSudoku grille, SudokuInterface sudokuInterface) {
        super(grille);
        this.sudokuInterface = sudokuInterface;
    }

    @Override
    public boolean resoudre() {
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
                        }
                    }
                }
            }
        }
    }

}
