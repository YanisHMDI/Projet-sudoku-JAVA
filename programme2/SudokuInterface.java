import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class SudokuInterface extends JFrame {
    private GrilleSudoku grille;
    private JButton[][] boutons;
    private JButton sauvegarderButton;
    private JButton chargerButton;

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

        sauvegarderButton = new JButton("Sauvegarder");
        chargerButton = new JButton("Charger");

        // Ajout des boutons de sauvegarde et de chargement
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(sauvegarderButton);
        buttonPanel.add(chargerButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ajout du grillePanel à la fenêtre principale
        add(grillePanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Ajout des actions pour les boutons de sauvegarde et de chargement
        sauvegarderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int choice = fileChooser.showSaveDialog(SudokuInterface.this);
                if (choice == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    sauvegarderGrille(file);
                }
            }
        });

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

    private void sauvegarderGrille(File file) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(grille);
            System.out.println("Grille sauvegardée avec succès.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde de la grille : " + ex.getMessage());
        }
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

    private void refreshUI() {
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            Cellule cellule = grille.getCellule(i, j);
            String valeur = cellule.estVide() ? "" : Integer.toString(cellule.getValeur());
            boutons[i][j].setText(valeur);
        }
    }
}


    public JButton[][] getBoutons() {
        return boutons;
    }

    
}