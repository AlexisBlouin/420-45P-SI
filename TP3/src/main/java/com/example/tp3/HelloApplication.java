package com.example.tp3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Classe appelée au démarrage du programme pour charger la scene menu.
 */
public class HelloApplication extends Application {
    /**
     * Le stage contiendra toutes nos scènes
     */
    static Stage stage;
    /**
     * La scène pourra être réinitialisée pour en charger une nouvelle.
     */
    static Scene scene;

    /**
     * Variable utiliser pour initialiser le son du bouton.
     */
    static Media son;

    static {
        try {
            son = new Media(HelloApplication.class.getResource("Sons/Windows Shutdown.wav").toURI().toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Variable qui contiendra le son du bouton.
     */
    static MediaPlayer sonBouton = new MediaPlayer(son);

    /**
     * Fonction exécutée au démarrage pour charger la première scène.
     * @param stage Variable qui contiendra la scène chargée.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        scene = new Scene(fxmlLoader.load(), 800, 600);
        HelloApplication.stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Fonction permettant de changer de scene.
     * @param sceneACharger String contenant le nom de la scene a charger.
     * @throws IOException
     */
    public static void ChangerScene(String sceneACharger) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(sceneACharger + ".fxml"));
        scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}