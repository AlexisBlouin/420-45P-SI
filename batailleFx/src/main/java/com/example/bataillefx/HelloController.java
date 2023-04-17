package com.example.bataillefx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    public GridPane grilleJoueur;
    public GridPane grilleEnnemie;
    public GridPane grilleEnnemieTriche;
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
    public ImageView porteAvionsHEnnemie;
    public ImageView porteAvionsVEnnemie;
    public ImageView croiseurHEnnemie;
    public ImageView croiseurVEnnemie;
    public ImageView contreTorpilleurHEnnemie;
    public ImageView contreTorpilleurVEnnemie;
    public ImageView sousMarinHEnnemie;
    public ImageView sousMarinVEnnemie;
    public ImageView torpilleurHEnnemie;
    public ImageView torpilleurVEnnemie;
    public ImageView eauAlliee;
    public ImageView eauEnnemie;
    ImageView[][] bateaux;

    public Button boutonFinPlacement;
    public Button boutonInitialisation;
    public Button boutonNouvellePartie;
    public Button boutonTourner;
    public MenuBar barreMenu;
    public Text messagePlacement;
    public Text messageTour;

    int nombreBateauPlace = 0;
    Integer bateauChoisi = 0;
    int direction = 1;
    boolean initialisation = true;
    public static boolean tricheActive = false;
    ArrayList<Bateau> listeBateau = new ArrayList<Bateau>();
    ArrayList<Bateau> listeBateauEnnemie = new ArrayList<Bateau>();
    PlacerBateauFx pbf = new PlacerBateauFx();
    PlacerBateau pB = new PlacerBateau();

    public static int [][]grilleOrdiBackend = new int[10][10];
    public static int [][]grilleJoueurBackend = new int [10][10];
    int[] longueurBateaux = {5, 4, 3, 3, 2};
    int[] numBateaux = {1, 2, 3, 4, 5};
    public Bateau porteAvions = new Bateau(longueurBateaux[0], numBateaux[0], direction);
    public Bateau croiseur = new Bateau(longueurBateaux[1], numBateaux[1], direction);
    public Bateau contreTorpilleur = new Bateau(longueurBateaux[2], numBateaux[2], direction);
    public Bateau sousMarin = new Bateau(longueurBateaux[3], numBateaux[3], direction);
    public Bateau torpilleur = new Bateau(longueurBateaux[4], numBateaux[4], direction);
    public Bateau porteAvionsEnnemie = new Bateau(longueurBateaux[0], numBateaux[0], direction);
    public Bateau croiseurEnnemie = new Bateau(longueurBateaux[1], numBateaux[1], direction);
    public Bateau contreTorpilleurEnnemie = new Bateau(longueurBateaux[2], numBateaux[2], direction);
    public Bateau sousMarinEnnemie = new Bateau(longueurBateaux[3], numBateaux[3], direction);
    public Bateau torpilleurEnnemie = new Bateau(longueurBateaux[4], numBateaux[4], direction);

    public void clickGrid(MouseEvent t) {
        Node source = (Node)t.getSource();
        //System.out.println("X : " + t.getX() + ", Y : " + t.getY());
        Integer row = GridPane.getRowIndex(source);
        Integer column = GridPane.getColumnIndex(source);
        //System.out.println("L : " + row + ", C : " + column);


        if(pB.posOk(grilleJoueurBackend, row - 1, column - 1, direction, longueurBateaux[bateauChoisi])){

            /*positionbateaux[bateauChoisi][0] = row - 1;
            positionbateaux[bateauChoisi][1] = column - 1;
            positionbateaux[bateauChoisi][2] = direction;*/
            for(Bateau bateau:listeBateau){
                if(bateau.numGrille == numBateaux[bateauChoisi]){
                    if(bateau.posGrilleX == -1){
                        pbf.PlacerUnBateau(grilleJoueur, bateau.imageHorizontale, bateau.imageVerticale, row, column);
                        nombreBateauPlace++;
                    }
                    else {
                        pB.ecrireDansGrille(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, direction, bateau.longueur, 0);
                        System.out.println("Je devrais avoir effacer le bateau");
                        pbf.ReplacerUnBateau(grilleJoueur, bateau.imageHorizontale, bateau.imageVerticale, row, column);

                    }
                    bateau.SetPosition(row - 1, column - 1, direction);
                    pB.ecrireDansGrille(grilleJoueurBackend, row - 1, column - 1, direction, bateau.longueur, bateau.numGrille);
                    pB.afficherGrille(grilleJoueurBackend);
                    //System.out.println("X : " + bateau.posGrilleX + " Y : " + bateau.posGrilleY + " D : " + bateau.direction);
                }
                //System.out.println("Num bateau : " + bateau.numGrille + " NumSuppose : " + numBateaux[bateauChoisi]);
            }


            if(nombreBateauPlace >= 5){
                messagePlacement.setVisible(true);
                boutonFinPlacement.setVisible(true);
            }
        }
    }

    public void tournerBateau(){
        //Fonction hide
        if(direction == 1){
            direction = 2;

            for(Bateau bateau:listeBateau){
                if(bateauChoisi + 1 == bateau.numGrille){
                    if(bateau.posGrilleX != -1){
                        pB.ecrireDansGrille(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, 1, bateau.longueur, 0);
                    }
                    if(bateau.posGrilleX == -1 || pB.posOk(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, direction, bateau.longueur)){
                        bateau.direction = 2;
                        bateau.imageHorizontale.setVisible(false);
                        bateau.imageVerticale.setVisible(true);

                    }
                    else {
                        direction = 1;
                    }
                    if(bateau.posGrilleX != -1){
                        pB.ecrireDansGrille(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, direction, bateau.longueur, bateau.numGrille);
                    }
                }
            }
        }
        else {
            direction = 1;

            for(Bateau bateau:listeBateau){
                if(bateauChoisi + 1 == bateau.numGrille){
                    if(bateau.posGrilleX != -1){
                        pB.ecrireDansGrille(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, 2, bateau.longueur, 0);
                    }
                    if(bateau.posGrilleX == -1 || pB.posOk(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, direction, bateau.longueur)){
                        bateau.direction = 1;
                        bateau.imageHorizontale.setVisible(true);
                        bateau.imageVerticale.setVisible(false);

                    }
                    else {
                        direction = 2;
                    }
                    if(bateau.posGrilleX != -1){
                        pB.ecrireDansGrille(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, direction, bateau.longueur, bateau.numGrille);
                    }
                }
            }
        }
        pB.afficherGrille(grilleJoueurBackend);
    }

    public void ChoisirBateau(MouseEvent t){
        Node source = (Node)t.getSource();
        bateauChoisi = GridPane.getColumnIndex(source);
        if(bateauChoisi == null){
            bateauChoisi = 0;
        }
        //System.out.println(bateauChoisi);
        for(Bateau bateau:listeBateau){
            if(bateauChoisi + 1 == bateau.numGrille){
                if(bateau.direction == 1){
                    direction = 1;
                    bateau.imageHorizontale.setVisible(true);
                }
                else {
                    direction = 2;
                    bateau.imageVerticale.setVisible(true);
                }
            }
            else {
                if(bateau.posGrilleX == -1){
                    bateau.imageHorizontale.setVisible(false);
                    bateau.imageVerticale.setVisible(false);
                }
            }
        }
    }

    /*public void ConfirmerPosition(){
        //pB.ecrireDansGrille(grilleJoueurBackend, row, column, direction, longueurBateaux[bateauChoisi], numBateaux[bateauChoisi]);

        for(Bateau bateau:listeBateau){
            pB.ecrireDansGrille(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, bateau.direction, bateau.longueur, bateau.numGrille);
        }

        pB.afficherGrille(grilleJoueurBackend);
    }*/

    public void tirAlier(MouseEvent evenement){
        Node source = (Node)evenement.getSource();
        //System.out.println("X : " + evenement.getX() + ", Y : " + evenement.getY());
        Integer row = GridPane.getRowIndex(source) - 1;
        Integer column = GridPane.getColumnIndex(source) - 1;
        System.out.println("L : " + row + ", C : " + column);
        TirCanon tirCanon = new TirCanon();

        if(grilleOrdiBackend[row][column] != 6){
            System.out.println("bonjour");
            String[] nomBateau = new String[] {"Porte-avions", "Croiseur", "Contre-torpilleur", "Sous-marin", "Torpilleur"};
            String nomBateauVise = "";
            if(grilleOrdiBackend[row][column] - 1 != -1 && grilleOrdiBackend[row][column] - 1 != 5){
                nomBateauVise = nomBateau[grilleOrdiBackend[row][column] - 1];
            }

            tirCanon(grilleEnnemie, grilleOrdiBackend, column, row);

            tirEnnemi();

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
    }

    public void tirEnnemi() {
        /*int ligne;
        int colonne;
        ligne = randRange(0, 10);
        colonne = randRange(0, 10);
        while(grilleJoueurBackend[ligne][colonne] < 6 && grilleJoueurBackend[ligne][colonne] > 0);{
            ligne = randRange(0, 10);
            colonne = randRange(0, 10);
        }
        tirCanon(grilleJoueur, grilleJoueurBackend, colonne, ligne);*/

        int ligne;
        int colonne;
        ligne = randRange(0, 10);
        colonne = randRange(0, 10);
        tirCanon(grilleJoueur, grilleJoueurBackend, colonne, ligne);
        TirCanon tirCanon = new TirCanon();
        tirCanon.mouvement(grilleJoueurBackend, ligne, colonne, "GrilleJoueur");
        //game util
    }

    public void tirCanon(GridPane grille, int[][] grilleBackend, int column, int row){


        pbf.marqueTouche(grille, grilleBackend, column + 1, row + 1);
    }

    public void InitialiserTableau(){


        if(initialisation){
            boutonInitialisation.setVisible(false);
            ImageView[][] tempo = {{porteAvionsH, porteAvionsV}, {croiseurH, croiseurV},
                    {contreTorpilleurH, contreTorpilleurV}, {sousMarinH, sousMarinV}, {torpilleurH, torpilleurV}};
            bateaux = tempo;
            initialisation = false;

            porteAvions.InitImages(porteAvionsH, porteAvionsV);
            croiseur.InitImages(croiseurH, croiseurV);
            contreTorpilleur.InitImages(contreTorpilleurH, contreTorpilleurV);
            sousMarin.InitImages(sousMarinH, sousMarinV);
            torpilleur.InitImages(torpilleurH, torpilleurV);

            porteAvionsEnnemie.InitImages(porteAvionsHEnnemie, porteAvionsVEnnemie);
            croiseurEnnemie.InitImages(croiseurHEnnemie, croiseurVEnnemie);
            contreTorpilleurEnnemie.InitImages(contreTorpilleurHEnnemie, contreTorpilleurVEnnemie);
            sousMarinEnnemie.InitImages(sousMarinHEnnemie, sousMarinVEnnemie);
            torpilleurEnnemie.InitImages(torpilleurHEnnemie, torpilleurVEnnemie);

            listeBateau.add(porteAvions);
            listeBateau.add(croiseur);
            listeBateau.add(contreTorpilleur);
            listeBateau.add(sousMarin);
            listeBateau.add(torpilleur);

            listeBateauEnnemie.add(porteAvionsEnnemie);
            listeBateauEnnemie.add(croiseurEnnemie);
            listeBateauEnnemie.add(contreTorpilleurEnnemie);
            listeBateauEnnemie.add(sousMarinEnnemie);
            listeBateauEnnemie.add(torpilleurEnnemie);
        }

        Bateau bateau = listeBateau.get(0);
        bateau.imageHorizontale.setVisible(true);
        InitGrilleOrdi();
    }


    //Fait en partie avec : https://www.youtube.com/watch?v=hcM-R-YOKkQ
    public void AllerAuMenu(ActionEvent event) throws IOException{
        grilleJoueurBackend = new int[10][10];
        grilleOrdiBackend = new int[10][10];
        //ActiverTriche();
        Stage stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneMenu.fxml"));
        stage = (Stage)boutonTourner.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setScene(scene);
        stage.show();
    }
    public void ChangerPourSceneJeu(ActionEvent event) throws IOException{
        messagePlacement.setVisible(false);
        boutonFinPlacement.setVisible(false);
        boutonTourner.setVisible(false);
        grilleBoutons.setVisible(false);
        //grilleJoueur.setLayoutX(240);
        grilleJoueur.setLayoutY(450);
        //eauAlliee.setLayoutX(240);
        eauAlliee.setLayoutY(450);
        eauEnnemie.setVisible(true);
        grilleEnnemie.setVisible(true);
        //eauEnnemie.setVisible(true);
        //messageTour.setVisible(true);

        if(tricheActive){
            grilleEnnemieTriche.setVisible(true);
        }

        Menu menu = new Menu("Options");
        MenuItem optionRecommencer = new MenuItem("Recommencer");
        MenuItem optionTricher = new MenuItem("Tricher");
        MenuItem optionQuitter = new MenuItem("Quitter");
        menu.getItems().add(optionRecommencer);
        menu.getItems().add(optionTricher);
        menu.getItems().add(optionQuitter);
        barreMenu.getMenus().remove(0, 3);
        barreMenu.getMenus().add(menu);
        barreMenu.setVisible(true);

        optionQuitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                try{
                    ActiverTriche();
                    AllerAuMenu(actionEvent);
                }
                catch (IOException e){
                    throw new IllegalStateException("something went wrong", e);
                }
            }
        });
        optionRecommencer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    grilleJoueurBackend = new int[10][10];
                    grilleOrdiBackend = new int[10][10];
                    if(tricheActive){
                        ActiverTriche();
                    }
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ScenePlacementBateaux.fxml"));
                    stage = (Stage)boutonFinPlacement.getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader.load(), 800, 800);
                    stage.setScene(scene);
                    stage.show();
                }
                catch (IOException e){
                    throw new IllegalStateException("something went wrong", e);
                }
            }
        });
        optionTricher.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ActiverTriche();
            }
        });
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
            for(Bateau bateau:listeBateauEnnemie){
                if(bateau.numGrille == numeroBateau){
                    pbf.PlacerUnBateau(grilleEnnemieTriche, bateau.imageHorizontale, bateau.imageVerticale, ligne + 1, colonne + 1);
                    if(direction == 1){
                        bateau.imageHorizontale.setVisible(true);
                        bateau.imageVerticale.setVisible(false);
                    }
                    else {
                        bateau.imageHorizontale.setVisible(false);
                        bateau.imageVerticale.setVisible(true);
                    }
                }
            }
        }
        pB.afficherGrille(grilleOrdiBackend);
    }

    public void ActiverTriche(){
        if(tricheActive){
            tricheActive = false;
            grilleEnnemieTriche.setVisible(false);
        }
        else {
            tricheActive = true;
            if(!initialisation){
                grilleEnnemieTriche.setVisible(true);
            }
        }
    }
}