package com.example.tp3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HelloController {
    AjouterMarqueur ajouterMarqueur = new AjouterMarqueur();
    AjouterMarqueurFX ajouterMarqueurFX = new AjouterMarqueurFX();
    static GrilleJeu grilleJeu = new GrilleJeu();
    static InputStream stream;
    static ImageView imageO = new ImageView();
    static ImageView imageX = new ImageView();
    @FXML
    GridPane grilleDuJeu;

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
            //ajouterMarqueurFX.AjouterMarqueur(grilleDuJeu, grilleJeu, 1, ligne, colonne);
            grilleDuJeu.add(grilleJeu.gifO, ligne, colonne);
            grilleJeu.AfficherGrille();
        }
        //System.out.println("x : " + ligne + ", y : " + colonne);
    }

    public static void InitialiserImages() throws IOException {
        stream = new FileInputStream("C:\\Users\\Alexis\\Desktop\\Git\\420-45P-SI\\TP3\\src\\main\\java\\com\\example\\tp3\\Images\\LettreO.gif");
        Image tempo = new Image(stream);
        imageO.setImage(tempo);
        stream = new FileInputStream("C:\\Users\\Alexis\\Desktop\\Git\\420-45P-SI\\TP3\\src\\main\\java\\com\\example\\tp3\\Images\\LettreX.gif");
        tempo = new Image(stream);
        imageX.setImage(tempo);
        //grilleJeu.SetImages(grilleDuJeu, imageO, imageX);
        grilleJeu.SetImages(imageO, imageX);
    }
}