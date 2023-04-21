package com.example.bataillefx;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Classe contenant les fonctions pour placer les bateaux dans les grilles frontends.
 */
public class PlacerBateauFx {

    /**
     * Fonction permettant d'ajouter un bateau dans la grille du joueur ou de l'ordi.
     * @param grille Grille dans laquelle il faut ajouter le bateau.
     * @param bateauH Image du bateau horizontale.
     * @param bateauV Image du bateau verticale.
     * @param ligne Ligne du placement du bateau.
     * @param colonne Colonne du placement du bateau.
     */
    public void PlacerUnBateau(GridPane grille, ImageView bateauH, ImageView bateauV, int ligne, int colonne){
        grille.add(bateauH, colonne, ligne);
        grille.add(bateauV, colonne, ligne);
    }

    /**
     * Fonction permettant de replacer un bateau dans la grille du joueur ou de l'ordi.
     * @param grille Grille dans laquelle il faut replacer le bateau.
     * @param bateauH Image du bateau horizontale.
     * @param bateauV Image du bateau verticale.
     * @param ligne Ligne du placement du bateau.
     * @param colonne Colonne du placement du bateau.
     */
    public void ReplacerUnBateau(GridPane grille, ImageView bateauH, ImageView bateauV, int ligne, int colonne){
        grille.getChildren().remove(bateauH);
        grille.add(bateauH, colonne, ligne);
        grille.getChildren().remove(bateauV);
        grille.add(bateauV, colonne, ligne);
    }

    /**
     * Fonction permettant d'ajouter un marqueur de tir identifiant si le tir est bon.
     * @param grille Grille dans laquelle il faut ajouter le marqueur
     * @param grilleBackend  Grille pour tester le resultat du tir.
     * @param ligne Ligne du tir.
     * @param colonne Colonne du tir.
     */
    public void MarquerTir(GridPane grille, int[][] grilleBackend, int ligne, int colonne){
        Rectangle carre = new Rectangle(22, 22);
        Color couleurDeTir;
        if(grilleBackend[colonne - 1][ligne - 1] == 0){
            couleurDeTir = Color.CADETBLUE;
            carre.setFill(couleurDeTir);
        }
        else{
            couleurDeTir = Color.RED;
            carre.setFill(couleurDeTir);
        }
        grille.add(carre, ligne, colonne);
    }
}
