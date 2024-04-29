import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SudokuSaver {
    public static void sauvegarderGrille(GrilleSudoku grille) {
        // Afficher une boîte de dialogue pour choisir l'emplacement et le nom du fichier
        JFileChooser fileChooser = new JFileChooser();
        int choix = fileChooser.showSaveDialog(null);
        
        if (choix == JFileChooser.APPROVE_OPTION) {
            File fichier = fileChooser.getSelectedFile();
            try {
                // Créer un FileWriter pour écrire dans le fichier
                FileWriter writer = new FileWriter(fichier);

                // Écrire la grille dans le fichier
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        writer.write(Integer.toString(grille.getCellule(i, j).getValeur()));
                    }
                    writer.write("\n");
                }

                // Fermer le FileWriter
                writer.close();
                
                JOptionPane.showMessageDialog(null, "Grille sauvegardée avec succès.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde du fichier.");
                e.printStackTrace();
            }
        }
    }
}
