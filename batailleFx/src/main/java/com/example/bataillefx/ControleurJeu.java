package com.example.bataillefx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControleurJeu {
    private Stage stage;
    private Scene scene;
    private Parent root;
    GridPane grilleJoueur = new GridPane();

    void SetInformation(GridPane grilleJoueurPlacement){
        grilleJoueur = grilleJoueurPlacement;
    }
}
