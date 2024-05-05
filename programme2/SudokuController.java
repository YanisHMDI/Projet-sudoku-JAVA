import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class SudokuController implements ActionListener {
    private GrilleSudoku grille;
    private SudokuInterface sudokuInterface;
    private SudokuSolver solver;

    public SudokuController(GrilleSudoku grille, SudokuInterface sudokuInterface) {
        this.grille = grille;
        this.sudokuInterface = sudokuInterface;
        this.solver = new AutomatiqueSolver(grille);

        sudokuInterface.getResoudreButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resoudreGrille();
            }
        });

        for (JButton[] row : sudokuInterface.getBoutons()) {
            for (JButton button : row) {
                button.addActionListener(this); // Ajout de l'action listener pour chaque bouton de la grille
            }
        }
        choisirMode();
    }

    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton selectedButton = (JButton) e.getSource();

        // Récupérer les coordonnées de la case sélectionnée à partir du bouton cliqué
        int row = (int) selectedButton.getClientProperty("row");
        int col = (int) selectedButton.getClientProperty("col");

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
                    if (grille.estValide(row, col, value)) {
                        // Mettre à jour la valeur de la cellule dans la grille
                        grille.setCellule(row, col, value);
                        // Mettre à jour l'affichage du bouton
                        selectedButton.setText(inputValue);
                    } else {
                        JOptionPane.showMessageDialog(null, "Nombre invalide ! Veuillez entrer un nombre valide.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Le nombre doit être compris entre 1 et 9.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Format de nombre invalide ! Veuillez entrer un nombre valide.");
            }
        }
    }

    private void resoudreGrille() {
        if (solver.resoudre()) { // Appel de la méthode de résolution automatique
            sudokuInterface.refreshUI(); // Mettre à jour l'interface utilisateur avec la grille résolue
            JOptionPane.showMessageDialog(sudokuInterface, "Grille résolue avec succès !");
        } else {
            JOptionPane.showMessageDialog(sudokuInterface, "La grille n'a pas de solution.");
        }
    }

    private void choisirMode() {
        // Afficher un menu pour choisir le mode (manuel ou automatique)
        int choix = JOptionPane.showOptionDialog(null, "Choisissez le mode de jeu", "Mode de jeu",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[] { "Manuel", "Automatique" }, "Manuel");

        if (choix == JOptionPane.YES_OPTION) {
            // Mode manuel
            grille = new GrilleSudoku();
        } else {
            // Mode automatique
            grille = new GrilleSudoku();
            // Logique pour générer une grille automatiquement
            grille.resoudre(); // Par exemple
        }
    }

}
