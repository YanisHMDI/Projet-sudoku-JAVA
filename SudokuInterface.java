import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class SudokuInterface extends JFrame {
    private GrilleSudoku grille;
    private JButton[][] boutons;
    private JButton jouerButton;
    private JButton resoudreButton;

    public SudokuInterface() {
        super("Sudoku");

        // Création de la grille de Sudoku vide
        grille = new GrilleSudoku();

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

        // Création du bouton "Jouer"
        jouerButton = new JButton("Jouer");

        // Création du bouton "Résoudre"
        resoudreButton = new JButton("Résoudre");

        // Configuration de la fenêtre
        setLayout(new BorderLayout());
        add(grillePanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(jouerButton);
        buttonPanel.add(resoudreButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Ajuster la taille de la fenêtre pour qu'elle s'adapte au contenu
        setLocationRelativeTo(null);
        setVisible(true);

        // Création du contrôleur
        SudokuController controller = new SudokuController(grille, boutons);
        jouerButton.addActionListener(controller);
        resoudreButton.addActionListener(controller);
    }
}