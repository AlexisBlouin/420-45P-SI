package com.example.tp3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

import java.io.IOException;
import java.io.InputStream;

/**
 * Classe permettant de controller la scène du jeu.
 */
public class HelloController {
    /**
     * Instantiation de la classe grilleJeu pour l'utilisation de ses fonctions interne.
     */
    GrilleJeu grilleJeu = new GrilleJeu();
    /**
     * Création d'un objet GrilleJeu pour avoir accès à ses composants internes.
     */
    //static GrilleJeu grilleJeu = new GrilleJeu();
    static File chemin;
    boolean partieFinie = false;
    static ImageView[][] tableauImages = new ImageView[2][5];
    static MediaPlayer sonVictoire;
    static MediaPlayer sonDefaite;
    int nombreCoup;
    int etaFinPartie = 0;


    /**
     * Ligne 1 de l'affichage du X.
     */
    @FXML
    Line ligne1;

    /**
     * Ligne 2 de l'affichage du X.
     */
    @FXML
    Line ligne2;

    /**
     * Cercle de l'affichage.
     */
    @FXML
    Circle cercle;

    /**
     * GridPane contenant les images.
     */
    @FXML
    GridPane grilleDuJeu;

    /**
     * Message apparaissant à la fin de la partie.
     */
    @FXML
    Text messageFin;
    /**
     * Bouton de retour au menu.
     */
    @FXML
    Button boutonMenu;

    /**
     * Fonction appelée quand le GridPane est cliqué permettant de placer notre coup.
     * @param evenement Évènement de la souris contenant les informations sur le clic de l'utilisateur dont la ligne et la colonne du clic.
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    void clicGrille(MouseEvent evenement) throws IOException, URISyntaxException {
        if(!partieFinie){
            Node source = (Node)evenement.getSource();
            Integer ligne = GridPane.getRowIndex(source);
            Integer colonne = GridPane.getColumnIndex(source);
            if(ligne == null){
                ligne = 0;
            }
            if(colonne == null){
                colonne = 0;
            }

            if(nombreCoup == 0){
                initialiserJeu();
            }

            tirJoueur(ligne, colonne);

            grilleJeu.AfficherGrille();
        }

        //System.out.println("x : " + ligne + ", y : " + colonne);
    }


    /**
     * Fonction testant si l'emplacement du clic est déjà occupé ou non.
     * @param ligne Ligne choisie
     * @param colonne Colonne choisie
     * @throws IOException
     */
    private void tirJoueur(int ligne, int colonne) throws IOException{
        if(grilleJeu.TesterEmplacement(grilleJeu.grille, ligne, colonne)){
            grilleJeu.ModifierGrille(grilleJeu.grille, 1, ligne, colonne);
            //grilleJeuFX.grilleJeu(grilleDuJeu, grilleJeu, 1, ligne, colonne);
            grilleDuJeu.add(tableauImages[0][nombreCoup], colonne, ligne);
            //grilleDuJeu.add(imageTest, ligne, colonne);


            if(grilleJeu.TesterFinDePartie(grilleJeu.grille, 1, ligne, colonne)){
                sonVictoire.play();
                System.out.println("Bien joueur");
                partieFinie = true;
                messageFin.setText("Bravo, vou avez gagne!");
                etaFinPartie = 2;
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
                boutonMenu.setVisible(true);
            }
            //grilleJeu.AfficherGrille();
            if(!partieFinie) {
                if (nombreCoup < 4) {
                    coupEnnemi();
                } else {
                    partieFinie = true;
                    System.out.println("Partie nulle");
                    messageFin.setText("Partie nulle.");
                    etaFinPartie = 1;
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                    boutonMenu.setVisible(true);
                }
            }
        }
    }

    /**
     * Fonction permettant d'effectuer le coup de l'ennemi.
     */
    private void coupEnnemi() {
        int ligne;
        int colonne;
        do{
            ligne = genereNombreAleatoire(0, 3);
            colonne = genereNombreAleatoire(0, 3);
        }
        while(!grilleJeu.TesterEmplacement(grilleJeu.grille, ligne, colonne));
        grilleJeu.ModifierGrille(grilleJeu.grille, 2, ligne, colonne);
        grilleDuJeu.add(tableauImages[1][nombreCoup], colonne, ligne);
        //grilleDuJeu.add(imageTest2, ligne, colonne);

        if(grilleJeu.TesterFinDePartie(grilleJeu.grille, 2, ligne, colonne)){
            System.out.println("Pas bien joueur");
            sonDefaite.play();
            partieFinie = true;
            messageFin.setText("Dommage, vous avez perdu...");
            etaFinPartie = 0;
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            boutonMenu.setVisible(true);
        }
        nombreCoup++;
    }

    /**
     * Fonction initialisant le jeu au premier clic dans le GridPane
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void initialiserJeu() throws IOException, URISyntaxException {
        for(int i = 0; i < 2; i++){
            for(int ii = 0; ii < 5; ii++){
                tableauImages[i][ii] = new ImageView();
            }
        }

        chemin = new File(HelloApplication.class.getResource("Images/LettreO.gif").toURI());
        Image tempo = new Image(chemin.toURI().toString());

        for(int i = 0; i < 5; i++){
            tableauImages[0][i].setImage(tempo);
            tableauImages[0][i].setFitWidth(40);
            tableauImages[0][i].setFitHeight(40);
        }

        chemin = new File(HelloApplication.class.getResource("Images/LettreX.gif").toURI());
        tempo = new Image(chemin.toURI().toString());

        for(int i = 0; i < 5; i++){
            tableauImages[1][i].setImage(tempo);
            tableauImages[1][i].setFitWidth(40);
            tableauImages[1][i].setFitHeight(40);
        }

        Media son = new Media(HelloApplication.class.getResource("Sons/tadaa-47995.mp3").toURI().toString());
        sonVictoire = new MediaPlayer(son);

        son = new Media(HelloApplication.class.getResource("Sons/mixkit-arcade-retro-game-over-213.wav").toURI().toString());
        sonDefaite = new MediaPlayer(son);
    }

    /**
     * Fonction appelée afin de retourner au menu.
     * @param evenement Evenement de la souris contenant les informations sur le clic de l'utilisateur dont la ligne et la colonne du clic.
     * @throws IOException
     */
    @FXML
    void retourAuMenu(ActionEvent evenement) throws IOException{
        grilleJeu.grille = new int[3][3];
        partieFinie = false;
        HelloApplication.ChangerScene("Menu");
        HelloApplication.sonBouton.seek(Duration.ZERO);
        HelloApplication.sonBouton.play();
    }

    /**
     * Fonction retournant un nombre aléatoire.
     * @param borneInfreieure Borne inférieure (incluse).
     * @param borneSuperieure Borne supérieure (excluse).
     * @return Nombre aléatoire.
     */
    int genereNombreAleatoire(int borneInfreieure, int borneSuperieure){
        Random random = new Random();
        return random.nextInt(borneSuperieure - borneInfreieure) + borneInfreieure;
    }

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
        int deplacement = 10;
        int valeurGrossissement = 10;

        @Override
        public void handle(ActionEvent actionEvent) {
            if(partieFinie){


                if (etaFinPartie == 0) {
                    if(cercle.getLayoutX() < -200){
                        deplacement = 0;
                    }
                    if(ligne1.getStrokeWidth() > 20){
                        valeurGrossissement = 0;
                    }

                    cercle.setLayoutX(cercle.getLayoutX() - deplacement);
                    ligne1.setStrokeWidth(ligne1.getStrokeWidth() + valeurGrossissement);
                    ligne2.setStrokeWidth(ligne1.getStrokeWidth() + valeurGrossissement);
                }
                else if(etaFinPartie == 1){
                    if(cercle.getLayoutY() > 800){
                        deplacement = 0;
                    }

                    cercle.setLayoutY(cercle.getLayoutY() + deplacement);
                    ligne1.setLayoutY(ligne1.getLayoutY() + deplacement);
                    ligne2.setLayoutY(ligne2.getLayoutY() + deplacement);
                }
                else if(etaFinPartie == 2){
                    if(ligne1.getLayoutX() > 900){
                        deplacement = 0;
                    }
                    if(cercle.getStrokeWidth() > 20){
                        valeurGrossissement = 0;
                    }

                    ligne1.setLayoutX(ligne1.getLayoutX() + deplacement);
                    ligne2.setLayoutX(ligne2.getLayoutX() + deplacement);
                    cercle.setStrokeWidth(cercle.getStrokeWidth() + valeurGrossissement);
                }
            }
        }
    }));
}