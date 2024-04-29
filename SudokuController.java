import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class SudokuController implements ActionListener {
    private GrilleSudoku grille;
    private JButton[][] boutons;

    public SudokuController(GrilleSudoku grille, JButton[][] boutons) {
        this.grille = grille;
        this.boutons = boutons;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boutons[i][j].addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (e.getSource() == boutons[i][j]) {
                    String input = JOptionPane.showInputDialog("Saisir une valeur pour la cellule [" + i + "][" + j + "]");
                    if (input != null && !input.isEmpty()) {
                        try {
                            int value = Integer.parseInt(input);
                            if (value >= 1 && value <= 9) {
                                if (grille.estValide(i, j, value)) {
                                    grille.setCellule(i, j, value);
                                    boutons[i][j].setText(input);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ce nombre ne peut pas être placé ici !");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Veuillez saisir un chiffre entre 1 et 9.");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Veuillez saisir un chiffre valide.");
                        }
                    } else {
                        grille.setCellule(i, j, 0);
                        boutons[i][j].setText("");
                    }
                }
            }
        }
    }

    // Méthode pour sauvegarder la grille dans un fichier
    public void sauvegarderGrille(String cheminFichier) {
        try {
            SudokuFileHandler.sauvegarderGrille(grille, cheminFichier);
            JOptionPane.showMessageDialog(null, "Grille sauvegardée avec succès !");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde de la grille : " + e.getMessage());
        }
    }
}