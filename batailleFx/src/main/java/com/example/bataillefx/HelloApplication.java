package com.example.bataillefx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    static Stage currentStage;
    static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        currentStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneMenu.fxml"));
        scene = new Scene(fxmlLoader.load(), 800, 800);
        currentStage.setResizable(false);
        currentStage.setTitle("Hello!");
        currentStage.setScene(scene);
        currentStage.show();
    }

    /**
     * Fonction permettant de changer de scene.
     * @param sceneACharger String contenant le nom de la scene a charger.
     * @throws IOException
     */
    public static void RechargerScene(String sceneACharger) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(sceneACharger + ".fxml"));
        scene = new Scene(fxmlLoader.load(), 800, 800);
        currentStage.setResizable(false);
        currentStage.setTitle("Hello!");
        currentStage.setScene(scene);
        currentStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}