package com.example.tp3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ControlleurMenu {

    @FXML
    void DemarrerPartie(ActionEvent evenement) throws IOException {
        HelloApplication.ChangerScene("Jeu");
        HelloController.InitialiserImages();
    }
}
