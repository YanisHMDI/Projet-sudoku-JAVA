import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SudokuController implements ActionListener {
    private SudokuSolver solver;
    private SudokuInterface sudokuInterface;
    private boolean modeAutomatique;

    public SudokuController(GrilleSudoku grille, SudokuInterface sudokuInterface, boolean modeAutomatique) {
        this.sudokuInterface = sudokuInterface;
        this.modeAutomatique = modeAutomatique;

        if (modeAutomatique) {
            solver = new AutomatiqueSolver(grille);
        } else {
            solver = new ManuelSolver(grille, sudokuInterface);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Vérifier si le bouton cliqué est celui de chargement
        if (e.getSource() == sudokuInterface.getChargerButton()) {
            // Charger la grille depuis un fichier
            JFileChooser fileChooser = new JFileChooser();
            int choice = fileChooser.showOpenDialog(sudokuInterface);
            if (choice == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                chargerGrille(file);
            }
        } else {
            // Si ce n'est pas le bouton de chargement, traiter l'action en fonction du mode
            if (modeAutomatique) {
                // Mode automatique : résoudre automatiquement le Sudoku
                solver.resoudre();
            } else {
                // Mode manuel : l'utilisateur résout le Sudoku en cliquant sur les boutons de la grille
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
                            if (solver.grille.estValide(row, col, value)) {
                                // Mettre à jour la valeur de la cellule dans la grille
                                solver.grille.setCellule(row, col, value);
                                // Mettre à jour l'affichage du bouton
                                selectedButton.setText(inputValue);

                                // Vérifier si la grille est complète
                                if (solver.grille.estComplet()) {
                                    JOptionPane.showMessageDialog(null, "Félicitations ! Vous avez résolu le Sudoku !");
                                }
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
        }
    }

    private void chargerGrille(File file) {
        try {
            GrilleSudoku grille = SudokuFileHandler.chargerGrille(file.getAbsolutePath());
            solver.grille = grille; // Mettre à jour la grille du solveur
            sudokuInterface.refreshUI(); // Mettre à jour l'interface utilisateur
            JOptionPane.showMessageDialog(null, "Grille chargée avec succès.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement de la grille : " + ex.getMessage());
        }
    }
}
