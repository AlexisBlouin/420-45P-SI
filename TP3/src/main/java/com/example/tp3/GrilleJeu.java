package com.example.tp3;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GrilleJeu {
    int[][] grille = new int[3][3];
    GridPane grilleVisuelle;
    ImageView gifO;
    ImageView gifX;

    /*GrilleJeu(GridPane grille, ImageView imageO, ImageView imageX){
        grilleVisuelle = grille;
        gifO = imageO;
        gifX = imageX;
    }*/

    public void AfficherGrille(){
        for(int i = 0; i < 3; i++){
            for(int ii = 0; ii < 3; ii++){
                System.out.print(grille[i][ii] + " ");
            }
            System.out.println();
        }
    }
}
