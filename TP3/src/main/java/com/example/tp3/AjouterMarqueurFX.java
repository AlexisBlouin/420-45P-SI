package com.example.tp3;

import javafx.scene.layout.GridPane;

public class AjouterMarqueurFX {

    public void AjouterMarqueur(GrilleJeu grille, int num, int x, int y){
        grille.grilleVisuelle.add(grille.gifO, x, y);
    }
}
