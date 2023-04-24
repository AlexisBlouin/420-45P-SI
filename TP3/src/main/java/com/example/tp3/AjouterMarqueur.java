package com.example.tp3;

public class AjouterMarqueur {

    public boolean TesterEmplacement(int[][] grille, int x, int y){
        if(grille[x][y] == 0){
            return true;
        }
        return false;
    }

    public void ModifierGrille(int[][] grille, int num, int x, int y){
        grille[x][y] = num;
    }
}
