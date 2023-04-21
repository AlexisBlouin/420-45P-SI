package com.example.bataillefx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Controleur permettant de controller les actions de la scene du menu.
 */
public class ControlleurJeu {
    @FXML
    /**
     * Grille du joueur.
     */
    public GridPane grilleJoueur;
    /**
     * Grille de l'ennemi.
     */
    public GridPane grilleEnnemie;
    /**
     * Grille de l'ennemi contenant les bateaux pour l'affichage de la triche.
     */
    public GridPane grilleEnnemieTriche;
    /**
     * Grille contenant les boutons pour le choix des bateaux.
     */
    public GridPane grilleBoutons;
    /**
     * Image bateau.
     */
    public ImageView porteAvionsH;
    /**
     * Image bateau.
     */
    public ImageView porteAvionsV;
    /**
     * Image bateau.
     */
    public ImageView croiseurH;
    /**
     * Image bateau.
     */
    public ImageView croiseurV;
    /**
     * Image bateau.
     */
    public ImageView contreTorpilleurH;
    /**
     * Image bateau.
     */
    public ImageView contreTorpilleurV;
    /**
     * Image bateau.
     */
    public ImageView sousMarinH;
    /**
     * Image bateau.
     */
    public ImageView sousMarinV;
    /**
     * Image bateau.
     */
    public ImageView torpilleurH;
    /**
     * Image bateau.
     */
    public ImageView torpilleurV;
    /**
     * Image bateau.
     */
    public ImageView porteAvionsHEnnemie;
    /**
     * Image bateau.
     */
    public ImageView porteAvionsVEnnemie;
    /**
     * Image bateau.
     */
    public ImageView croiseurHEnnemie;
    /**
     * Image bateau.
     */
    public ImageView croiseurVEnnemie;
    /**
     * Image bateau.
     */
    public ImageView contreTorpilleurHEnnemie;
    /**
     * Image bateau.
     */
    public ImageView contreTorpilleurVEnnemie;
    /**
     * Image bateau.
     */
    public ImageView sousMarinHEnnemie;
    /**
     * Image bateau.
     */
    public ImageView sousMarinVEnnemie;
    /**
     * Image bateau.
     */
    public ImageView torpilleurHEnnemie;
    /**
     * Image bateau.
     */
    public ImageView torpilleurVEnnemie;
    /**
     * Image de l'eau de la grille joueur.
     */
    public ImageView eauJoueur;
    /**
     * Image de l'eau de la grille ennemie.
     */
    public ImageView eauEnnemie;
    /**
     * Bouton qui apparait une fois le placement des bateaux complete.
     */
    public Button boutonFinPlacement;
    /**
     * Bouton pour commencer le placement des bateaux.
     */
    public Button boutonInitialisation;
    /**
     * Bouton pour retourner au menu une fois la partie terminee.
     */
    public Button boutonNouvellePartie;
    /**
     * Bouton pour tourner un bateau.
     */
    public Button boutonTourner;
    /**
     * Barre des menus.
     */
    public MenuBar barreMenu;
    /**
     * Message apparaissant apres le placement des bateaux.
     */
    public Text messagePlacement;
    /**
     * Texte identifiant la grille ennemie.
     */
    public Text messageGrilleEnnemie;
    /**
     * Texte identifiant la grille joueur.
     */
    public Text messageGrilleJoueur;
    /**
     * Message de fin de partie.
     */
    public Text messageFinPartie;
    /**
     * Nombre de bateaux places.
     */
    int nombreBateauPlace = 0;
    /**
     * Numero du bateau choisi.
     */
    Integer bateauChoisi = 0;
    /**
     * Direction du bateau actuel.
     */
    int direction = 1;
    /**
     * Variable disant si l'initialisation a ete faite.
     */
    boolean initialisation = true;
    /**
     * Variable disant si la partie est terminee.
     */
    boolean partieFinie = false;
    /**
     * Variable disant si le joueur triche actuellement.
     */
    public static boolean tricheActive = false;
    /**
     * Variable disant si le joueur a triche.
     */
    boolean aTriche = false;
    /**
     * Liste contenant les bateaux du joueur.
     */
    ArrayList<Bateau> listeBateau = new ArrayList<Bateau>();
    /**
     * Liste contenant les bateaux de l'ennemi.
     */
    ArrayList<Bateau> listeBateauEnnemie = new ArrayList<Bateau>();
    /**
     * Reference a la classe PlacerBateauFx pour un acces aux fonctions internes.
     */
    PlacerBateauFx placerBateauFx = new PlacerBateauFx();
    /**
     * Reference a la classe PlacerBateau pour un acces aux fonctions internes.
     */
    PlacerBateau placerBateau = new PlacerBateau();
    /**
     * Grille de l'ordinateur contenant les informations backend.
     */
    public static int [][]grilleOrdiBackend = new int[10][10];
    /**
     * Grille du joueur contenant les informations backend.
     */
    public static int [][]grilleJoueurBackend = new int [10][10];
    /**
     * Tableau contenant la longueur des bateaux.
     */
    int[] longueurBateaux = {5, 4, 3, 3, 2};
    /**
     * Tableau contenant le numero de grille des bateaux.
     */
    int[] numBateaux = {1, 2, 3, 4, 5};
    /**
     * Bateau joueur.
     */
    public Bateau porteAvions = new Bateau(longueurBateaux[0], numBateaux[0], direction);
    /**
     * Bateau joueur.
     */
    public Bateau croiseur = new Bateau(longueurBateaux[1], numBateaux[1], direction);
    /**
     * Bateau joueur.
     */
    public Bateau contreTorpilleur = new Bateau(longueurBateaux[2], numBateaux[2], direction);
    /**
     * Bateau joueur.
     */
    public Bateau sousMarin = new Bateau(longueurBateaux[3], numBateaux[3], direction);
    /**
     * Bateau joueur.
     */
    public Bateau torpilleur = new Bateau(longueurBateaux[4], numBateaux[4], direction);
    /**
     * Bateau ennemi.
     */
    public Bateau porteAvionsEnnemie = new Bateau(longueurBateaux[0], numBateaux[0], direction);
    /**
     * Bateau ennemi.
     */
    public Bateau croiseurEnnemie = new Bateau(longueurBateaux[1], numBateaux[1], direction);
    /**
     * Bateau ennemi.
     */
    public Bateau contreTorpilleurEnnemie = new Bateau(longueurBateaux[2], numBateaux[2], direction);
    /**
     * Bateau ennemi.
     */
    public Bateau sousMarinEnnemie = new Bateau(longueurBateaux[3], numBateaux[3], direction);
    /**
     * Bateau ennemi.
     */
    public Bateau torpilleurEnnemie = new Bateau(longueurBateaux[4], numBateaux[4], direction);

    /**
     * Fonction permettant de recevoir le clic d'un joueur dans la grille de placement des bateaux et d'ensuite faire les teste pour s'assurer que la position est bonne.
     * @param event Evenement de la souris contenant les informations sur le clic de l'utilisateur dont la ligne et la colonne du clic.
     */
    public void ClicGrillePlacement(MouseEvent event) {
        Node source = (Node)event.getSource();
        Integer ligne = GridPane.getRowIndex(source);
        Integer colonne = GridPane.getColumnIndex(source);

        for(Bateau bateau:listeBateau){
            if(bateau.numGrille == numBateaux[bateauChoisi]){
                if(bateau.posGrilleX == -1){
                    if(placerBateau.PosOk(grilleJoueurBackend, ligne - 1, colonne - 1, direction, longueurBateaux[bateauChoisi])) {
                        placerBateauFx.PlacerUnBateau(grilleJoueur, bateau.imageHorizontale, bateau.imageVerticale, ligne, colonne);
                        nombreBateauPlace++;
                        bateau.SetPosition(ligne - 1, colonne - 1, direction);
                        placerBateau.EcrireDansGrille(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, bateau.direction, bateau.longueur, bateau.numGrille);
                    }
                }
                else {
                    placerBateau.EcrireDansGrille(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, direction, bateau.longueur, 0);
                    if(placerBateau.PosOk(grilleJoueurBackend, ligne - 1, colonne - 1, direction, longueurBateaux[bateauChoisi])) {

                        placerBateauFx.ReplacerUnBateau(grilleJoueur, bateau.imageHorizontale, bateau.imageVerticale, ligne, colonne);
                        bateau.SetPosition(ligne - 1, colonne - 1, direction);
                        placerBateau.EcrireDansGrille(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, bateau.direction, bateau.longueur, bateau.numGrille);
                    }
                }
            }
        }

            if(nombreBateauPlace >= 5){
                messagePlacement.setVisible(true);
                boutonFinPlacement.setVisible(true);
            }

    }

    /**
     * Fonction permettant de tourner le bateau selectionne.
     */
    public void TournerBateau(){

        if(!initialisation) {
            if (direction == 1) {
                direction = 2;

                for (Bateau bateau : listeBateau) {
                    if (bateauChoisi + 1 == bateau.numGrille) {
                        if (bateau.posGrilleX != -1) {
                            placerBateau.EcrireDansGrille(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, 1, bateau.longueur, 0);
                        }
                        if (bateau.posGrilleX == -1 || placerBateau.PosOk(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, direction, bateau.longueur)) {
                            bateau.direction = 2;
                            bateau.imageHorizontale.setVisible(false);
                            bateau.imageVerticale.setVisible(true);

                        } else {
                            direction = 1;
                        }
                        if (bateau.posGrilleX != -1) {
                            placerBateau.EcrireDansGrille(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, direction, bateau.longueur, bateau.numGrille);
                        }
                    }
                }
            } else {
                direction = 1;

                for (Bateau bateau : listeBateau) {
                    if (bateauChoisi + 1 == bateau.numGrille) {
                        if (bateau.posGrilleX != -1) {
                            placerBateau.EcrireDansGrille(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, 2, bateau.longueur, 0);
                        }
                        if (bateau.posGrilleX == -1 || placerBateau.PosOk(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, direction, bateau.longueur)) {
                            bateau.direction = 1;
                            bateau.imageHorizontale.setVisible(true);
                            bateau.imageVerticale.setVisible(false);

                        } else {
                            direction = 2;
                        }
                        if (bateau.posGrilleX != -1) {
                            placerBateau.EcrireDansGrille(grilleJoueurBackend, bateau.posGrilleX, bateau.posGrilleY, direction, bateau.longueur, bateau.numGrille);
                        }
                    }
                }
            }
        }
    }

    /**
     * Fonction appelee par des boutons permettant de determiner quel bateau est selectionne par l'utilisateur.
     * @param event Evenement de la souris contenant les informations sur le clic de l'utilisateur dont le numero du bateau selectionne.
     */
    public void ChoisirBateau(MouseEvent event){
        if(!initialisation) {
            Node source = (Node) event.getSource();
            bateauChoisi = GridPane.getColumnIndex(source);
            if (bateauChoisi == null) {
                bateauChoisi = 0;
            }
            for (Bateau bateau : listeBateau) {
                if (bateauChoisi + 1 == bateau.numGrille) {
                    if (bateau.direction == 1) {
                        direction = 1;
                        bateau.imageHorizontale.setVisible(true);
                    } else {
                        direction = 2;
                        bateau.imageVerticale.setVisible(true);
                    }
                } else {
                    if (bateau.posGrilleX == -1) {
                        bateau.imageHorizontale.setVisible(false);
                        bateau.imageVerticale.setVisible(false);
                    }
                }
            }
        }
    }

    /**
     * Fonction permettant au joueur de tirer un missile sur la grille ennemie.
     * @param event Evenement de la souris contenant les informations sur le clic de l'utilisateur dont la ligne et la colonne du clic.
     */
    public void TirAlier(MouseEvent event){
        if(!partieFinie) {
            Node source = (Node) event.getSource();
            Integer row = GridPane.getRowIndex(source) - 1;
            Integer column = GridPane.getColumnIndex(source) - 1;
            TirCanon tirCanon = new TirCanon();

            if (grilleOrdiBackend[row][column] != 6) {
                String[] nomBateau = new String[]{"Porte-avions", "Croiseur", "Contre-torpilleur", "Sous-marin", "Torpilleur"};
                String nomBateauVise = "";
                if (grilleOrdiBackend[row][column] - 1 != -1 && grilleOrdiBackend[row][column] - 1 != 5) {
                    nomBateauVise = nomBateau[grilleOrdiBackend[row][column] - 1];
                }

                tirCanon(grilleEnnemie, grilleOrdiBackend, column, row);

                tirCanon.Tir(grilleOrdiBackend, row, column);

                if (tirCanon.vainqueur(grilleOrdiBackend)) {
                    FinDePartie();
                }
                tirEnnemi();
            }
        }
    }

    /**
     * Fonction appelee apres le tir du joueur pour permettre a l'ennemi de tirer un missile sur la grille du joueur.
     */
    void tirEnnemi() {

        int ligne;
        int colonne;
        do{
            ligne = randRange(0, 10);
            colonne = randRange(0, 10);
        }
        while(grilleJoueurBackend[ligne][colonne] > 5);
        tirCanon(grilleJoueur, grilleJoueurBackend, colonne, ligne);
        TirCanon tirCanon = new TirCanon();
        tirCanon.Tir(grilleJoueurBackend, ligne, colonne);
        String[] nomBateau = new String[] {"Porte-avions", "Croiseur", "Contre-torpilleur", "Sous-marin", "Torpilleur"};
        String nomBateauVise = "";
        if(grilleOrdiBackend[ligne][colonne] - 1 != -1 && grilleOrdiBackend[ligne][colonne] - 1 != 5){
            nomBateauVise = nomBateau[grilleOrdiBackend[ligne][colonne] - 1];
        }

        if(tirCanon.vainqueur(grilleJoueurBackend)){
            FinDePartie();
        }
    }

    /**
     * Fonction permettant de rendre les tirs visible sur les grilles du joueur et de l'ennemi.
     * @param grille Grille sur laquelle on ajoutera le carre de couleur selon le resultat du tir.
     * @param grilleBackend Grille contenant les informations pour tester sur le tir est bon ou non.
     * @param column Colonne du tir.
     * @param row Ligne du tir.
     */
    void tirCanon(GridPane grille, int[][] grilleBackend, int column, int row){
        placerBateauFx.MarquerTir(grille, grilleBackend, column + 1, row + 1);
    }

    /**
     * Fonction appelee si la partie est terminee et fait l'affichage de fin de partie.
     */
    void FinDePartie(){
        boutonNouvellePartie.setVisible(true);
        TirCanon tirCanon = new TirCanon();
        if(tirCanon.vainqueur(grilleOrdiBackend)){
            if(aTriche){
                messageFinPartie.setText("Vous avez gagne! En trichant...");
            }
            else{
                messageFinPartie.setText("Vous avez gagne!");
            }
        }
        else {
            if(aTriche){
                messageFinPartie.setText("Vous avez perdu... En trichant!");
            }
            else{
                messageFinPartie.setText("Vous avez perdu...");
            }
        }
        messageFinPartie.setVisible(true);
        tricheActive = false;
        partieFinie = true;
    }

    /**
     * Fonction permettant d'initialiser les listes pour le placement des bateaux.
     */
    public void InitialisationPlacement(){
        boutonInitialisation.setVisible(false);
        initialisation = false;

        porteAvions.InitImages(porteAvionsH, porteAvionsV);
        croiseur.InitImages(croiseurH, croiseurV);
        contreTorpilleur.InitImages(contreTorpilleurH, contreTorpilleurV);
        sousMarin.InitImages(sousMarinH, sousMarinV);
        torpilleur.InitImages(torpilleurH, torpilleurV);

        listeBateau.add(porteAvions);
        listeBateau.add(croiseur);
        listeBateau.add(contreTorpilleur);
        listeBateau.add(sousMarin);
        listeBateau.add(torpilleur);

        porteAvionsEnnemie.InitImages(porteAvionsHEnnemie, porteAvionsVEnnemie);
        croiseurEnnemie.InitImages(croiseurHEnnemie, croiseurVEnnemie);
        contreTorpilleurEnnemie.InitImages(contreTorpilleurHEnnemie, contreTorpilleurVEnnemie);
        sousMarinEnnemie.InitImages(sousMarinHEnnemie, sousMarinVEnnemie);
        torpilleurEnnemie.InitImages(torpilleurHEnnemie, torpilleurVEnnemie);

        listeBateauEnnemie.add(porteAvionsEnnemie);
        listeBateauEnnemie.add(croiseurEnnemie);
        listeBateauEnnemie.add(contreTorpilleurEnnemie);
        listeBateauEnnemie.add(sousMarinEnnemie);
        listeBateauEnnemie.add(torpilleurEnnemie);

        Bateau bateau = listeBateau.get(0);
        bateau.imageHorizontale.setVisible(true);
        InitGrilleOrdi();
    }

    /**
     * Fonction permettant de retourner à l'ecran du menu.
     * @param event Evenement de la souris contenant les informations sur le clic de l'utilisateur.
     * @throws IOException
     */
    public void AllerAuMenu(ActionEvent event) throws IOException{
        grilleJoueurBackend = new int[10][10];
        grilleOrdiBackend = new int[10][10];
        HelloApplication.RechargerScene("SceneMenu");
    }

    /**
     * Fonction permettant de changer la scene actuelle pour que le joueur puisse ensuite jouer au jeu.
     * @param event Evenement de la souris contenant les informations sur le clic de l'utilisateur.
     * @throws IOException
     */
    public void ChangerPourSceneJeu(ActionEvent event) throws IOException{
        messagePlacement.setVisible(false);
        boutonFinPlacement.setVisible(false);
        boutonTourner.setVisible(false);
        grilleBoutons.setVisible(false);
        grilleJoueur.setLayoutY(450);
        eauJoueur.setLayoutY(450);
        eauEnnemie.setVisible(true);
        grilleEnnemie.setVisible(true);
        messageGrilleJoueur.setVisible(true);
        messageGrilleEnnemie.setVisible(true);

        if(tricheActive){
            grilleEnnemieTriche.setVisible(true);
            aTriche = true;
        }

        InitBarreMenu();
    }

    /**
     * Fonction permettant d'initialiser la barre de menus.
     */
    void InitBarreMenu(){
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

        InitOptionQuitter(optionQuitter);
        InitOptionRecommencer(optionRecommencer);
        InitOptionTricher(optionTricher);
    }

    /**
     * Initialisation de l'option quitter de la barre de menus.
     * @param option Option du menu a initialiser.
     */
    void InitOptionQuitter(MenuItem option){
        option.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                try{
                    AllerAuMenu(actionEvent);
                }
                catch (IOException e){
                    throw new IllegalStateException("something went wrong", e);
                }
            }
        });
    }

    /**
     * Initialisation de l'option recommencer de la barre de menus.
     * @param option Option du menu a initialiser.
     */
    void InitOptionRecommencer(MenuItem option){
        option.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    grilleJoueurBackend = new int[10][10];
                    grilleOrdiBackend = new int[10][10];
                    HelloApplication.RechargerScene("SceneJeu");
                }
                catch (IOException e){
                    throw new IllegalStateException("something went wrong", e);
                }
            }
        });
    }

    /**
     * Initialisation de l'option tricher de la barre de menus.
     * @param option Option du menu a initialiser.
     */
    void InitOptionTricher(MenuItem option){
        option.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ActiverTriche();
            }
        });
    }

    /**
     * Fonction retournant un nombre aleatoire.
     * @param a Borne inferieure (incluse).
     * @param b Borne exterieure (excluse).
     * @return Nombre aleatoire.
     */
    public static int randRange (int a , int b){
        Random rand = new Random();
        return rand.nextInt(b - a)+ a;
    }

    /**
     * Fonction permettant d'initialiser la grille de l'ordinateur.
     */
    void InitGrilleOrdi(){
        int[] grandeurBateau = new int[] {5, 4, 3, 3, 2};
        int ligne;
        int colonne;
        int direction;
        for(int numeroBateau = 1; numeroBateau <= 5; numeroBateau++)
        {
            ligne = randRange(0, 10);
            colonne = randRange(0, 10);
            direction = randRange(1, 3);

            while (!placerBateau.PosOk(grilleOrdiBackend, ligne, colonne, direction, grandeurBateau[numeroBateau - 1]))
            {
                ligne = randRange(0, 10);
                colonne = randRange(0, 10);
                direction = randRange(1, 3);
            }

            placerBateau.EcrireDansGrille(grilleOrdiBackend, ligne, colonne, direction, grandeurBateau[numeroBateau - 1], numeroBateau);
            for(Bateau bateau:listeBateauEnnemie){
                if(bateau.numGrille == numeroBateau){
                    placerBateauFx.PlacerUnBateau(grilleEnnemieTriche, bateau.imageHorizontale, bateau.imageVerticale, ligne + 1, colonne + 1);
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
    }

    /**
     * Fonction permettant d'activer la triche.
     */
    public void ActiverTriche(){
        if(tricheActive){
            tricheActive = false;
            grilleEnnemieTriche.setVisible(false);
        }
        else {
            tricheActive = true;
            aTriche = true;
            if(!initialisation){
                grilleEnnemieTriche.setVisible(true);
            }
        }
    }
}