package com.example.bataillefx;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlacerBateauFx {
    /*HelloController hc = new HelloController();
    ImageView[][] bateauxTempo = {{hc.porteAvions1, hc.porteAvions2}, {hc.croiseur1, hc.croiseur2},
            {hc.contreTorpilleur1, hc.contreTorpilleur2}, {hc.sousMarin1, hc.sousMarin2}, {hc.torpilleur1, hc.torpilleur2}};


    public ImageView placementBateau(int ligne, int colonne, int direction, int numBateau){
        return bateauxTempo[numBateau][direction - 1];
    }*/

    public void PlacerUnBateau(GridPane grille, ImageView bateauH, ImageView bateauV, int ligne, int colonne){
        grille.add(bateauH, colonne, ligne);
        grille.add(bateauV, colonne, ligne);
    }

    public void ReplacerUnBateau(GridPane grille, ImageView bateauH, ImageView bateauV, int ligne, int colonne){
        grille.getChildren().remove(bateauH);
        grille.add(bateauH, colonne, ligne);
        grille.getChildren().remove(bateauV);
        grille.add(bateauV, colonne, ligne);
    }

    public void marqueTouche(GridPane grille, int ligne, int colonne){
        Rectangle carre = new Rectangle(22, 22);
        Color couleur = Color.CADETBLUE;
        carre.setFill(couleur);
        grille.add(carre, ligne, colonne);
    }
}
