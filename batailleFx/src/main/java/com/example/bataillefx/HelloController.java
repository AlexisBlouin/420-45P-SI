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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    public GridPane grilleJoueur;
    public GridPane grilleEnnemie;
    public GridPane grilleBoutons;
    public ImageView porteAvionsH;
    public ImageView porteAvionsV;
    public ImageView croiseurH;
    public ImageView croiseurV;
    public ImageView contreTorpilleurH;
    public ImageView contreTorpilleurV;
    public ImageView sousMarinH;
    public ImageView sousMarinV;
    public ImageView torpilleurH;
    public ImageView torpilleurV;
    ImageView[][] bateaux;
    int[][] positionbateaux = new int[5][3];

    public Button boutonFinPlacement;
    public Button boutonInitialisation;
    public Button boutonNouvellePartie;
    public Button boutonTourner;
    public Button confirmerPosition;
    public Text messagePlacement;
    public Text messageTour;

    int nombreBateauPlace = 0;
    Integer bateauChoisi = 0;
    int direction = 1;
    boolean initialisation = true;
    boolean tricheActive = false;
    PlacerBateauFx pbf = new PlacerBateauFx();
    PlacerBateau pB = new PlacerBateau();

    public static int [][]grilleOrdiBackend = new int[10][10];
    public static int [][]grilleJoueurBackend = new int [10][10];

    public void clickGrid(MouseEvent t) {
        Node source = (Node)t.getSource();
        System.out.println("X : " + t.getX() + ", Y : " + t.getY());
        Integer row = GridPane.getRowIndex(source);
        Integer column = GridPane.getColumnIndex(source);
        System.out.println("L : " + row + ", C : " + column);



        int[] longueurBateaux = {5, 4, 3, 3, 2};
        int[] numBateaux = {1, 2, 3, 4, 5};


        if(pB.posOk(grilleJoueurBackend, row, column, direction, longueurBateaux[bateauChoisi])){

            positionbateaux[bateauChoisi][0] = row - 1;
            positionbateaux[bateauChoisi][1] = column - 1;
            positionbateaux[bateauChoisi][2] = direction;
            pbf.PlacerUnBateau(grilleJoueur, bateaux[bateauChoisi][direction -1], row, column);

            //pB.ecrireDansGrille(grilleJoueurBackend, row, column, direction, longueurBateaux[bateauChoisi], numBateaux[bateauChoisi]);
            //bateaux[bateauChoisi][0] = null;
            //bateaux[bateauChoisi][1] = null;
            nombreBateauPlace++;
            if(nombreBateauPlace >= 5){
                messagePlacement.setVisible(true);
                boutonFinPlacement.setVisible(true);
                confirmerPosition.setVisible(true);
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

    public void ChoisirBateau(MouseEvent t){
        Node source = (Node)t.getSource();
        bateauChoisi = GridPane.getColumnIndex(source);
        if(bateauChoisi == null){
            bateauChoisi = 0;
        }
        System.out.println(bateauChoisi);
    }

    public void ConfirmerPosition(){
        //pB.ecrireDansGrille(grilleJoueurBackend, row, column, direction, longueurBateaux[bateauChoisi], numBateaux[bateauChoisi]);

        int[] longueurBateaux = {5, 4, 3, 3, 2};
        int[] numBateaux = {1, 2, 3, 4, 5};
        for(int i = 0; i < 5; i++){
            pB.ecrireDansGrille(grilleJoueurBackend, positionbateaux[i][0], positionbateaux[i][1], positionbateaux[i][2], longueurBateaux[i], numBateaux[i]);
        }
        pB.afficherGrille(grilleJoueurBackend);
    }

    public void tirCanon(MouseEvent evenement){
        Node source = (Node)evenement.getSource();
        //System.out.println("X : " + evenement.getX() + ", Y : " + evenement.getY());
        Integer row = GridPane.getRowIndex(source) - 1;
        Integer column = GridPane.getColumnIndex(source) - 1;
        System.out.println("L : " + row + ", C : " + column);
        TirCanon tirCanon = new TirCanon();

        String[] nomBateau = new String[] {"Porte-avions", "Croiseur", "Contre-torpilleur", "Sous-marin", "Torpilleur"};
        String nomBateauVise = "";
        if(grilleOrdiBackend[row][column] - 1 != -1 && grilleOrdiBackend[row][column] - 1 != 5){
            nomBateauVise = nomBateau[grilleOrdiBackend[row][column] - 1];
        }

        int resultat = tirCanon.mouvement(grilleOrdiBackend, row, column, "GrilleOrdi");
        System.out.println(resultat);
        switch (resultat){
            case 0 :
                System.out.println("A l'eau");
                break;
            case 1 :
                System.out.println("Touche : " + nomBateauVise);
                break;
            case 2:
                System.out.println("Tous les bateaux ne sont pas coules." + '\n');
                break;
            case 3 :
                System.out.println('\n' + "Vous avez gagne!" + '\n');
                System.out.println("Tous les bateaux ennemis sont coules : ");
                //afficherGrille(grilleOrdi);
                System.out.println("Voici ce qu'il reste de votre grille : ");
                //afficherGrille(grilleJeu);
                boutonNouvellePartie.setVisible(true);
                break;
        }
    }

    public void InitialiserTableau(){
        InitGrilleOrdi();
        if(initialisation){
            boutonInitialisation.setVisible(false);
            ImageView[][] tempo = {{porteAvionsH, porteAvionsV}, {croiseurH, croiseurV},
                    {contreTorpilleurH, contreTorpilleurV}, {sousMarinH, sousMarinV}, {torpilleurH, torpilleurV}};
            bateaux = tempo;
            initialisation = false;
        }

    }


    //Fait en partie avec : https://www.youtube.com/watch?v=hcM-R-YOKkQ
    public void AllerAuMenu(ActionEvent event) throws IOException{
        Stage stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setScene(scene);
        stage.show();
    }
    public void ChangerPourSceneJeu(ActionEvent event) throws IOException{
        messagePlacement.setVisible(false);
        boutonFinPlacement.setVisible(false);
        boutonTourner.setVisible(false);
        grilleBoutons.setVisible(false);
        grilleJoueur.setLayoutX(240);
        grilleJoueur.setLayoutY(450);
        grilleEnnemie.setVisible(true);
        messageTour.setVisible(true);
    }

    public static int randRange (int a , int b){
        Random rand = new Random();
        return rand.nextInt(b - a)+ a;
    }
    public void InitGrilleOrdi(){
        int[] grandeurBateau = new int[] {5, 4, 3, 3, 2};
        int ligne;
        int colonne;
        int direction;
        for(int numeroBateau = 1; numeroBateau <= 5; numeroBateau++)
        {
            ligne = randRange(0, 10);
            colonne = randRange(0, 10);
            direction = randRange(1, 3);

            while (!pB.posOk(grilleOrdiBackend, ligne, colonne, direction, grandeurBateau[numeroBateau - 1]))
            {
                ligne = randRange(0, 10);
                colonne = randRange(0, 10);
                direction = randRange(1, 3);
            }

            pB.ecrireDansGrille(grilleOrdiBackend, ligne, colonne, direction, grandeurBateau[numeroBateau - 1], numeroBateau);
        }
    }

    /*public void ActiverTriche(){
        if(tricheActive){

        }
        else {

        }
    }*/
}