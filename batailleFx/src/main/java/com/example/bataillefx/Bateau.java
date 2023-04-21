package com.example.bataillefx;

import javafx.scene.image.ImageView;

/**
 * Classe des bateaux contenant les informations sur les différents bateaux.
 */
public class Bateau {
    /**
     * Longueur du bateau
     */
    public int longueur;
    /**
     * Numero du bateau dans la grille.
     */
    public int numGrille;
    /**
     * Direction du bateau
     */
    public int direction;
    /**
     * Image verticale du bateau.
     */
    public ImageView imageVerticale;
    /**
     * Image horizontale du bateau.
     */
    public ImageView imageHorizontale;
    /**
     * Position en X dans la grille.
     */
    public int posGrilleX;
    /**
     * Position en Y dans la grille.
     */
    public int posGrilleY;

    /**
     * Constructeur du bateau recevant certains paremetres.
     * @param l Longueur du bateau.
     * @param n Numero du bateau dans la grille.
     * @param d Direction du bateau.
     */
    public Bateau(int l, int n, int d){
        longueur = l;
        numGrille = n;
        direction = d;
        posGrilleX = -1;
        posGrilleY = -1;
    }

    /**
     * Fonction pour initialiser les image horizontale et verticale du bateau.
     * @param imageH Image horizontale.
     * @param imageV Image verticale.
     */
    public void InitImages(ImageView imageH, ImageView imageV){
        imageHorizontale = imageH;
        imageVerticale = imageV;
    }

    /**
     * Fonctione permettant de modifier la position du bateau.
     * @param x Position en x.
     * @param y Position en y.
     * @param d Direction.
     */
    public void SetPosition(int x, int y, int d){
        posGrilleX = x;
        posGrilleY = y;
        direction = d;
    }

}
