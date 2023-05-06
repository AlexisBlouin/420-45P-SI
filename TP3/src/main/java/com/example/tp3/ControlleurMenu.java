package com.example.tp3;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Classe permettant de controller la scène du menu.
 */
public class ControlleurMenu {

    /**
     * Cette fonction commence une partie lorsqu'elle est appelée.
     * @param evenement Évènement de la souris contenant les informations sur le clic de l'utilisateur dont la ligne et la colonne du clic.
     * @throws IOException
     */
    @FXML
    void demarrerPartie(ActionEvent evenement) throws IOException {
        HelloApplication.changerScene("Jeu");
        HelloApplication.sonBouton.seek(Duration.ZERO);
        HelloApplication.sonBouton.play();
    }

    /**
     * Cette fonction quitte le jeu.
     */
    @FXML
    void quitterPartie(){
        Platform.exit();
    }
}
