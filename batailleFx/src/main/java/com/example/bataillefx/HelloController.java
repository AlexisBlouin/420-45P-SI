package com.example.bataillefx;

import javafx.fxml.FXML;
import javafx.scene.Group;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML
    public GridPane grilleJoueur;
    public ImageView porteAvions1;
    public ImageView porteAvions2;
    public ImageView torpilleur1;
    public ImageView torpilleur2;

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

        if(direction == 1 && pB.posOk(grilleJoueurBackend, row - 1, column - 1, direction, 5)){
            grilleJoueur.add(porteAvions1, column, row);
        }
        else {
            grilleJoueur.add(porteAvions2, column, row);
        }
    }

    public void tournerBateau(){
        //Fonction hide
        if(direction == 1){
            direction = 2;
        }
        else {
            direction = 1;
        }
    }
}