package com.example.tp3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HelloController {
    AjouterMarqueur ajouterMarqueur = new AjouterMarqueur();
    AjouterMarqueurFX ajouterMarqueurFX = new AjouterMarqueurFX();
    static GrilleJeu grilleJeu = new GrilleJeu();
    static InputStream stream;
    static File chemin;
    static URL url;
    boolean partieFinie = false;
    static ImageView[][] tableauImages = new ImageView[2][5];
    //static MediaPlayer sonBouton;
    static MediaPlayer sonVictoire;
    static MediaPlayer sonDefaite;
    int nombreCoup;

    static Line ligneX1 = new Line(0, 0, 100, 100);
    static Line lignex2;
    static Circle cercleO;
    @FXML
    GridPane grilleDuJeu;
    @FXML
    static
    AnchorPane anchorPane;

    @FXML
    void ClicGrille(MouseEvent evenement) throws IOException, URISyntaxException {
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
                InitialiserJeu();
            }

            Tirjoueur(ligne, colonne);

            grilleJeu.AfficherGrille();
        }

        //System.out.println("x : " + ligne + ", y : " + colonne);
    }


    private void Tirjoueur(int ligne, int colonne) throws IOException{
        if(ajouterMarqueur.TesterEmplacement(grilleJeu.grille, ligne, colonne)){
            ajouterMarqueur.ModifierGrille(grilleJeu.grille, 1, ligne, colonne);
            //ajouterMarqueurFX.AjouterMarqueur(grilleDuJeu, grilleJeu, 1, ligne, colonne);
            grilleDuJeu.add(tableauImages[0][nombreCoup], colonne, ligne);
            //grilleDuJeu.add(imageTest, ligne, colonne);

            if(ajouterMarqueur.TesterFinDePartie(grilleJeu.grille, 1, ligne, colonne)){
                sonVictoire.play();
                System.out.println("Bien joueur");
                partieFinie = true;
            }
            //grilleJeu.AfficherGrille();
            if(!partieFinie) {
                if (nombreCoup < 4) {
                    TirEnnemi();
                } else {
                    partieFinie = true;
                    System.out.println("Partie nulle");
                }
            }
        }
    }

    private void TirEnnemi() {
        int ligne;
        int colonne;
        do{
            ligne = GenereNombreAleatoire(0, 3);
            colonne = GenereNombreAleatoire(0, 3);
        }
        while(!ajouterMarqueur.TesterEmplacement(grilleJeu.grille, ligne, colonne));
        ajouterMarqueur.ModifierGrille(grilleJeu.grille, 2, ligne, colonne);
        grilleDuJeu.add(tableauImages[1][nombreCoup], colonne, ligne);
        //grilleDuJeu.add(imageTest2, ligne, colonne);

        if(ajouterMarqueur.TesterFinDePartie(grilleJeu.grille, 2, ligne, colonne)){
            System.out.println("Pas bien joueur");
            sonDefaite.play();
            partieFinie = true;
        }
        nombreCoup++;
    }

    public static void InitialiserJeu() throws IOException, URISyntaxException {
        for(int i = 0; i < 2; i++){
            for(int ii = 0; ii < 5; ii++){
                tableauImages[i][ii] = new ImageView();
            }
        }

        //stream = new FileInputStream(HelloApplication.class.getResource("Images/LettreO.gif").toURI().toString());
        //Image tempo = new Image(stream);

        chemin = new File(HelloApplication.class.getResource("Images/LettreO.gif").toURI());
        Image tempo = new Image(chemin.toURI().toString());


        for(int i = 0; i < 5; i++){
            tableauImages[0][i].setImage(tempo);
            tableauImages[0][i].setFitWidth(40);
            tableauImages[0][i].setFitHeight(40);
        }
        //stream = new FileInputStream(HelloApplication.class.getResource("Images/LettreX.gif").toURI().toString());
        //tempo = new Image(stream);

        chemin = new File(HelloApplication.class.getResource("Images/LettreX.gif").toURI());
        tempo = new Image(chemin.toURI().toString());

        for(int i = 0; i < 5; i++){
            tableauImages[1][i].setImage(tempo);
            tableauImages[1][i].setFitWidth(40);
            tableauImages[1][i].setFitHeight(40);
        }

        //Media son = new Media(new File("C:\\Users\\Alexis\\Desktop\\Git\\420-45P-SI\\TP3\\src\\main\\java\\com\\example\\tp3\\Sons\\Windows Shutdown.wav").toURI().toString());
        //sonBouton = new MediaPlayer(son);

        Media son = new Media(HelloApplication.class.getResource("Sons/tadaa-47995.mp3").toURI().toString());
        sonVictoire = new MediaPlayer(son);

        son = new Media(HelloApplication.class.getResource("Sons/mixkit-arcade-retro-game-over-213.wav").toURI().toString());
        sonDefaite = new MediaPlayer(son);

        anchorPane = new AnchorPane();
        ligneX1.setStrokeWidth(10);
        anchorPane.getChildren().add(ligneX1);
    }

    @FXML
    void RetourAuMenu(ActionEvent evenement) throws IOException{
        grilleJeu.grille = new int[3][3];
        partieFinie = false;
        HelloApplication.ChangerScene("Menu");
        HelloApplication.sonBouton.seek(Duration.ZERO);
        HelloApplication.sonBouton.play();
    }

    int GenereNombreAleatoire(int borneInfreieure, int borneSuperieure){
        Random random = new Random();
        return random.nextInt(borneSuperieure - borneInfreieure) + borneInfreieure;
    }
}