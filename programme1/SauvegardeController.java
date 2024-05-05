import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SauvegardeController {
    public void saveData(int[][] sudokuGridData, JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        writer.write(String.valueOf(sudokuGridData[i][j]));
                        // Ajouter un espace ou un autre caractère de séparation si nécessaire
                        // Par exemple, writer.write(" ") pour séparer les nombres par un espace
                    }
                    writer.newLine();
                }
                writer.close();
                System.out.println("Données sauvegardées avec succès.");
            } catch (IOException e) {
                System.err.println("Erreur lors de la sauvegarde des données : " + e.getMessage());
            }
        }
    }
}
