import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class SudokuInterface extends JFrame {
    private GrilleSudoku grille;
    private JButton[][] boutons;
    private JButton chargerButton;
    private JButton resoudreButton;
    private JButton reinitialiserButton;
    private GrilleSudoku grilleChargee;

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

                        sousGrillePanel.add(boutons[i * 3 + k][j * 3 + l]);
                    }
                }
                grillePanel.add(sousGrillePanel);
            }
        }

        chargerButton = new JButton("Charger");
        resoudreButton = new JButton("Résoudre");
        reinitialiserButton = new JButton("Réinitialiser");

        // Ajout du bouton de chargement
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3)); // Modifié pour 3 colonnes
        buttonPanel.add(chargerButton);
        buttonPanel.add(resoudreButton);
        buttonPanel.add(reinitialiserButton);
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

        // Ajout de l'action pour le bouton "Résoudre"
        resoudreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resoudreGrille();
            }
        });

        reinitialiserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grille.reinitialiser(); // Réinitialisation de la grille
                refreshUI(); // Mise à jour de l'interface utilisateur
            }
        });
    }

    public void desactiverEditionGrilleChargee() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!grilleChargee.getCellule(i, j).estVide()) {
                    boutons[i][j].setEnabled(false); // Désactiver l'édition des cellules chargées
                }
            }
        }
    }

    private void chargerGrille(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            grilleChargee = (GrilleSudoku) inputStream.readObject(); // Stocker la grille chargée
            grille = grilleChargee; // Mettre à jour la grille affichée
            refreshUI();
            System.out.println("Grille chargée avec succès.");

            desactiverEditionGrilleChargee();

        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement de la grille : " + ex.getMessage());
        }
    }

    private void resoudreGrille() {
        if (grille == null) {
            JOptionPane.showMessageDialog(this, "Veuillez charger une grille avant de la résoudre.");
            return;
        }
        if (!grille.resoudre()) {
            JOptionPane.showMessageDialog(this, "La grille chargée n'a pas de solution.");
        }
        refreshUI(); // Mettre à jour l'interface utilisateur après la résolution
    }

    public void refreshUI() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int valeur = grille.getCellule(i, j).getValeur();
                boutons[i][j].setText(valeur == 0 ? "" : Integer.toString(valeur));
            }
        }
    }

    public JButton[][] getBoutons() {
        return boutons;
    }

    public JButton getChargerButton() {
        return chargerButton;
    }

    public JButton getResoudreButton() {
        return resoudreButton;
    }
}
