package com.example.tp3;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.Random;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.random.RandomGenerator;

public class HelloController {
    AjouterMarqueur ajouterMarqueur = new AjouterMarqueur();
    AjouterMarqueurFX ajouterMarqueurFX = new AjouterMarqueurFX();
    static GrilleJeu grilleJeu = new GrilleJeu();
    static InputStream stream;
    boolean partieFinie = false;
    static ImageView[][] tableauImages = new ImageView[2][5];
    static ImageView imageTest = new ImageView();
    static ImageView imageTest2 = new ImageView();
    int nombreCoup;
    @FXML
    GridPane grilleDuJeu;

    @FXML
    void ClicGrille(MouseEvent evenement) throws IOException{
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
                InitialiserImages();
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
                System.out.println("Bien joueur");
                partieFinie = true;
            }
            //grilleJeu.AfficherGrille();
            if(!partieFinie){
                TirEnnemi();
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
            partieFinie = true;
        }
        nombreCoup++;
    }

    public static void InitialiserImages() throws IOException {
        for(int i = 0; i < 2; i++){
            for(int ii = 0; ii < 5; ii++){
                tableauImages[i][ii] = new ImageView();
            }
        }

        stream = new FileInputStream("C:\\Users\\bloa\\Desktop\\Git\\420-45P-SI\\TP3\\src\\main\\java\\com\\example\\tp3\\Images\\LettreO.gif");
        Image tempo = new Image(stream);

        for(int i = 0; i < 5; i++){
            tableauImages[0][i].setImage(tempo);
            tableauImages[0][i].setFitWidth(40);
            tableauImages[0][i].setFitHeight(40);
        }
        stream = new FileInputStream("C:\\Users\\bloa\\Desktop\\Git\\420-45P-SI\\TP3\\src\\main\\java\\com\\example\\tp3\\Images\\LettreX.gif");
        tempo = new Image(stream);

        for(int i = 0; i < 5; i++){
            tableauImages[1][i].setImage(tempo);
            tableauImages[1][i].setFitWidth(40);
            tableauImages[1][i].setFitHeight(40);
        }
    }

    @FXML
    void RetourAuMenu(ActionEvent evenement) throws IOException{
        grilleJeu.grille = new int[3][3];
        partieFinie = false;
        HelloApplication.ChangerScene("Menu");
    }

    int GenereNombreAleatoire(int borneInfreieure, int borneSuperieure){
        Random random = new Random();
        return random.nextInt(borneSuperieure - borneInfreieure) + borneInfreieure;
    }
}