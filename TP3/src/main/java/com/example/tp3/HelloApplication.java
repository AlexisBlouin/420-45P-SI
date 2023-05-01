package com.example.tp3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    static Stage stage;
    static Scene scene;

    static Media son = new Media(new File("C:\\Users\\bloa\\Desktop\\Git\\420-45P-SI\\TP3\\src\\main\\java\\com\\example\\tp3\\Sons\\Windows Shutdown.wav").toURI().toString());
    static MediaPlayer sonBouton = new MediaPlayer(son);

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