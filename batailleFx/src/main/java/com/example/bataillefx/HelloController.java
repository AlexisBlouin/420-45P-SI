package com.example.bataillefx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML
    public GridPane grilleJoueur;

    public void clickGrid(javafx.scene.input.MouseEvent event) {
        Node source = (Node)event.getSource() ;
        Integer colIndex = grilleJoueur.getColumnIndex(source);
        Integer rowIndex = grilleJoueur.getRowIndex(source);
        System.out.println("Mouse clicked cell: " + colIndex + "And: " + rowIndex);
    }
}