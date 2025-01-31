import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cellule implements Serializable {
    private int valeur;

    public Cellule() {
        this.valeur = 0; // Par défaut, une cellule est vide
    }

    public Cellule(int valeur) {
        if (valeur < 0 || valeur > 9) {
            throw new IllegalArgumentException("La valeur d'une cellule doit être entre 0 et 9 inclus.");
        }
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public boolean estVide() {
        return valeur == 0;
    }

    @Override
    public String toString() {
        return Integer.toString(valeur);
    }
}
