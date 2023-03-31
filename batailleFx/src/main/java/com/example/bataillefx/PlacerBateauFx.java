package com.example.bataillefx;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PlacerBateauFx {
    /*HelloController hc = new HelloController();
    ImageView[][] bateauxTempo = {{hc.porteAvions1, hc.porteAvions2}, {hc.croiseur1, hc.croiseur2},
            {hc.contreTorpilleur1, hc.contreTorpilleur2}, {hc.sousMarin1, hc.sousMarin2}, {hc.torpilleur1, hc.torpilleur2}};


    public ImageView placementBateau(int ligne, int colonne, int direction, int numBateau){
        return bateauxTempo[numBateau][direction - 1];
    }*/

    public void PlacerUnBateau(GridPane grille, ImageView bateau, int ligne, int colonne){
        grille.add(bateau, colonne, ligne);
    }
}
