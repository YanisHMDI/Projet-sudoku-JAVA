import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class SudokuInterface extends JFrame {
    private GrilleSudoku grille;
    private JButton[][] boutons;
    private JButton jouerButton;
    private JButton resoudreButton;
    private JButton sauvegarderButton;
    private JButton chargerButton;
    private SudokuController controller;

    public SudokuInterface() {
        super("Sudoku");

        // Création de la grille de Sudoku vide
        grille = SudokuGenerator.genererGrille(); // Générer une grille Sudoku aléatoire

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
        jouerButton = new JButton("Jouer");
        resoudreButton = new JButton("Résoudre");
        sauvegarderButton = new JButton("Sauvegarder");
        chargerButton = new JButton("Charger");

        // Initialisation du contrôleur
        controller = new SudokuController(grille, this);

         // Configuration de la fenêtre
        setLayout(new BorderLayout());
        add(grillePanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(jouerButton);
        buttonPanel.add(resoudreButton);
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

    public void addListenerToButtons(ActionListener listener) {
        for (JButton[] row : boutons) {
            for (JButton button : row) {
                button.addActionListener(listener);
            }
        }
    }

    public JButton getJouerButton() {
        return jouerButton;
    }

    public JButton getResoudreButton() {
        return resoudreButton;
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
