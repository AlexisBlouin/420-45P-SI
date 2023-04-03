package com.example.bataillefx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    public GridPane grilleJoueurPlacement;
    public GridPane grilleJoueurJeu;
    public ImageView porteAvions1;
    public ImageView porteAvions2;
    public ImageView croiseur1;
    public ImageView croiseur2;
    public ImageView contreTorpilleur1;
    public ImageView contreTorpilleur2;
    public ImageView sousMarin1;
    public ImageView sousMarin2;
    public ImageView torpilleur1;
    public ImageView torpilleur2;
    private Stage stage;
    private Scene scene;
    private Parent root;
    int nombrePlace = 0;

    public Button boutonFinPlacement;

    Integer bateauChoisi = 0;
    int direction = 1;
    boolean initialisation = true;
    ImageView[][] bateaux;
    PlacerBateauFx pbf = new PlacerBateauFx();
    PlacerBateau pB = new PlacerBateau();

    public static int [][]grilleOrdiBackend = new int[10][10];
    public static int [][]grilleJoueurBackend = new int [10][10];

    public void clickGrid(javafx.scene.input.MouseEvent t) {
        Node source = (Node)t.getSource();
        System.out.println("X : " + t.getX() + ", Y : " + t.getY());
        Integer row = GridPane.getRowIndex(source);
        Integer column = GridPane.getColumnIndex(source);
        System.out.println("L : " + row + ", C : " + column);



        int[] longueurBateaux = {5, 4, 3, 3, 2};
        int[] numBateaux = {1, 2, 3, 4, 5};


        if(pB.posOk(grilleJoueurBackend, row, column, direction, longueurBateaux[bateauChoisi])){
            pbf.PlacerUnBateau(grilleJoueurPlacement, bateaux[bateauChoisi][direction -1], row, column);
            pB.ecrireDansGrille(grilleJoueurBackend, row, column, direction, longueurBateaux[bateauChoisi], numBateaux[bateauChoisi]);
            bateaux[bateauChoisi][0] = null;
            bateaux[bateauChoisi][1] = null;
            nombrePlace++;
            if(nombrePlace >= 5){
                boutonFinPlacement.setVisible(true);
            }
        }
    }

    public void tournerBateau(){
        //Fonction hide
        if(direction == 1){
            direction = 2;

            for(int i = 0; i < 5; i++){
                if(bateaux[i][1] != null){
                    bateaux[i][1].setVisible(true);
                }
            }

            for(int i = 0; i < 5; i++){
                if(bateaux[i][0] != null){
                    bateaux[i][0].setVisible(false);
                }
            }
        }
        else {
            direction = 1;

            for(int i = 0; i < 5; i++){
                if(bateaux[i][0] != null){
                    bateaux[i][0].setVisible(true);
                }
            }

            for(int i = 0; i < 5; i++){
                if(bateaux[i][1] != null){
                    bateaux[i][1].setVisible(false);
                }
            }
        }
    }

    public void ChoisirBateau(javafx.scene.input.MouseEvent t){
        Node source = (Node)t.getSource();
        bateauChoisi = GridPane.getColumnIndex(source);
        if(bateauChoisi == null){
            bateauChoisi = 0;
        }
        System.out.println(bateauChoisi);
    }

    public void InitialiserTableau(){
        if(initialisation){
            ImageView[][] tempo = {{porteAvions1, porteAvions2}, {croiseur1, croiseur2},
                    {contreTorpilleur1, contreTorpilleur2}, {sousMarin1, sousMarin2}, {torpilleur1, torpilleur2}};
            bateaux = tempo;
            initialisation = false;
        }
    }


    //Fait en partie avec : https://www.youtube.com/watch?v=hcM-R-YOKkQ
    public void ChangerScene1(ActionEvent event) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ScenePlacementBateaux.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setScene(scene);
        stage.show();
    }
    public void ChangerScene2(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setScene(scene);
        stage.show();
    }
    public void ChangerScene3(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneJeu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setScene(scene);
        stage.show();
    }
    public void MiseAJour(){
        System.out.println("Yo");
        grilleJoueurJeu = grilleJoueurPlacement;
    }
}