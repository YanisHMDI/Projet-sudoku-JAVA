import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SudokuInterface extends JFrame {
    private GrilleSudoku grille;
    private JButton[][] boutons;
    private JButton sauvegarderButton;
    private JButton chargerButton;
    private SudokuController controller;

    public SudokuInterface(GrilleSudoku grille) {
        super("Sudoku");

        // Initialisation de la grille Sudoku avec la grille passée en argument
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
                        // Ajout des coordonnées comme attribut du bouton
                        boutons[i * 3 + k][j * 3 + l].putClientProperty("row", i * 3 + k);
                        boutons[i * 3 + k][j * 3 + l].putClientProperty("col", j * 3 + l);

                        sousGrillePanel.add(boutons[i * 3 + k][j * 3 + l]);
                    }
                }
                grillePanel.add(sousGrillePanel);
            }
        }

        // Création des boutons pour les fonctionnalités supplémentaires
        sauvegarderButton = new JButton("Sauvegarder");
        chargerButton = new JButton("Charger");

        // Configuration de la fenêtre
        setLayout(new BorderLayout());
        add(grillePanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(sauvegarderButton);
        buttonPanel.add(chargerButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JButton[][] getBoutons() {
        return boutons;
    }

    public GrilleSudoku getGrille() {
        return grille;
    }

    public JButton getSauvegarderButton() {
        return sauvegarderButton;
    }

    public JButton getChargerButton() {
        return chargerButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuInterface::new);
    }
}