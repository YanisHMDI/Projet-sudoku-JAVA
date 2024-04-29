import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuInterface extends JFrame implements ActionListener {
    private GrilleSudoku grille;
    private JPanel grillePanel;
    private JButton[][] boutons;
    private JButton jouerButton;
    private JButton resoudreButton;

    public SudokuInterface() {
        super("Sudoku");

        // Création de la grille de Sudoku vide
        grille = new GrilleSudoku();

        // Création des boutons pour chaque cellule de la grille
        boutons = new JButton[9][9];
        grillePanel = new JPanel(new GridLayout(3, 3));
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
                        boutons[i * 3 + k][j * 3 + l].addActionListener(this);
                        sousGrillePanel.add(boutons[i * 3 + k][j * 3 + l]);
                    }
                }
                grillePanel.add(sousGrillePanel);
            }
        }

        // Création du bouton "Jouer"
        jouerButton = new JButton("Jouer");
        jouerButton.addActionListener(this);

        // Création du bouton "Résoudre"
        resoudreButton = new JButton("Résoudre");
        resoudreButton.addActionListener(this);

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Action à effectuer lorsqu'un bouton est cliqué
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (e.getSource() == boutons[i][j]) {
                    // Ouvrir une boîte de dialogue pour permettre à l'utilisateur de saisir une valeur dans la cellule
                    String input = JOptionPane.showInputDialog(this, "Saisir une valeur pour la cellule [" + i + "][" + j + "]");
                    if (input != null && !input.isEmpty()) {
                        // Vérifier si la valeur saisie est un chiffre entre 1 et 9
                        try {
                            int value = Integer.parseInt(input);
                            if (value >= 1 && value <= 9) {
                                // Mettre à jour la valeur de la cellule dans la grille et sur le bouton correspondant
                                grille.setCellule(i, j, value);
                                boutons[i][j].setText(input);
                            } else {
                                JOptionPane.showMessageDialog(this, "Veuillez saisir un chiffre entre 1 et 9.");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Veuillez saisir un chiffre valide.");
                        }
                    } else {
                        // Effacer la valeur de la cellule si l'utilisateur a annulé la saisie
                        grille.setCellule(i, j, 0);
                        boutons[i][j].setText("");
                    }
                }
            }
        }

        // Action à effectuer lorsqu'on clique sur le bouton "Jouer"
        if (e.getSource() == jouerButton) {
            // Réinitialiser la grille et les boutons
            grille.viderGrille();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    boutons[i][j].setText("");
                }
            }
        }

        // Action à effectuer lorsqu'on clique sur le bouton "Résoudre"
        if (e.getSource() == resoudreButton) {
            // Résoudre la grille de Sudoku
            SudokuSolver.solve(grille);

            // Mettre à jour les boutons avec la solution
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    boutons[i][j].setText(Integer.toString(grille.getCellule(i, j).getValeur()));
                }
            }
        }
    }

    public static void main(String[] args) {
        // Créer et afficher l'interface utilisateur dans l'Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new SudokuInterface());
    }
}