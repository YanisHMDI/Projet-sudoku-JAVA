import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuInterface extends JFrame implements ActionListener {
    private GrilleSudoku grille;
    private JButton[][] boutons;
    private JButton jouerButton;
    private JButton resoudreButton;
    private JButton sauvegarderButton;
    private JButton chargerButton; // Bouton pour charger une partie
    private JFileChooser fileChooser;
    private int selectedRow = -1;
    private int selectedCol = -1;

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

        // Création du bouton "Sauvegarder"
        sauvegarderButton = new JButton("Sauvegarder");
        sauvegarderButton.addActionListener(this);

        // Création du bouton "Charger"
        chargerButton = new JButton("Charger");
        chargerButton.addActionListener(this);

        // Configuration de la fenêtre
        setLayout(new BorderLayout());
        add(grillePanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4)); // Ajout du quatrième bouton
        buttonPanel.add(jouerButton);
        buttonPanel.add(resoudreButton);
        buttonPanel.add(sauvegarderButton);
        buttonPanel.add(chargerButton); // Ajout du bouton "Charger"
        add(buttonPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Ajuster la taille de la fenêtre pour qu'elle s'adapte au contenu
        setLocationRelativeTo(null);
        setVisible(true);

        // Initialisation du JFileChooser
        fileChooser = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Gestion des actions pour chaque bouton

        // Code pour charger une partie depuis un fichier
        if (e.getSource() == chargerButton) {
            // Afficher une boîte de dialogue pour choisir le fichier à charger
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                chargerPartie(selectedFile);
            }
        }

        // Code pour sauvegarder la partie dans un fichier
        else if (e.getSource() == sauvegarderButton) {
            // Afficher une boîte de dialogue pour choisir l'emplacement et le nom du fichier
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                sauvegarderPartie(selectedFile);
            }
        }

        // Code pour jouer la partie (à implémenter)
        else if (e.getSource() == jouerButton) {
            // Ajoutez ici la logique pour jouer la partie
        }

        // Code pour résoudre la partie (à implémenter)
        else if (e.getSource() == resoudreButton) {
            // Ajoutez ici la logique pour résoudre la partie
        }

       // Code pour sélectionner une case et saisir un chiffre
else {
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            if (e.getSource() == boutons[i][j]) {
                selectedRow = i;
                selectedCol = j;
                // Demander à l'utilisateur de saisir un chiffre
                String input = JOptionPane.showInputDialog(this, "Entrez un chiffre (1-9):");
                try {
                    int value = Integer.parseInt(input);
                    if (value >= 1 && value <= 9) {
                        // Mettre le chiffre dans la cellule correspondante de la grille
                        grille.setCellule(selectedRow, selectedCol, value);
                        // Mettre à jour le texte du bouton correspondant
                        boutons[selectedRow][selectedCol].setText(Integer.toString(value));
                    } else {
                        JOptionPane.showMessageDialog(this, "Veuillez entrer un chiffre entre 1 et 9.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                return;
            }
        }
    }
}

    }

    // Méthode pour charger une partie à partir d'un fichier
   private void chargerPartie(File file) {
    try {
        Scanner scanner = new Scanner(file);
        for (int i = 0; i < 9 && scanner.hasNextLine(); i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < 9 && j < line.length(); j++) {
                char c = line.charAt(j);
                if (Character.isDigit(c)) {
                    int value = Character.getNumericValue(c);
                    if (value >= 0 && value <= 9) {
                        grille.setCellule(i, j, value);
                        boutons[i][j].setText(value == 0 ? "" : Integer.toString(value));
                    } else {
                        JOptionPane.showMessageDialog(this, "La valeur de la cellule [" + i + "][" + j + "] doit être entre 0 et 9 inclus.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        scanner.close();
    } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(this, "Erreur lors du chargement de la partie : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}


    // Méthode pour sauvegarder une partie dans un fichier
    private void sauvegarderPartie(File file) {
        try {
            SudokuFileHandler.sauvegarderGrille(grille, file.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Partie sauvegardée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde de la partie : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Créer et afficher l'interface utilisateur dans l'Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new SudokuInterface());
    }
}
