import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuController implements ActionListener {
    private GrilleSudoku grille;
    private SudokuInterface sudokuInterface;

    public SudokuController(GrilleSudoku grille, SudokuInterface sudokuInterface) {
        this.grille = grille;
        this.sudokuInterface = sudokuInterface;

        sudokuInterface.getJouerButton().addActionListener(this);
        sudokuInterface.getResoudreButton().addActionListener(this);
        sudokuInterface.getSauvegarderButton().addActionListener(this);
        sudokuInterface.getChargerButton().addActionListener(this);
        sudokuInterface.addListenerToButtons(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sudokuInterface.getJouerButton()) {
            // Logique pour commencer à jouer
            grille.viderGrille(); // Réinitialiser la grille
            mettreAJourAffichageGrille();
        } else if (e.getSource() == sudokuInterface.getResoudreButton()) {
            // Logique pour résoudre la grille automatiquement
            SudokuSolver.solve(grille); // Résoudre la grille
            mettreAJourAffichageGrille();
        } else if (e.getSource() == sudokuInterface.getSauvegarderButton()) {
            // Logique pour sauvegarder la grille dans un fichier
            SudokuFileIO.sauvegarderGrille(grille); // Sauvegarder la grille
        } else if (e.getSource() == sudokuInterface.getChargerButton()) {
            // Logique pour charger une grille à partir d'un fichier
            grille = SudokuFileIO.chargerGrille(); // Charger la grille
            mettreAJourAffichageGrille();
        } else {
            // Logique pour sélectionner une cellule et entrer une valeur
            JButton selectedButton = (JButton) e.getSource();

            // Récupérer les coordonnées de la case sélectionnée à partir du bouton cliqué
            int row = (int) selectedButton.getClientProperty("row");
            int col = (int) selectedButton.getClientProperty("col");

            // Demander à l'utilisateur d'entrer une valeur
            String inputValue = JOptionPane.showInputDialog("Enter a number:");
            // Vérifier si l'entrée n'est pas nulle
            if (inputValue != null && !inputValue.isEmpty()) {
                try {
                    // Convertir la valeur en entier
                    int value = Integer.parseInt(inputValue);
                    // Vérifier la validité de la valeur avec la méthode estValide
                    boolean isValid = grille.estValide(row, col, value);
                    if (!isValid) {
                        // Afficher un message d'erreur approprié
                        JOptionPane.showMessageDialog(null, "Impossible d'ajouter le chiffre " + value + " à cet emplacement.");
                    } else {
                        // Mettre à jour la valeur de la cellule dans la grille
                        grille.setCellule(row, col, value);
                        // Mettre à jour l'affichage du bouton
                        selectedButton.setText(inputValue);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number format!");
                }
            }

        }
    }

    // Méthode pour mettre à jour l'affichage de la grille
    private void mettreAJourAffichageGrille() {
        // Mettre à jour l'affichage des boutons dans l'interface utilisateur
        JButton[][] boutons = sudokuInterface.getBoutons();
        for (int i = 0; i < boutons.length; i++) {
            for (int j = 0; j < boutons[i].length; j++) {
                int valeur = grille.getCellule(i, j).getValeur();
                boutons[i][j].setText(valeur == 0 ? "" : String.valueOf(valeur));
            }
        }
    }
}