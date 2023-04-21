package com.example.bataillefx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe appelee au demarrage du programme pour charger la scene menu.
 */
public class HelloApplication extends Application {
    static Stage stage;
    static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneMenu.fxml"));
        scene = new Scene(fxmlLoader.load(), 800, 800);
        HelloApplication.stage.setResizable(false);
        HelloApplication.stage.setTitle("Hello!");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }

    /**
     * Fonction permettant de changer de scene.
     * @param sceneACharger String contenant le nom de la scene a charger.
     * @throws IOException
     */
    public static void RechargerScene(String sceneACharger) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(sceneACharger + ".fxml"));
        scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}