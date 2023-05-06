package com.example.tp3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
import java.util.Random;

import java.io.IOException;

/**
 * Classe permettant de controller la scène du jeu.
 */
public class ControlleurJeu {
    /**
     * Création d'un objet GrilleJeu pour avoir accès à ses composants internes.
     */
    GrilleJeu grilleJeu = new GrilleJeu();
    /**
     * Chemin de fichier utilisé pour intialiser les images.
     */
    static File chemin;
    /**
     * Tableau contenant toute les image pouvant être placées lors de la partie.
     */
    static ImageView[][] tableauImages = new ImageView[2][5];
    /**
     * Son jouant lors de la victoire du joueur.
     */
    static MediaPlayer sonVictoire;
    /**
     * Son jouant lors de la défaite du joueur.
     */
    static MediaPlayer sonDefaite;
    /**
     * Booléen permettant de déterminer si la partie est finie.
     */
    boolean partieFinie = false;
    /**
     * Variable contenant le nombre de tours joués.
     */
    int nombreTour;
    /**
     * Variable indiquant comment s'est terminée la partie (0 = défaite, 1 = neutre, 2 = victoire).
     */
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
     * @throws URISyntaxException
     */
    @FXML
    void clicGrille(MouseEvent evenement) throws URISyntaxException {
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

            if(nombreTour == 0){
                initialiserJeu();
            }

            coupJoueur(ligne, colonne);
        }
    }


    /**
     * Fonction testant si l'emplacement du clic est déjà occupé ou non.
     * @param ligne Ligne choisie
     * @param colonne Colonne choisie
     */
    private void coupJoueur(int ligne, int colonne) {
        if(grilleJeu.testerEmplacement(grilleJeu.grille, ligne, colonne)){
            grilleJeu.modifierGrille(grilleJeu.grille, 1, ligne, colonne);
            grilleDuJeu.add(tableauImages[0][nombreTour], colonne, ligne);

            // Si la partie est terminée après le dernier coup, le joueur a gagné.
            if(grilleJeu.testerFinDePartie(grilleJeu.grille, 1, ligne, colonne)){
                sonVictoire.play();
                partieFinie = true;
                messageFin.setText("Bravo, vou avez gagne!");
                etaFinPartie = 2;
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
                boutonMenu.setVisible(true);
            }

            // S'il y a plus de 4 tours de joué, personne n'a gagné.
            if(!partieFinie) {
                if (nombreTour < 4) {
                    coupEnnemi();
                } else {
                    partieFinie = true;
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
        while(!grilleJeu.testerEmplacement(grilleJeu.grille, ligne, colonne));
        grilleJeu.modifierGrille(grilleJeu.grille, 2, ligne, colonne);
        grilleDuJeu.add(tableauImages[1][nombreTour], colonne, ligne);

        if(grilleJeu.testerFinDePartie(grilleJeu.grille, 2, ligne, colonne)){
            sonDefaite.play();
            partieFinie = true;
            messageFin.setText("Dommage, vous avez perdu...");
            etaFinPartie = 0;
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            boutonMenu.setVisible(true);
        }
        nombreTour++;
    }

    /**
     * Fonction initialisant le jeu au premier clic dans le GridPane
     * @throws URISyntaxException
     */
    private static void initialiserJeu() throws URISyntaxException {
        // Initialise le tableau des Images.
        for(int i = 0; i < 2; i++){
            for(int ii = 0; ii < 5; ii++){
                tableauImages[i][ii] = new ImageView();
            }
        }

        // Assigne le chemin d'accès pour la lettre O.
        chemin = new File(HelloApplication.class.getResource("Images/LettreO.gif").toURI());
        Image tempo = new Image(chemin.toURI().toString());

        // Met l'image de O dans le tableau et met l'image à la taille nécessaire.
        for(int i = 0; i < 5; i++){
            tableauImages[0][i].setImage(tempo);
            tableauImages[0][i].setFitWidth(40);
            tableauImages[0][i].setFitHeight(40);
        }

        // Assigne le chemin d'accès pour la lettre X.
        chemin = new File(HelloApplication.class.getResource("Images/LettreX.gif").toURI());
        tempo = new Image(chemin.toURI().toString());

        // Met l'image de X dans le tableau et met l'image à la taille nécessaire.
        for(int i = 0; i < 5; i++){
            tableauImages[1][i].setImage(tempo);
            tableauImages[1][i].setFitWidth(40);
            tableauImages[1][i].setFitHeight(40);
        }

        // Initialise le son de victoire.
        Media son = new Media(HelloApplication.class.getResource("Sons/tadaa-47995.mp3").toURI().toString());
        sonVictoire = new MediaPlayer(son);

        // Initialise le son de défaite.
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
        HelloApplication.changerScene("Menu");
        HelloApplication.sonBouton.seek(Duration.ZERO);
        HelloApplication.sonBouton.play();
    }

    /**
     * Fonction retournant un nombre aléatoire.
     * @param borneInfreieure Borne inférieure (incluse).
     * @param borneSuperieure Borne supérieure (excluse).
     * @return Nombre aléatoire.
     */
    private int genereNombreAleatoire(int borneInfreieure, int borneSuperieure){
        Random random = new Random();
        return random.nextInt(borneSuperieure - borneInfreieure) + borneInfreieure;
    }

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
        int deplacement = 10;
        int valeurGrossissement = 10;

        @Override
        public void handle(ActionEvent actionEvent) {
            if(partieFinie){

                // Selon l'état de fin de partie, le mouvement sera différent.
                if (etaFinPartie == 0) {
                    // Une fois que les objets sont hors de la fenêtre, on arrête leur déplacement.
                    if(cercle.getLayoutX() < -200){
                        deplacement = 0;
                    }
                    // Une fois que l'épaisseur est de 20, on arrête de l'épaissir.
                    if(ligne1.getStrokeWidth() > 20){
                        valeurGrossissement = 0;
                    }

                    // Mouvement des objets.
                    cercle.setLayoutX(cercle.getLayoutX() - deplacement);
                    ligne1.setStrokeWidth(ligne1.getStrokeWidth() + valeurGrossissement);
                    ligne2.setStrokeWidth(ligne1.getStrokeWidth() + valeurGrossissement);
                }
                else if(etaFinPartie == 1){
                    // Une fois que les objets sont hors de la fenêtre, on arrête leur déplacement.
                    if(cercle.getLayoutY() > 800){
                        deplacement = 0;
                    }

                    // Mouvement des objets.
                    cercle.setLayoutY(cercle.getLayoutY() + deplacement);
                    ligne1.setLayoutY(ligne1.getLayoutY() + deplacement);
                    ligne2.setLayoutY(ligne2.getLayoutY() + deplacement);
                }
                else if(etaFinPartie == 2){
                    // Une fois que les objets sont hors de la fenêtre, on arrête leur déplacement.
                    if(ligne1.getLayoutX() > 900){
                        deplacement = 0;
                    }
                    // Une fois que l'épaisseur est de 20, on arrête de l'épaissir.
                    if(cercle.getStrokeWidth() > 20){
                        valeurGrossissement = 0;
                    }

                    // Mouvement des objets.
                    ligne1.setLayoutX(ligne1.getLayoutX() + deplacement);
                    ligne2.setLayoutX(ligne2.getLayoutX() + deplacement);
                    cercle.setStrokeWidth(cercle.getStrokeWidth() + valeurGrossissement);
                }
            }
        }
    }));
}