package com.example.tp3;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Classe permettant de controller la scène du menu.
 */
public class ControlleurMenu {

    /**
     * Cette fonction commence une partie lorsqu'elle est appelée.
     * @param evenement Évènement de la souris contenant les informations sur le clic de l'utilisateur dont la ligne et la colonne du clic.
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    void DemarrerPartie(ActionEvent evenement) throws IOException, URISyntaxException {
        HelloApplication.ChangerScene("Jeu");
        //HelloController.InitialiserImages();
        HelloApplication.sonBouton.seek(Duration.ZERO);
        HelloApplication.sonBouton.play();
        //HelloController.InitialiserJeu();
    }

    /**
     * Cette fonction quitte le jeu.
     */
    @FXML
    void QuitterPartie(){
        Platform.exit();
    }
}
