import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SudokuView extends JFrame {
    private GrilleSudoku grille;
    public JButton[][] boutons;

    public JButton getBouton(int ligne, int colonne) {
    return boutons[ligne][colonne];
}



    public SudokuView(GrilleSudoku grille) {
        super("Sudoku");
        this.grille = grille;

        // Création des boutons pour chaque cellule de la grille
        boutons = new JButton[9][9];
        JPanel grillePanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JPanel sousGrillePanel = new JPanel(new GridLayout(3, 3));
                Border border = BorderFactory.createLineBorder(Color.BLACK, 5); // Bordure de largeur 5
                sousGrillePanel.setBorder(border);
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        boutons[i * 3 + k][j * 3 + l] = new JButton();
                        boutons[i * 3 + k][j * 3 + l].setFont(new Font("Arial", Font.PLAIN, 20));
                        boutons[i * 3 + k][j * 3 + l].setPreferredSize(new Dimension(50, 50));
                        boutons[i * 3 + k][j * 3 + l].setBackground(Color.WHITE); // Couleur blanche
                        sousGrillePanel.add(boutons[i * 3 + k][j * 3 + l]);
                    }
                }
                grillePanel.add(sousGrillePanel);
            }
        }

        // Configuration de la fenêtre
        setLayout(new BorderLayout());
        add(grillePanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Ajuster la taille de la fenêtre pour qu'elle s'adapte au contenu
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void mettreAJour() {
        // Mettre à jour l'affichage des boutons en fonction de la grille
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boutons[i][j].setText(Integer.toString(grille.getCellule(i, j).getValeur()));
            }
        }
    }
}
