package com.example.bataillefx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControleurMenu {
    private Stage stage;
    private Scene scene;
    private Parent root;
    ControlleurJeu controlleurJeu = new ControlleurJeu();
    @FXML
    Button bouttonPartieNormale;

    public void PartieNormalle(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneJeu.fxml"));
        stage = (Stage)bouttonPartieNormale.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setScene(scene);
        stage.show();
        controlleurJeu.tricheActive = false;
    }

    public void PartieTriche(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneJeu.fxml"));
        stage = (Stage)bouttonPartieNormale.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setScene(scene);
        stage.show();
        controlleurJeu.tricheActive = true;
    }

    public void Quitter(ActionEvent event) throws IOException{
        Platform.exit();
    }
}
