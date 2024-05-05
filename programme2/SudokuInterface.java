import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class SudokuInterface extends JFrame {
    private GrilleSudoku grille;
    private JButton[][] boutons;
    private JButton chargerButton;
    private boolean modeManuel = false;

    public SudokuInterface(GrilleSudoku grille) {
        super("Sudoku");
        this.grille = grille;

        boutons = new JButton[9][9];
        // Initialisation de l'interface graphique
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

                        // Ajout d'un écouteur d'événements à chaque bouton pour le mode manuel
                        boutons[i * 3 + k][j * 3 + l].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (modeManuel) {
                                    JButton selectedButton = (JButton) e.getSource();
                                    int row = (int) selectedButton.getClientProperty("row");
                                    int col = (int) selectedButton.getClientProperty("col");

                                    // Demander à l'utilisateur d'entrer une valeur
                                    String inputValue = JOptionPane.showInputDialog("Entrez un nombre entre 1 et 9 :");

                                    // Vérifier si l'entrée n'est pas nulle
                                    if (inputValue != null && !inputValue.isEmpty()) {
                                        try {
                                            int value = Integer.parseInt(inputValue);
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
                            }
                        });

                        sousGrillePanel.add(boutons[i * 3 + k][j * 3 + l]);
                    }
                }
                grillePanel.add(sousGrillePanel);
            }
        }

        chargerButton = new JButton("Charger");

        // Ajout du bouton de chargement
        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));
        buttonPanel.add(chargerButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ajout du grillePanel à la fenêtre principale
        add(grillePanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Ajout de l'action pour le bouton de chargement
        chargerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int choice = fileChooser.showOpenDialog(SudokuInterface.this);
                if (choice == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    chargerGrille(file);
                }
            }
        });
    }

    private void chargerGrille(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            grille = (GrilleSudoku) inputStream.readObject();
            refreshUI();
            System.out.println("Grille chargée avec succès.");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement de la grille : " + ex.getMessage());
        }
    }

    public void refreshUI() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Cellule cellule = grille.getCellule(i, j);
                String valeur = cellule.estVide() ? "" : Integer.toString(cellule.getValeur());
                boutons[i][j].setText(valeur);
            }
        }
    }

    // Méthode pour activer le mode manuel
    public void setModeManuel(boolean mode) {
        this.modeManuel = mode;
    }
}
