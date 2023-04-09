package com.example.bataillefx;

import javafx.scene.image.ImageView;

import java.time.Year;

public class Bateau {
    public int longueur;
    public int numGrille;
    public int direction;
    public ImageView imageVerticale;
    public ImageView imageHorizontale;
    public int posGrilleX;
    public int posGrilleY;

    public Bateau(int longueur1, int numGrille1, int direction1){
        longueur = longueur1;
        numGrille = numGrille1;
        direction = direction1;
        posGrilleX = -1;
        posGrilleY = -1;
    }

    public void InitImages(ImageView imageH, ImageView imageV){
        imageHorizontale = imageH;
        imageVerticale = imageV;
    }

    public void SetPosition(int x, int y, int d){
        posGrilleX = x;
        posGrilleY = y;
        direction = d;
    }

}
