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
    public ImageView porteAvions;
    public ImageView torpilleur;

    public void clickGrid(javafx.scene.input.MouseEvent t) {
        Node source = (Node)t.getSource();
        System.out.println("X : " + t.getX() + ", Y : " + t.getY());
        Integer row = GridPane.getRowIndex(source);
        Integer column = GridPane.getColumnIndex(source);
        System.out.println("L : " + row + ", C : " + column);

        Rectangle carreBleu = new Rectangle(0, 0, grilleJoueur.getWidth()/11, grilleJoueur.getHeight()/11);
        carreBleu.setFill(Color.CADETBLUE);

        //grilleJoueur.add(carreBleu, column, row);
        grilleJoueur.add(porteAvions, column, row);
    }

    public void tournerBateau(){
        if(porteAvions.getRotate() == 0){
            porteAvions.setRotate(90);
        }
        else {
            porteAvions.setRotate(0);
        }
    }
}