package com.example.bataillefx;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * Controleur permettant de controller les actions des boutons de la scene du menu.
 */
public class ControleurMenu {

    /**
     * Variable du ControleurJeu pour acceder a certaines variables.
     */
    ControlleurJeu controlleurJeu = new ControlleurJeu();

    /**
     * Fonction demarrant une partie normale.
     * @param event Evenement de la souris contenant les informations sur le clic de l'utilisateur.
     * @throws IOException
     */
    public void PartieNormalle(ActionEvent event) throws IOException {

        controlleurJeu.tricheActive = false;
        HelloApplication.RechargerScene("SceneJeu");
    }

    /**
     * Fonction demarrant une partie avec triche.
     * @param event Evenement de la souris contenant les informations sur le clic de l'utilisateur.
     * @throws IOException
     */
    public void PartieTriche(ActionEvent event) throws IOException{
        controlleurJeu.tricheActive = true;
        HelloApplication.RechargerScene("SceneJeu");
    }

    /**
     * Fonction permettant de quitter le programme.
     * @param event Evenement de la souris contenant les informations sur le clic de l'utilisateur.
     * @throws IOException
     */
    public void Quitter(ActionEvent event) throws IOException{
        Platform.exit();
    }
}
