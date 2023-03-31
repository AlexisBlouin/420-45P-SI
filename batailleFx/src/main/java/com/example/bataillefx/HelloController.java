package com.example.bataillefx;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML
    public GridPane grilleJoueur;
    public ImageView porteAvions1;
    public ImageView porteAvions2;
    public ImageView croiseur1;
    public ImageView croiseur2;
    public ImageView contreTorpilleur1;
    public ImageView contreTorpilleur2;
    public ImageView sousMarin1;
    public ImageView sousMarin2;
    public ImageView torpilleur1;
    public ImageView torpilleur2;

    Integer bateauChoisi = 0;
    int direction = 1;
    boolean initialisation = true;
    ImageView[][] bateaux;
    PlacerBateauFx pbf = new PlacerBateauFx();
    PlacerBateau pB = new PlacerBateau();

    public static int [][]grilleOrdiBackend = new int[10][10];
    public static int [][]grilleJoueurBackend = new int [10][10];

    public void clickGrid(javafx.scene.input.MouseEvent t) {
        Node source = (Node)t.getSource();
        System.out.println("X : " + t.getX() + ", Y : " + t.getY());
        Integer row = GridPane.getRowIndex(source);
        Integer column = GridPane.getColumnIndex(source);
        System.out.println("L : " + row + ", C : " + column);

        InitialiserTableau();

        int[] longueurBateaux = {5, 4, 3, 3, 2};
        int[] numBateaux = {1, 2, 3, 4, 5};


        if(pB.posOk(grilleJoueurBackend, row, column, direction, longueurBateaux[bateauChoisi])){
            System.out.println("oui");
            pbf.PlacerUnBateau(grilleJoueur, bateaux[bateauChoisi][direction -1], row, column);
            pB.ecrireDansGrille(grilleJoueurBackend, row, column, direction, longueurBateaux[bateauChoisi], numBateaux[bateauChoisi]);
        }
    }

    public void tournerBateau(){
        //Fonction hide
        if(direction == 1){
            direction = 2;
            porteAvions2.setVisible(true);
            croiseur2.setVisible(true);
            contreTorpilleur2.setVisible(true);
            sousMarin2.setVisible(true);
            torpilleur2.setVisible(true);

            porteAvions1.setVisible(false);
            croiseur1.setVisible(false);
            contreTorpilleur1.setVisible(false);
            sousMarin1.setVisible(false);
            torpilleur1.setVisible(false);
        }
        else {
            direction = 1;
            porteAvions1.setVisible(true);
            croiseur1.setVisible(true);
            contreTorpilleur1.setVisible(true);
            sousMarin1.setVisible(true);
            torpilleur1.setVisible(true);

            porteAvions2.setVisible(false);
            croiseur2.setVisible(false);
            contreTorpilleur2.setVisible(false);
            sousMarin2.setVisible(false);
            torpilleur2.setVisible(false);
        }
    }

    //PlacerBateauFx pbf = new PlacerBateauFx();

    public void ChoisirBateau(javafx.scene.input.MouseEvent t){

        System.out.println("OUI");
        Node source = (Node)t.getSource();
        bateauChoisi = GridPane.getColumnIndex(source);
        if(bateauChoisi == null){
            bateauChoisi = 0;
        }
        System.out.println(bateauChoisi);
    }

    public void InitialiserTableau(){
        if(initialisation){
            ImageView[][] tempo = {{porteAvions1, porteAvions2}, {croiseur1, croiseur2},
                    {contreTorpilleur1, contreTorpilleur2}, {sousMarin1, sousMarin2}, {torpilleur1, torpilleur2}};
            bateaux = tempo;
            initialisation = false;
        }
    }
}