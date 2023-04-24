package com.example.tp3;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class HelloController {
    AjouterMarqueur ajouterMarqueur = new AjouterMarqueur();
    AjouterMarqueurFX ajouterMarqueurFX = new AjouterMarqueurFX();
    GrilleJeu grilleJeu = new GrilleJeu();
    @FXML
    GridPane grilleDuJeu;
    ImageView imageO;
    ImageView imageX;

    @FXML
    void ClicGrille(MouseEvent evenement){
        Node source = (Node)evenement.getSource();
        Integer ligne = GridPane.getRowIndex(source);
        Integer colonne = GridPane.getColumnIndex(source);
        if(ligne == null){
            ligne = 0;
        }
        if(colonne == null){
            colonne = 0;
        }

        if(ajouterMarqueur.TesterEmplacement(grilleJeu.grille, ligne, colonne)){
            ajouterMarqueur.ModifierGrille(grilleJeu.grille, 1, ligne, colonne);
            //ajouterMarqueurFX.AjouterMarqueur(grilleJeu, 1, ligne, colonne);
            grilleJeu.AfficherGrille();
        }
        //System.out.println("x : " + ligne + ", y : " + colonne);
    }
}