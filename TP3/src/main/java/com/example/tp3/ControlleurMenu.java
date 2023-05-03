package com.example.tp3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class ControlleurMenu {

    @FXML
    void DemarrerPartie(ActionEvent evenement) throws IOException, URISyntaxException {
        HelloApplication.ChangerScene("Jeu");
        //HelloController.InitialiserImages();
        HelloApplication.sonBouton.seek(Duration.ZERO);
        HelloApplication.sonBouton.play();
        //HelloController.InitialiserJeu();
    }
}
