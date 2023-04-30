package com.example.tp3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

public class ControlleurMenu {

    static Media son = new Media(new File("C:\\Users\\Alexis\\Desktop\\Git\\420-45P-SI\\TP3\\src\\main\\java\\com\\example\\tp3\\Sons\\Windows Shutdown.wav").toURI().toString());
    static MediaPlayer sonBouton = new MediaPlayer(son);

    @FXML
    void DemarrerPartie(ActionEvent evenement) throws IOException {
        HelloApplication.ChangerScene("Jeu");
        //HelloController.InitialiserImages();
        sonBouton.play();
    }
}
