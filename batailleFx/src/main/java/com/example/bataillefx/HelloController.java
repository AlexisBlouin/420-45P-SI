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
    @FXML
    public ImageView porteAvions1;
    @FXML
    public ImageView porteAvions2;
    @FXML
    public ImageView croiseur1;
    @FXML
    public ImageView croiseur2;
    @FXML
    public ImageView contreTorpilleur1;
    @FXML
    public ImageView contreTorpilleur2;
    @FXML
    public ImageView sousMarin1;
    @FXML
    public ImageView sousMarin2;
    @FXML
    public ImageView torpilleur1;
    @FXML
    public ImageView torpilleur2;

    Integer bateauChoisi = 0;

    public ImageView[][] bateaux = new ImageView[2][5];

    public static int [][]grilleOrdiBackend = new int[10][10];
    public static int [][]grilleJoueurBackend = new int [10][10];
    public int direction = 1;

    PlacerBateau pB = new PlacerBateau();

    public void clickGrid(javafx.scene.input.MouseEvent t) {
        Node source = (Node)t.getSource();
        System.out.println("X : " + t.getX() + ", Y : " + t.getY());
        Integer row = GridPane.getRowIndex(source);
        Integer column = GridPane.getColumnIndex(source);
        System.out.println("L : " + row + ", C : " + column);

        /*Rectangle carreBleu = new Rectangle(0, 0, grilleJoueur.getWidth()/11, grilleJoueur.getHeight()/11);
        carreBleu.setFill(Color.CADETBLUE);*/

        //grilleJoueur.add(carreBleu, column, row);
        //grilleJoueur.add(porteAvions, column, row);

        /*if(direction == 1 && pB.posOk(grilleJoueurBackend, row - 1, column - 1, direction, 5)){
            grilleJoueur.add(porteAvions1, column, row);
        }
        else {
            grilleJoueur.add(porteAvions2, column, row);
        }*/

        int[] u = {5, 4, 3, 3, 2};

        switch (bateauChoisi){
            case 0:
                if(pB.posOk(grilleJoueurBackend, row - 1, column - 1, direction, 5)){
                    if(direction == 1){
                        System.out.println("TEST");
                        System.out.println(bateaux[0][0]);
                        grilleJoueur.add(bateaux[0][0], column, row);
                        pB.ecrireDansGrille(grilleJoueurBackend, row - 1, column - 1, direction, u, 1);
                    }
                    else{
                        grilleJoueur.add(porteAvions2, column, row);
                        pB.ecrireDansGrille(grilleJoueurBackend, row - 1, column - 1, direction, u, 1);
                    }
                }
                System.out.println(bateauChoisi);
                break;

            case 1:
                if(pB.posOk(grilleJoueurBackend, row - 1, column - 1, direction, 4)){
                    if(direction == 1){
                        grilleJoueur.add(croiseur1, column, row);
                        pB.ecrireDansGrille(grilleJoueurBackend, row - 1, column - 1, direction, u, 2);
                    }
                    else{
                        grilleJoueur.add(croiseur2, column, row);
                        pB.ecrireDansGrille(grilleJoueurBackend, row - 1, column - 1, direction, u, 2);
                    }
                }
                System.out.println(bateauChoisi);
                break;

            case 2:
                if(pB.posOk(grilleJoueurBackend, row - 1, column - 1, direction, 3)){
                    if(direction == 1){
                        grilleJoueur.add(contreTorpilleur1, column, row);
                        pB.ecrireDansGrille(grilleJoueurBackend, row - 1, column - 1, direction, u, 3);
                    }
                    else{
                        grilleJoueur.add(contreTorpilleur2, column, row);
                        pB.ecrireDansGrille(grilleJoueurBackend, row - 1, column - 1, direction, u, 3);
                    }
                }
                System.out.println(bateauChoisi);
                break;

            case 3:
                if(pB.posOk(grilleJoueurBackend, row - 1, column - 1, direction, 3)){
                    if(direction == 1){
                        grilleJoueur.add(sousMarin1, column, row);
                        pB.ecrireDansGrille(grilleJoueurBackend, row - 1, column - 1, direction, u, 4);
                    }
                    else{
                        grilleJoueur.add(sousMarin2, column, row);
                        pB.ecrireDansGrille(grilleJoueurBackend, row - 1, column - 1, direction, u, 4);
                    }
                }
                System.out.println(bateauChoisi);
                break;

            case 4:
                if(pB.posOk(grilleJoueurBackend, row - 1, column - 1, direction, 2)){
                    if(direction == 1){
                        grilleJoueur.add(torpilleur1, column, row);
                        pB.ecrireDansGrille(grilleJoueurBackend, row - 1, column - 1, direction, u, 5);
                    }
                    else{
                        grilleJoueur.add(torpilleur2, column, row);
                        pB.ecrireDansGrille(grilleJoueurBackend, row - 1, column - 1, direction, u, 5);
                    }
                }
                System.out.println(bateauChoisi);
                break;
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

    PlacerBateauFx pbf = new PlacerBateauFx();

    public void ChoisirBateau(javafx.scene.input.MouseEvent t){
        bateaux = pbf.initTableauBateau();
        System.out.println("OUI");
        Node source = (Node)t.getSource();
        bateauChoisi = GridPane.getColumnIndex(source);
        if(bateauChoisi == null){
            bateauChoisi = 0;
        }
        System.out.println(bateauChoisi);
    }
}